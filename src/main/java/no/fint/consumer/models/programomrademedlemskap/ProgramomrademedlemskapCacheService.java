package no.fint.consumer.models.programomrademedlemskap;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.cache.model.CacheObject;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.fint.relations.FintResourceCompatibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.novari.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap;
import no.novari.fint.model.resource.utdanning.utdanningsprogram.ProgramomrademedlemskapResource;
import no.novari.fint.model.utdanning.utdanningsprogram.UtdanningsprogramActions;
import no.novari.fint.model.felles.kompleksedatatyper.Identifikator;

@Slf4j
@Service
@ConditionalOnProperty(name = "fint.consumer.cache.disabled.programomrademedlemskap", havingValue = "false", matchIfMissing = true)
public class ProgramomrademedlemskapCacheService extends CacheService<ProgramomrademedlemskapResource> {

    public static final String MODEL = Programomrademedlemskap.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private ProgramomrademedlemskapLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public ProgramomrademedlemskapCacheService() {
        super(MODEL, UtdanningsprogramActions.GET_ALL_PROGRAMOMRADEMEDLEMSKAP, UtdanningsprogramActions.UPDATE_PROGRAMOMRADEMEDLEMSKAP);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, ProgramomrademedlemskapResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        props.getAssets().forEach(this::createCache);
    }

    @Scheduled(initialDelayString = Constants.CACHE_INITIALDELAY_PROGRAMOMRADEMEDLEMSKAP, fixedRateString = Constants.CACHE_FIXEDRATE_PROGRAMOMRADEMEDLEMSKAP)
    public void populateCacheAll() {
        props.getAssets().forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    @Override
    public void populateCache(String orgId) {
		log.info("Populating Programomrademedlemskap cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, UtdanningsprogramActions.GET_ALL_PROGRAMOMRADEMEDLEMSKAP, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<ProgramomrademedlemskapResource> getProgramomrademedlemskapBySystemId(String orgId, String systemId) {
        return getOne(orgId, systemId.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(ProgramomrademedlemskapResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(systemId::equals)
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<ProgramomrademedlemskapResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<ProgramomrademedlemskapResource> to ProgramomrademedlemskapResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), ProgramomrademedlemskapResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(resource -> {
            linker.mapLinks(resource);
            linker.resetSelfLinks(resource);
        });
        if (UtdanningsprogramActions.valueOf(event.getAction()) == UtdanningsprogramActions.UPDATE_PROGRAMOMRADEMEDLEMSKAP) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                List<CacheObject<ProgramomrademedlemskapResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
                addCache(event.getOrgId(), cacheObjects);
                log.info("Added {} cache objects to cache for {}", cacheObjects.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            List<CacheObject<ProgramomrademedlemskapResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
            updateCache(event.getOrgId(), cacheObjects);
            log.info("Updated cache for {} with {} cache objects", event.getOrgId(), cacheObjects.size());
        }
    }
}

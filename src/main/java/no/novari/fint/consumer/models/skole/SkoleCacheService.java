package no.novari.fint.consumer.models.skole;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.cache.model.CacheObject;
import no.novari.fint.consumer.config.Constants;
import no.novari.fint.consumer.config.ConsumerProps;
import no.novari.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.novari.fint.relations.FintResourceCompatibility;
import no.novari.fint.model.resource.FintLinks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.novari.fint.model.utdanning.utdanningsprogram.Skole;
import no.novari.fint.model.resource.utdanning.utdanningsprogram.SkoleResource;
import no.novari.fint.model.utdanning.utdanningsprogram.UtdanningsprogramActions;
import no.novari.fint.model.felles.kompleksedatatyper.Identifikator;

@Slf4j
@Service
@ConditionalOnProperty(name = "fint.consumer.cache.disabled.skole", havingValue = "false", matchIfMissing = true)
public class SkoleCacheService extends CacheService<SkoleResource> {

    public static final String MODEL = Skole.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private SkoleLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public SkoleCacheService() {
        super(MODEL, UtdanningsprogramActions.GET_ALL_SKOLE, UtdanningsprogramActions.UPDATE_SKOLE);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, SkoleResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        props.getAssets().forEach(this::createCache);
    }

    @Scheduled(initialDelayString = Constants.CACHE_INITIALDELAY_SKOLE, fixedRateString = Constants.CACHE_FIXEDRATE_SKOLE)
    public void populateCacheAll() {
        props.getAssets().forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    @Override
    public void populateCache(String orgId) {
		log.info("Populating Skole cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, UtdanningsprogramActions.GET_ALL_SKOLE, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<SkoleResource> getSkoleBySkolenummer(String orgId, String skolenummer) {
        return getOne(orgId, skolenummer.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(SkoleResource::getSkolenummer)
                .map(Identifikator::getIdentifikatorverdi)
                .map(skolenummer::equals)
                .orElse(false));
    }

    public Optional<SkoleResource> getSkoleBySystemId(String orgId, String systemId) {
        return getOne(orgId, systemId.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(SkoleResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(systemId::equals)
                .orElse(false));
    }

    public Optional<SkoleResource> getSkoleByOrganisasjonsnummer(String orgId, String organisasjonsnummer) {
        return getOne(orgId, organisasjonsnummer.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(SkoleResource::getOrganisasjonsnummer)
                .map(Identifikator::getIdentifikatorverdi)
                .map(organisasjonsnummer::equals)
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<SkoleResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<SkoleResource> to SkoleResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), SkoleResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(resource -> {
            linker.mapLinks(resource);
            linker.resetSelfLinks(resource);
        });
        if (UtdanningsprogramActions.valueOf(event.getAction()) == UtdanningsprogramActions.UPDATE_SKOLE) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                List<CacheObject<SkoleResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
                addCache(event.getOrgId(), cacheObjects);
                log.info("Added {} cache objects to cache for {}", cacheObjects.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            List<CacheObject<SkoleResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
            updateCache(event.getOrgId(), cacheObjects);
            log.info("Updated cache for {} with {} cache objects", event.getOrgId(), cacheObjects.size());
        }
    }
}

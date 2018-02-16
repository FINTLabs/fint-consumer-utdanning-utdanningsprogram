package no.fint.consumer.models.programomrade;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.model.relation.FintResource;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import no.fint.model.utdanning.utdanningsprogram.Programomrade;
import no.fint.model.utdanning.utdanningsprogram.UtdanningsprogramActions;

@Slf4j
@Service
public class ProgramomradeCacheService extends CacheService<FintResource<Programomrade>> {

    public static final String MODEL = Programomrade.class.getSimpleName().toLowerCase();

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    public ProgramomradeCacheService() {
        super(MODEL, UtdanningsprogramActions.GET_ALL_PROGRAMOMRADE);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_PROGRAMOMRADE, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_PROGRAMOMRADE)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Programomrade cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, UtdanningsprogramActions.GET_ALL_PROGRAMOMRADE, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<FintResource<Programomrade>> getProgramomradeBySystemId(String orgId, String systemId) {
        return getOne(orgId, (fintResource) -> Optional
                .ofNullable(fintResource)
                .map(FintResource::getResource)
                .map(Programomrade::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(id -> id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        update(event, new TypeReference<List<FintResource<Programomrade>>>() {
        });
    }
}

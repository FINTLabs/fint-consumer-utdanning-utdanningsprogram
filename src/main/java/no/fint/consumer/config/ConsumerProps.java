package no.fint.consumer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConsumerProps {
    
    @Value("${fint.consumer.override-org-id:false}")
    private boolean overrideOrgId;

    @Value("${fint.consumer.default-client:FINT}")
    private String defaultClient;

    @Value("${fint.consumer.default-org-id:fint.no}")
    private String defaultOrgId;
    
    @Value("${fint.events.orgIds:fint.no}")
    private String[] orgs;

    
    public static final String CACHE_INITIALDELAY_ARSTRINN = "${fint.consumer.cache.initialDelay.arstrinn:60000}";
    public static final String CACHE_FIXEDRATE_ARSTRINN = "${fint.consumer.cache.fixedRate.arstrinn:900000}";
    
    public static final String CACHE_INITIALDELAY_PROGRAMOMRADE = "${fint.consumer.cache.initialDelay.programomrade:70000}";
    public static final String CACHE_FIXEDRATE_PROGRAMOMRADE = "${fint.consumer.cache.fixedRate.programomrade:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLE = "${fint.consumer.cache.initialDelay.skole:80000}";
    public static final String CACHE_FIXEDRATE_SKOLE = "${fint.consumer.cache.fixedRate.skole:900000}";
    
    public static final String CACHE_INITIALDELAY_UTDANNINGSPROGRAM = "${fint.consumer.cache.initialDelay.utdanningsprogram:90000}";
    public static final String CACHE_FIXEDRATE_UTDANNINGSPROGRAM = "${fint.consumer.cache.fixedRate.utdanningsprogram:900000}";
    

}

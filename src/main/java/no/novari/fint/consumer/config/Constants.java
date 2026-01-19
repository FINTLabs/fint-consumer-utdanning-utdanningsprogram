package no.novari.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "utdanning-utdanningsprogram";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_ARSTRINN = "${fint.consumer.cache.initialDelay.arstrinn:900000}";
    public static final String CACHE_FIXEDRATE_ARSTRINN = "${fint.consumer.cache.fixedRate.arstrinn:900000}";
    
    public static final String CACHE_INITIALDELAY_PROGRAMOMRADE = "${fint.consumer.cache.initialDelay.programomrade:1000000}";
    public static final String CACHE_FIXEDRATE_PROGRAMOMRADE = "${fint.consumer.cache.fixedRate.programomrade:900000}";
    
    public static final String CACHE_INITIALDELAY_PROGRAMOMRADEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.programomrademedlemskap:1100000}";
    public static final String CACHE_FIXEDRATE_PROGRAMOMRADEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.programomrademedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLE = "${fint.consumer.cache.initialDelay.skole:1200000}";
    public static final String CACHE_FIXEDRATE_SKOLE = "${fint.consumer.cache.fixedRate.skole:900000}";
    
    public static final String CACHE_INITIALDELAY_UTDANNINGSPROGRAM = "${fint.consumer.cache.initialDelay.utdanningsprogram:1300000}";
    public static final String CACHE_FIXEDRATE_UTDANNINGSPROGRAM = "${fint.consumer.cache.fixedRate.utdanningsprogram:900000}";
    

}

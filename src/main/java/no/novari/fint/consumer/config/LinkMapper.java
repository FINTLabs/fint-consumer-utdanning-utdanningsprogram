package no.novari.fint.consumer.config;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.novari.fint.consumer.utils.RestEndpoints;
import no.novari.fint.model.utdanning.utdanningsprogram.Arstrinn;
import no.novari.fint.model.utdanning.utdanningsprogram.Programomrade;
import no.novari.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap;
import no.novari.fint.model.utdanning.utdanningsprogram.Skole;
import no.novari.fint.model.utdanning.utdanningsprogram.Utdanningsprogram;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Arstrinn.class.getName(), contextPath + RestEndpoints.ARSTRINN)
            .put(Programomrade.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADE)
            .put(Programomrademedlemskap.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADEMEDLEMSKAP)
            .put(Skole.class.getName(), contextPath + RestEndpoints.SKOLE)
            .put(Utdanningsprogram.class.getName(), contextPath + RestEndpoints.UTDANNINGSPROGRAM)
            .put("no.novari.fint.model.felles.kodeverk.iso.Landkode", "/model/felles/kodeverk/iso/landkode")
            .put("no.novari.fint.model.utdanning.elev.Klasse", "/model/utdanning/elev/klasse")
            .put("no.novari.fint.model.utdanning.timeplan.Fag", "/model/utdanning/timeplan/fag")
            .put("no.novari.fint.model.utdanning.elev.Elevforhold", "/model/utdanning/elev/elevforhold")
            .put("no.novari.fint.model.administrasjon.organisasjon.Organisasjonselement", "/model/administrasjon/organisasjon/organisasjonselement")
            .put("no.novari.fint.model.utdanning.kodeverk.Skoleeiertype", "/model/utdanning/kodeverk/skoleeiertype")
            .put("no.novari.fint.model.utdanning.elev.Kontaktlarergruppe", "/model/utdanning/elev/kontaktlarergruppe")
            .put("no.novari.fint.model.utdanning.elev.Skoleressurs", "/model/utdanning/elev/skoleressurs")
            .put("no.novari.fint.model.utdanning.elev.Undervisningsforhold", "/model/utdanning/elev/undervisningsforhold")
            .put("no.novari.fint.model.utdanning.timeplan.Faggruppe", "/model/utdanning/timeplan/faggruppe")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppe", "/model/utdanning/timeplan/undervisningsgruppe")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppe", "/model/utdanning/vurdering/eksamensgruppe")
            /* .put(TODO,TODO) */
            .build();
    }

}

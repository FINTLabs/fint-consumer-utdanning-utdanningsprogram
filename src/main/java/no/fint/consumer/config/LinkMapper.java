package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
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
            .put("no.novari.fint.model.felles.kodeverk.iso.Landkode", "/felles/kodeverk/iso/landkode")
            .put("no.novari.fint.model.utdanning.elev.Klasse", "/utdanning/elev/klasse")
            .put("no.novari.fint.model.utdanning.timeplan.Fag", "/utdanning/timeplan/fag")
            .put("no.novari.fint.model.utdanning.elev.Elevforhold", "/utdanning/elev/elevforhold")
            .put("no.novari.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
            .put("no.novari.fint.model.utdanning.kodeverk.Skoleeiertype", "/utdanning/kodeverk/skoleeiertype")
            .put("no.novari.fint.model.utdanning.elev.Kontaktlarergruppe", "/utdanning/elev/kontaktlarergruppe")
            .put("no.novari.fint.model.utdanning.elev.Skoleressurs", "/utdanning/elev/skoleressurs")
            .put("no.novari.fint.model.utdanning.elev.Undervisningsforhold", "/utdanning/elev/undervisningsforhold")
            .put("no.novari.fint.model.utdanning.timeplan.Faggruppe", "/utdanning/timeplan/faggruppe")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppe", "/utdanning/timeplan/undervisningsgruppe")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppe", "/utdanning/vurdering/eksamensgruppe")
            /* .put(TODO,TODO) */
            .build();
    }

}

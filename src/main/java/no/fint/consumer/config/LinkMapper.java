package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.utdanning.utdanningsprogram.Arstrinn;
import no.fint.model.utdanning.utdanningsprogram.Programomrade;
import no.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap;
import no.fint.model.utdanning.utdanningsprogram.Skole;
import no.fint.model.utdanning.utdanningsprogram.Utdanningsprogram;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Arstrinn.class.getName(), contextPath + RestEndpoints.ARSTRINN)
            .put(Programomrade.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADE)
            .put(Programomrademedlemskap.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADEMEDLEMSKAP)
            .put(Skole.class.getName(), contextPath + RestEndpoints.SKOLE)
            .put(Utdanningsprogram.class.getName(), contextPath + RestEndpoints.UTDANNINGSPROGRAM)
            .put("no.fint.model.felles.kodeverk.iso.Landkode", "/felles/kodeverk/iso/landkode")
            .put("no.fint.model.utdanning.elev.Basisgruppe", "/utdanning/elev/basisgruppe")
            .put("no.fint.model.utdanning.elev.Medlemskap", "/utdanning/elev/medlemskap")
            .put("no.fint.model.utdanning.elev.Elevforhold", "/utdanning/elev/elevforhold")
            .put("no.fint.model.utdanning.timeplan.Fag", "/utdanning/timeplan/fag")
            .put("no.fint.model.utdanning.larling.Larling", "/utdanning/larling/larling")
            .put("no.fint.model.utdanning.ot.OTUngdom", "/utdanning/ot/otungdom")
            .put("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
            .put("no.fint.model.utdanning.kodeverk.Skoleeiertype", "/utdanning/kodeverk/skoleeiertype")
            .put("no.fint.model.utdanning.elev.Kontaktlarergruppe", "/utdanning/elev/kontaktlarergruppe")
            .put("no.fint.model.utdanning.elev.Skoleressurs", "/utdanning/elev/skoleressurs")
            .put("no.fint.model.utdanning.elev.Undervisningsforhold", "/utdanning/elev/undervisningsforhold")
            .put("no.fint.model.utdanning.timeplan.Undervisningsgruppe", "/utdanning/timeplan/undervisningsgruppe")
            .put("no.fint.model.utdanning.vurdering.Eksamensgruppe", "/utdanning/vurdering/eksamensgruppe")
            /* .put(TODO,TODO) */
            .build();
    }

}

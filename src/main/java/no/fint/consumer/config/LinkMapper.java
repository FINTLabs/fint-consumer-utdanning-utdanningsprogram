package no.fint.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.consumer.utils.RestEndpoints;
import no.fint.model.felles.Person;
import no.fint.model.utdanning.elev.*;
import no.fint.model.utdanning.kodeverk.Elevkategori;
import no.fint.model.utdanning.kodeverk.Fravarstype;
import no.fint.model.utdanning.kodeverk.Karakterskala;
import no.fint.model.utdanning.kodeverk.Skoleeiertype;
import no.fint.model.utdanning.timeplan.Fag;
import no.fint.model.utdanning.timeplan.Rom;
import no.fint.model.utdanning.timeplan.Time;
import no.fint.model.utdanning.timeplan.Undervisningsgruppe;
import no.fint.model.utdanning.utdanningsprogram.*;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String, String>builder()
                .put(Arstrinn.class.getName(), contextPath + RestEndpoints.ARSTRINN)
                .put(Programomrade.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADE)
                .put(Programomrademedlemskap.class.getName(), contextPath + RestEndpoints.PROGRAMOMRADEMEDLEMSKAP)
                .put(Skole.class.getName(), contextPath + RestEndpoints.SKOLE)
                .put(Utdanningsprogram.class.getName(), contextPath + RestEndpoints.UTDANNINGSPROGRAM)
                .put(Basisgruppe.class.getName(), "/utdanning/elev/basisgruppe")
                .put(Elev.class.getName(), "/utdanning/elev/elev")
                .put(Elevforhold.class.getName(), "/utdanning/elev/elevforhold")
                .put(Kontaktlarergruppe.class.getName(), "/utdanning/elev/kontaktlarergruppe")
                .put(Medlemskap.class.getName(), "/utdanning/elev/medlemskap")
                .put(Person.class.getName(), "/utdanning/elev/person")
                .put(Undervisningsforhold.class.getName(), "/utdanning/elev/undervisningsforhold")
                .put(Skoleressurs.class.getName(), "/utdanning/elev/skoleressurs")
                .put(Fag.class.getName(), "/utdanning/timeplan/fag")
                .put(Rom.class.getName(), "/utdanning/timeplan/rom")
                .put(Time.class.getName(), "/utdanning/timeplan/time")
                .put(Undervisningsgruppe.class.getName(), "/utdanning/timeplan/undervisningsgruppe")
                .put(Elevkategori.class.getName(), "/utdanning/kodeverk/elevkategori")
                .put(Fravarstype.class.getName(), "/utdanning/kodeverk/fravarstype")
                .put(Karakterskala.class.getName(), "/utdanning/kodeverk/karakterskala")
                .put(Skoleeiertype.class.getName(), "/utdanning/kodeverk/skoleeiertype")
                .put("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
                .build();
    }

}

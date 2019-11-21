package no.fint.consumer.models.skole;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.SkoleResource;
import no.fint.model.resource.utdanning.utdanningsprogram.SkoleResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class SkoleLinker extends FintLinker<SkoleResource> {

    public SkoleLinker() {
        super(SkoleResource.class);
    }

    public void mapLinks(SkoleResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkoleResources toResources(Collection<SkoleResource> collection) {
        SkoleResources resources = new SkoleResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(SkoleResource skole) {
        if (!isNull(skole.getSkolenummer()) && !isEmpty(skole.getSkolenummer().getIdentifikatorverdi())) {
            return createHrefWithId(skole.getSkolenummer().getIdentifikatorverdi(), "skolenummer");
        }
        if (!isNull(skole.getSystemId()) && !isEmpty(skole.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(skole.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        if (!isNull(skole.getOrganisasjonsnummer()) && !isEmpty(skole.getOrganisasjonsnummer().getIdentifikatorverdi())) {
            return createHrefWithId(skole.getOrganisasjonsnummer().getIdentifikatorverdi(), "organisasjonsnummer");
        }
        
        return null;
    }

    int[] hashCodes(SkoleResource skole) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(skole.getSkolenummer()) && !isEmpty(skole.getSkolenummer().getIdentifikatorverdi())) {
            builder.add(skole.getSkolenummer().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(skole.getSystemId()) && !isEmpty(skole.getSystemId().getIdentifikatorverdi())) {
            builder.add(skole.getSystemId().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(skole.getOrganisasjonsnummer()) && !isEmpty(skole.getOrganisasjonsnummer().getIdentifikatorverdi())) {
            builder.add(skole.getOrganisasjonsnummer().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}


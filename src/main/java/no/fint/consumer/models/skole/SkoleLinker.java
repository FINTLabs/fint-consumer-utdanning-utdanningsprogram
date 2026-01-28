package no.fint.consumer.models.skole;

import no.novari.fint.model.resource.utdanning.utdanningsprogram.SkoleResource;
import no.novari.fint.model.resource.utdanning.utdanningsprogram.SkoleResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public SkoleResources toResources(Stream<SkoleResource> stream, int offset, int size, int totalItems) {
        SkoleResources resources = new SkoleResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(SkoleResource skole) {
        return getAllSelfHrefs(skole).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SkoleResource skole) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(skole.getSkolenummer()) && !isEmpty(skole.getSkolenummer().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skole.getSkolenummer().getIdentifikatorverdi(), "skolenummer"));
        }
        if (!isNull(skole.getSystemId()) && !isEmpty(skole.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skole.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        if (!isNull(skole.getOrganisasjonsnummer()) && !isEmpty(skole.getOrganisasjonsnummer().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skole.getOrganisasjonsnummer().getIdentifikatorverdi(), "organisasjonsnummer"));
        }
        
        return builder.build();
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


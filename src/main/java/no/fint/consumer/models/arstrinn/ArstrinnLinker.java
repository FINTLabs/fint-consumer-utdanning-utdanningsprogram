package no.fint.consumer.models.arstrinn;

import no.novari.fint.model.resource.utdanning.utdanningsprogram.ArstrinnResource;
import no.novari.fint.model.resource.utdanning.utdanningsprogram.ArstrinnResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ArstrinnLinker extends FintLinker<ArstrinnResource> {

    public ArstrinnLinker() {
        super(ArstrinnResource.class);
    }

    public void mapLinks(ArstrinnResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ArstrinnResources toResources(Collection<ArstrinnResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ArstrinnResources toResources(Stream<ArstrinnResource> stream, int offset, int size, int totalItems) {
        ArstrinnResources resources = new ArstrinnResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ArstrinnResource arstrinn) {
        return getAllSelfHrefs(arstrinn).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ArstrinnResource arstrinn) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(arstrinn.getSystemId()) && !isEmpty(arstrinn.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(arstrinn.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ArstrinnResource arstrinn) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(arstrinn.getSystemId()) && !isEmpty(arstrinn.getSystemId().getIdentifikatorverdi())) {
            builder.add(arstrinn.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}


package no.fint.consumer.models.utdanningsprogram;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.UtdanningsprogramResource;
import no.fint.model.resource.utdanning.utdanningsprogram.UtdanningsprogramResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class UtdanningsprogramLinker extends FintLinker<UtdanningsprogramResource> {

    public UtdanningsprogramLinker() {
        super(UtdanningsprogramResource.class);
    }

    public void mapLinks(UtdanningsprogramResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public UtdanningsprogramResources toResources(Collection<UtdanningsprogramResource> collection) {
        UtdanningsprogramResources resources = new UtdanningsprogramResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(UtdanningsprogramResource utdanningsprogram) {
        return getAllSelfHrefs(utdanningsprogram).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(UtdanningsprogramResource utdanningsprogram) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(utdanningsprogram.getSystemId()) && !isEmpty(utdanningsprogram.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(utdanningsprogram.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(UtdanningsprogramResource utdanningsprogram) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(utdanningsprogram.getSystemId()) && !isEmpty(utdanningsprogram.getSystemId().getIdentifikatorverdi())) {
            builder.add(utdanningsprogram.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}


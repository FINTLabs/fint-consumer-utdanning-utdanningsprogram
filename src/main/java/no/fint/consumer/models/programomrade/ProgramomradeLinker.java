package no.fint.consumer.models.programomrade;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResource;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ProgramomradeLinker extends FintLinker<ProgramomradeResource> {

    public ProgramomradeLinker() {
        super(ProgramomradeResource.class);
    }

    public void mapLinks(ProgramomradeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ProgramomradeResources toResources(Collection<ProgramomradeResource> collection) {
        ProgramomradeResources resources = new ProgramomradeResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ProgramomradeResource programomrade) {
        return getAllSelfHrefs(programomrade).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ProgramomradeResource programomrade) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(programomrade.getSystemId()) && !isEmpty(programomrade.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(programomrade.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ProgramomradeResource programomrade) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(programomrade.getSystemId()) && !isEmpty(programomrade.getSystemId().getIdentifikatorverdi())) {
            builder.add(programomrade.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}


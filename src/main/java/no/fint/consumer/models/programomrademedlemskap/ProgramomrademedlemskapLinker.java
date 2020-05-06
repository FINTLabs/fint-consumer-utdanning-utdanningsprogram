package no.fint.consumer.models.programomrademedlemskap;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomrademedlemskapResource;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomrademedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ProgramomrademedlemskapLinker extends FintLinker<ProgramomrademedlemskapResource> {

    public ProgramomrademedlemskapLinker() {
        super(ProgramomrademedlemskapResource.class);
    }

    public void mapLinks(ProgramomrademedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ProgramomrademedlemskapResources toResources(Collection<ProgramomrademedlemskapResource> collection) {
        ProgramomrademedlemskapResources resources = new ProgramomrademedlemskapResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ProgramomrademedlemskapResource programomrademedlemskap) {
        return getAllSelfHrefs(programomrademedlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ProgramomrademedlemskapResource programomrademedlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(programomrademedlemskap.getSystemId()) && !isEmpty(programomrademedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(programomrademedlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ProgramomrademedlemskapResource programomrademedlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(programomrademedlemskap.getSystemId()) && !isEmpty(programomrademedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(programomrademedlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}


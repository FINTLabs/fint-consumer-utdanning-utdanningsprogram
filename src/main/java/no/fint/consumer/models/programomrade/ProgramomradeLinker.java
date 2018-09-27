package no.fint.consumer.models.programomrade;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResource;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (!isNull(programomrade.getSystemId()) && !isEmpty(programomrade.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(programomrade.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}


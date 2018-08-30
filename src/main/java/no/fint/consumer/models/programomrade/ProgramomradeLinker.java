package no.fint.consumer.models.programomrade;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResource;
import no.fint.model.resource.utdanning.utdanningsprogram.ProgramomradeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (programomrade.getSystemId() != null && programomrade.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(programomrade.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}


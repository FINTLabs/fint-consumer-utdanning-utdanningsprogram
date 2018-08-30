package no.fint.consumer.models.utdanningsprogram;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.UtdanningsprogramResource;
import no.fint.model.resource.utdanning.utdanningsprogram.UtdanningsprogramResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (utdanningsprogram.getSystemId() != null && utdanningsprogram.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(utdanningsprogram.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}


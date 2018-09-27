package no.fint.consumer.models.arstrinn;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.ArstrinnResource;
import no.fint.model.resource.utdanning.utdanningsprogram.ArstrinnResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        ArstrinnResources resources = new ArstrinnResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ArstrinnResource arstrinn) {
        if (!isNull(arstrinn.getSystemId()) && !isEmpty(arstrinn.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(arstrinn.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}


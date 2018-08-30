package no.fint.consumer.models.skole;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.utdanningsprogram.SkoleResource;
import no.fint.model.resource.utdanning.utdanningsprogram.SkoleResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (skole.getSkolenummer() != null && skole.getSkolenummer().getIdentifikatorverdi() != null) {
            return createHrefWithId(skole.getSkolenummer().getIdentifikatorverdi(), "skolenummer");
        }
        if (skole.getSystemId() != null && skole.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(skole.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        if (skole.getOrganisasjonsnummer() != null && skole.getOrganisasjonsnummer().getIdentifikatorverdi() != null) {
            return createHrefWithId(skole.getOrganisasjonsnummer().getIdentifikatorverdi(), "organisasjonsnummer");
        }
        
        return null;
    }
    
}


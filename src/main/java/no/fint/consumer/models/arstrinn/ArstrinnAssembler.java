package no.fint.consumer.models.arstrinn;

import no.fint.model.utdanning.utdanningsprogram.Arstrinn;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class ArstrinnAssembler extends FintResourceAssembler<Arstrinn> {

    public ArstrinnAssembler() {
        super(ArstrinnController.class);
    }


    @Override
    public FintResourceSupport assemble(Arstrinn arstrinn , FintResource<Arstrinn> fintResource) {
        return createResourceWithId(arstrinn.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}


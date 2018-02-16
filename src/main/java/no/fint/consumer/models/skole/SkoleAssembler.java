package no.fint.consumer.models.skole;

import no.fint.model.utdanning.utdanningsprogram.Skole;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class SkoleAssembler extends FintResourceAssembler<Skole> {

    public SkoleAssembler() {
        super(SkoleController.class);
    }


    @Override
    public FintResourceSupport assemble(Skole skole , FintResource<Skole> fintResource) {
        return createResourceWithId(skole.getSkolenummer().getIdentifikatorverdi(), fintResource, "skolenummer");
    }
    
    
    
}


package no.fint.consumer.models.utdanningsprogram;

import no.fint.model.utdanning.utdanningsprogram.Utdanningsprogram;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class UtdanningsprogramAssembler extends FintResourceAssembler<Utdanningsprogram> {

    public UtdanningsprogramAssembler() {
        super(UtdanningsprogramController.class);
    }


    @Override
    public FintResourceSupport assemble(Utdanningsprogram utdanningsprogram , FintResource<Utdanningsprogram> fintResource) {
        return createResourceWithId(utdanningsprogram.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}


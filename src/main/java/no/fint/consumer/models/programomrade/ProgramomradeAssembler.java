package no.fint.consumer.models.programomrade;

import no.fint.model.utdanning.utdanningsprogram.Programomrade;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class ProgramomradeAssembler extends FintResourceAssembler<Programomrade> {

    public ProgramomradeAssembler() {
        super(ProgramomradeController.class);
    }


    @Override
    public FintResourceSupport assemble(Programomrade programomrade , FintResource<Programomrade> fintResource) {
        return createResourceWithId(programomrade.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}


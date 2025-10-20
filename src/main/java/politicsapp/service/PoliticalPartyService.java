package politicsapp.service;

import org.springframework.stereotype.Service;
import politicsapp.data.PoliticalPartyRepository;
import politicsapp.model.PoliticalParty;

import java.util.UUID;

@Service
public class PoliticalPartyService {
    private final PoliticalPartyRepository politicalPartyRepository;

    public PoliticalPartyService(PoliticalPartyRepository politicalPartyRepository) {
        this.politicalPartyRepository = politicalPartyRepository;
    }

    public UUID save(PoliticalParty politicalParty) {
        return politicalPartyRepository.save(politicalParty).getId();
    }

    public PoliticalParty getById(UUID id){
        return politicalPartyRepository.findById(id).orElse(null);
    }

    public PoliticalParty getByName(String name){
        return politicalPartyRepository.findByName(name);
    }

    public Iterable<PoliticalParty> getAll(){
        return politicalPartyRepository.findAll();
    }

    public void deleteById(UUID id){
        politicalPartyRepository.deleteById(id);
    }
}

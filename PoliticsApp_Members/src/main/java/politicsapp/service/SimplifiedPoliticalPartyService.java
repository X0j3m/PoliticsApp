package politicsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import politicsapp.data.SimplifiedPoliticalPartyRepository;
import politicsapp.model.SimplifiedPoliticalParty;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimplifiedPoliticalPartyService {
    private final SimplifiedPoliticalPartyRepository simplifiedPoliticalPartyRepository;

    public SimplifiedPoliticalParty findById(UUID id) {
        return simplifiedPoliticalPartyRepository.findById(id).orElse(null);
    }

    public UUID create(SimplifiedPoliticalParty party) {
        return simplifiedPoliticalPartyRepository.save(party).getId();
    }

    public SimplifiedPoliticalParty delete(UUID id) {
        SimplifiedPoliticalParty party = simplifiedPoliticalPartyRepository.findById(id).orElse(null);
        if (party != null) {
            simplifiedPoliticalPartyRepository.deleteById(id);
        }
        return party;
    }
}

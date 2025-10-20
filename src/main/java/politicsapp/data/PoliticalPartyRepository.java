package politicsapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import politicsapp.model.PoliticalParty;

import java.util.UUID;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, UUID> {
    PoliticalParty findByName(String name);
}

package politicsapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import politicsapp.model.SimplifiedPoliticalParty;

import java.util.UUID;

@Repository
public interface SimplifiedPoliticalPartyRepository extends JpaRepository<SimplifiedPoliticalParty, UUID> {}

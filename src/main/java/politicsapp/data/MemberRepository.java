package politicsapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import politicsapp.model.Member;
import politicsapp.model.PoliticalParty;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Iterable<Member> findAllByPoliticalParty(PoliticalParty politicalParty);
    Iterable<Member> findAllByNameAndSurname(String name, String surname);
}

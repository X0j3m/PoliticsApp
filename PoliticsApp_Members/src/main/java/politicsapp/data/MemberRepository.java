package politicsapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import politicsapp.model.Member;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Iterable<Member> findAllByNameAndSurname(String name, String surname);
    Iterable<Member> findAllBySimplifiedPoliticalPartyId_Id(UUID id);
}

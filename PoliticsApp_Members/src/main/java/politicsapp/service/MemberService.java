package politicsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import politicsapp.data.MemberRepository;
import politicsapp.model.Member;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public UUID create(Member member) {
        return memberRepository.save(member).getId();
    }

    public Member getById(UUID id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Iterable<Member> getAll() {
        return memberRepository.findAll();
    }

    public Iterable<Member> findByPoliticalPartyId(UUID politicalPartyId) {
        return memberRepository.findAllBySimplifiedPoliticalPartyId_Id(politicalPartyId);
    }

    public Iterable<Member> findByNameAndSurname(String name, String surname) {
        return memberRepository.findAllByNameAndSurname(name, surname);
    }

    public UUID deleteById(UUID id) {
        Member m = memberRepository.findById(id).orElse(null);
        if (m != null) {
            memberRepository.deleteById(id);
            return id;
        }
        return null;
    }
}

package politicsapp.service;

import org.springframework.stereotype.Service;
import politicsapp.data.MemberRepository;
import politicsapp.model.Member;

import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member getById(UUID id){
        return memberRepository.findById(id).orElse(null);
    }

    public Iterable<Member> getAll(){
        return memberRepository.findAll();
    }

    public Iterable<Member> findByNameAndSurname(String name, String surname){
        return memberRepository.findAllByNameAndSurname(name, surname);
    }

    public void deleteById(UUID id){
        memberRepository.deleteById(id);
    }
}

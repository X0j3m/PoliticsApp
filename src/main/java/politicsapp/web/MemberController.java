package politicsapp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import politicsapp.LocalDateCreator;
import politicsapp.model.Member;
import politicsapp.model.PoliticalParty;
import politicsapp.service.MemberService;
import politicsapp.service.PoliticalPartyService;
import politicsapp.web.dto.CreateUpdateMemberDto;
import politicsapp.web.dto.ReadMemberDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final PoliticalPartyService politicalPartyService;

    public MemberController(MemberService memberService, PoliticalPartyService politicalPartyService) {
        this.memberService = memberService;
        this.politicalPartyService = politicalPartyService;
    }

    @GetMapping("/political-parties/{name}/members/{id}")
    public ResponseEntity<ReadMemberDto> getMember(@PathVariable String name, @PathVariable String id) {
        Member member = memberService.getById(UUID.fromString(id));
        PoliticalParty party = politicalPartyService.getByName(name);

        if (party == null) {
            return ResponseEntity.badRequest().build();
        }

        if (member != null) {
            if (member.getPoliticalParty().equals(party)) {
                ReadMemberDto dto =
                        ReadMemberDto.builder()
                                .id(member.getId())
                                .name(member.getName())
                                .surname(member.getSurname())
                                .dateOfBirth(member.getDateOfBirth().toString())
                                .placeOfBirth(member.getPlaceOfBirth())
                                .constituency(member.getConstituency())
                                .politicalParty(member.getPoliticalParty().getName())
                                .build();

                return ResponseEntity.ok(dto);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/political-parties/{name}/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getMembersByName(@PathVariable String name) {
        PoliticalParty p = politicalPartyService.getByName(name);

        if (p == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Member> members = (List<Member>) memberService.findByPoliticalPartyId(p.getId());

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        return ResponseEntity.ok(memberDtos);
    }

    @GetMapping("/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getAllMembers() {
        List<Member> members = (List<Member>) memberService.getAll();

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        return ResponseEntity.ok(memberDtos);
    }

    @DeleteMapping("/political-parties/{name}/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name, @PathVariable String id) {
        PoliticalParty politicalParty = politicalPartyService.getByName(name);

        if (politicalParty == null) {
            return ResponseEntity.badRequest().build();
        }

        Member member = memberService.getById(UUID.fromString(id));

        if (member != null) {
            if (member.getPoliticalParty().equals(politicalParty)) {
                memberService.deleteById(UUID.fromString(id));
                return ResponseEntity.noContent().build();
            }
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/political-parties/{name}/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable String name, @PathVariable UUID id, @RequestBody CreateUpdateMemberDto dto) {
        PoliticalParty p = politicalPartyService.getByName(name);

        if (p == null) {
            return ResponseEntity.badRequest().build();
        }

        Member updated =
                Member.builder()
                        .id(id)
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .dateOfBirth(LocalDateCreator.fromString(dto.getDateOfBirth()))
                        .placeOfBirth(dto.getPlaceOfBirth())
                        .constituency(dto.getConstituency())
                        .politicalParty(p)
                        .build();
        memberService.create(updated);
        return ResponseEntity.created(URI.create("/political-parties/" + name + "/members/" + id)).build();

    }
}

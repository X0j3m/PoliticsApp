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

    @GetMapping("/members/{id}")
    public ResponseEntity<ReadMemberDto> getMember(@PathVariable String id) {
        Member member = memberService.getById(UUID.fromString(id));

        if (member != null) {
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

    @GetMapping("/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getAllMembers() {
        List<Member> members = (List<Member>) memberService.getAll();

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        return ResponseEntity.ok(memberDtos);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        UUID uuid = memberService.deleteById(UUID.fromString(id));
        if (uuid != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable UUID id, @RequestBody CreateUpdateMemberDto dto) {
        PoliticalParty p = politicalPartyService.getByName(dto.getPoliticalParty());

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
        return ResponseEntity.created(URI.create("/members/" + id)).build();

    }
}

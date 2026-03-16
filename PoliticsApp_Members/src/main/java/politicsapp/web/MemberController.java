package politicsapp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import politicsapp.LocalDateCreator;
import politicsapp.model.Member;
import politicsapp.model.SimplifiedPoliticalParty;
import politicsapp.service.MemberService;
import politicsapp.service.SimplifiedPoliticalPartyService;
import politicsapp.web.dto.CreateUpdateMemberDto;
import politicsapp.web.dto.ReadMemberDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final SimplifiedPoliticalPartyService simplifiedPoliticalPartyService;

    @GetMapping("/political-parties/{party_id}/members/{id}")
    public ResponseEntity<ReadMemberDto> getMember(@PathVariable String party_id, @PathVariable String id) {
        Member member = memberService.getById(UUID.fromString(id));
        SimplifiedPoliticalParty partyId = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));

        if (partyId == null) {
            log.error("Party {} not found", party_id);
            return ResponseEntity.badRequest().build();
        }

        if (member != null) {
            if (member.getSimplifiedPoliticalPartyId().equals(partyId)) {
                ReadMemberDto dto =
                        ReadMemberDto.builder()
                                .id(member.getId())
                                .name(member.getName())
                                .surname(member.getSurname())
                                .dateOfBirth(member.getDateOfBirth().toString())
                                .placeOfBirth(member.getPlaceOfBirth())
                                .constituency(member.getConstituency())
                                .politicalPartyId(member.getSimplifiedPoliticalPartyId().getId())
                                .build();

                log.info("Member found: {}", dto);
                return ResponseEntity.ok(dto);
            }
            log.info("Member not found");
            return ResponseEntity.notFound().build();
        }
        log.info("Member not found");
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/political-parties/{party_id}/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getMembersByName(@PathVariable String party_id) {
        SimplifiedPoliticalParty partyId = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));

        if (partyId == null) {
            log.error("Party {} not found", party_id);
            return ResponseEntity.badRequest().build();
        }

        List<Member> members = (List<Member>) memberService.findByPoliticalPartyId(partyId.getId());

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        log.info("Members found: {}", memberDtos);
        return ResponseEntity.ok(memberDtos);
    }

    @GetMapping("/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getAllMembers() {
        List<Member> members = (List<Member>) memberService.getAll();

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        log.info("Members found: {}", memberDtos);
        return ResponseEntity.ok(memberDtos);
    }

    @DeleteMapping("/political-parties/{party_id}/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String party_id, @PathVariable String id) {
        SimplifiedPoliticalParty partyId = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));

        if (partyId == null) {
            log.error("Party {} not found", party_id);
            return ResponseEntity.badRequest().build();
        }

        Member member = memberService.getById(UUID.fromString(id));

        if (member != null) {
            if (member.getSimplifiedPoliticalPartyId().equals(partyId)) {
                memberService.deleteById(UUID.fromString(id));
                log.info("Member {} deleted", id);
                return ResponseEntity.noContent().build();
            }
            log.info("Member not found");
            ResponseEntity.notFound().build();
        }
        log.info("Member not found");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/political-parties/{party_id}/members")
    public ResponseEntity<Void> deletePartyMembers(@PathVariable String party_id) {
        SimplifiedPoliticalParty partyId = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));

        if (partyId == null) {
            log.error("Party {} not found", party_id);
            return ResponseEntity.badRequest().build();
        }

        memberService.findByPoliticalPartyId(partyId.getId()).forEach(
                member -> memberService.deleteById(member.getId())
        );

        log.info("All members of party {} deleted", party_id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/political-parties/{party_id}/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable String party_id, @PathVariable String id, @RequestBody CreateUpdateMemberDto dto) {
        SimplifiedPoliticalParty partyId = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));

        if (partyId == null) {
            log.error("Party {} not found", party_id);
            return ResponseEntity.badRequest().build();
        }

        Member updated =
                Member.builder()
                        .id(UUID.fromString(id))
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .dateOfBirth(LocalDateCreator.fromString(dto.getDateOfBirth()))
                        .placeOfBirth(dto.getPlaceOfBirth())
                        .constituency(dto.getConstituency())
                        .simplifiedPoliticalPartyId(partyId)
                        .build();
        memberService.create(updated);
        log.info("Member {} updated", id);
        return ResponseEntity.created(URI.create("/political-parties/" + party_id + "/members/" + id)).build();

    }
}

package politicsapp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import politicsapp.LocalDateCreator;
import politicsapp.model.Member;
import politicsapp.model.PoliticalParty;
import politicsapp.service.MemberService;
import politicsapp.web.dto.CreateUpdatePoliticalPartyDto;
import politicsapp.web.dto.ReadMemberDto;
import politicsapp.web.dto.ReadPoliticalPartyDto;
import politicsapp.service.PoliticalPartyService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PoliticalPartyController {
    private final PoliticalPartyService politicalPartyService;
    private final MemberService memberService;

    public PoliticalPartyController(PoliticalPartyService politicalPartyService, MemberService memberService) {
        this.politicalPartyService = politicalPartyService;
        this.memberService = memberService;
    }

    @GetMapping("political-parties/{name}")
    public ResponseEntity<ReadPoliticalPartyDto> getPartyByName(@PathVariable String name) {
        PoliticalParty found = politicalPartyService.getByName(name);

        if (found != null) {
            ReadPoliticalPartyDto dto =
                    ReadPoliticalPartyDto.builder()
                            .id(found.getId())
                            .name(found.getName())
                            .dateOfEstablishment(found.getDateOfEstablishment().toString())
                            .build();

            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/political-parties")
    public ResponseEntity<Iterable<ReadPoliticalPartyDto>> getAll() {
        List<PoliticalParty> politicalParties = (List<PoliticalParty>) politicalPartyService.getAll();
        List<ReadPoliticalPartyDto> politicalPartiesDtos =
                politicalParties.stream().map(p ->
                        ReadPoliticalPartyDto.builder()
                                .id(p.getId())
                                .name(p.getName())
                                .dateOfEstablishment(p.getDateOfEstablishment().toString())
                                .build()
                ).toList();
        return ResponseEntity.ok(politicalPartiesDtos);
    }

    @GetMapping("/political-parties/{name}/members")
    public ResponseEntity<Iterable<ReadMemberDto>> getMembersByName(@PathVariable String name) {
        PoliticalParty p = politicalPartyService.getByName(name);
        List<Member> members = (List<Member>) memberService.findByPoliticalPartyId(p.getId());

        List<ReadMemberDto> memberDtos = ReadMemberDto.mapMembersListToReadMemberDto(members);

        return ResponseEntity.ok(memberDtos);
    }

    @DeleteMapping("/political-parties/{name}")
    public ResponseEntity<UUID> deletePartyByName(@PathVariable String name) {
        UUID id = politicalPartyService.deleteByName(name);
        if (id != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/political-parties/{id}")
    public ResponseEntity<Void> updatePartyByName(@PathVariable UUID id, @RequestBody CreateUpdatePoliticalPartyDto dto) {
        PoliticalParty updated =
                PoliticalParty.builder()
                        .id(id)
                        .name(dto.getName())
                        .dateOfEstablishment(LocalDateCreator.fromString(dto.getDateOfEstablishment()))
                        .build();
        politicalPartyService.create(updated);
        return ResponseEntity.created(URI.create("/political-parties/" + id)).build();
    }
}

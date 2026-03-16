package politicsapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import politicsapp.model.SimplifiedPoliticalParty;
import politicsapp.service.MemberService;
import politicsapp.service.SimplifiedPoliticalPartyService;
import politicsapp.web.dto.PoliticalPartyDto;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SimplifiedPoliticalPartyController {
    private final MemberService memberService;
    private final SimplifiedPoliticalPartyService simplifiedPoliticalPartyService;

    @GetMapping("/political-parties/{party_id}")
    public ResponseEntity<PoliticalPartyDto> getPoliticalParty(@PathVariable String party_id) {
        SimplifiedPoliticalParty found = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        PoliticalPartyDto dto = PoliticalPartyDto.builder().id(party_id).build();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/political-parties/{party_id}")
    public ResponseEntity<Void> createPoliticalParty(@PathVariable String party_id) {
        SimplifiedPoliticalParty p = SimplifiedPoliticalParty.builder().id(UUID.fromString(party_id)).build();
        simplifiedPoliticalPartyService.create(p);
        return ResponseEntity.created(URI.create("/political-parties/" + p.getId())).build();
    }

    @DeleteMapping("/political-parties/{party_id}")
    public ResponseEntity<Void> deletePoliticalParty(@PathVariable String party_id) {
        SimplifiedPoliticalParty found = simplifiedPoliticalPartyService.findById(UUID.fromString(party_id));
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        memberService.findByPoliticalPartyId(UUID.fromString(party_id)).forEach(
                member -> memberService.deleteById(member.getId())
        );
        simplifiedPoliticalPartyService.delete(UUID.fromString(party_id));
        return ResponseEntity.noContent().build();
    }
}

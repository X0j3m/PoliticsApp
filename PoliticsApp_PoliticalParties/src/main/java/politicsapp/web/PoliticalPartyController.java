package politicsapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import politicsapp.LocalDateCreator;
import politicsapp.event.PoliticalPartyCreatedEvent;
import politicsapp.event.PoliticalPartyDeletedEvent;
import politicsapp.model.PoliticalParty;
import politicsapp.web.dto.CreateUpdatePoliticalPartyDto;
import politicsapp.web.dto.ReadPoliticalPartyDto;
import politicsapp.service.PoliticalPartyService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PoliticalPartyController {
    private final PoliticalPartyService politicalPartyService;
    private final ApplicationEventPublisher publisher;

    @GetMapping("political-parties/{id}")
    public ResponseEntity<ReadPoliticalPartyDto> getPartyByName(@PathVariable String id) {
        PoliticalParty found = politicalPartyService.getById(UUID.fromString(id));

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

    @DeleteMapping("/political-parties/{id}")
    public ResponseEntity<UUID> deletePartyByName(@PathVariable String id) {
        UUID foundId = politicalPartyService.getById(UUID.fromString(id)).getId();

        if (foundId != null) {
            politicalPartyService.deleteById(UUID.fromString(id));
            publisher.publishEvent(
                    PoliticalPartyDeletedEvent.builder()
                            .id(foundId)
                            .build()
            );
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
        publisher.publishEvent(
                PoliticalPartyCreatedEvent.builder()
                        .id(id)
                        .build()
        );
        return ResponseEntity.created(URI.create("/political-parties/" + id)).build();
    }
}

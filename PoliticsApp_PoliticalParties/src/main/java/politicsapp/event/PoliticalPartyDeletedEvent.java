package politicsapp.event;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
@Builder
public class PoliticalPartyDeletedEvent {
    private final UUID id;
}

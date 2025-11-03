package politicsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ReadPoliticalPartyDto {
    private UUID id;
    private String name;
    private String dateOfEstablishment;
}

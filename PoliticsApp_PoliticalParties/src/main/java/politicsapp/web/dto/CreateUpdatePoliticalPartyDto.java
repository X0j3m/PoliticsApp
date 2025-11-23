package politicsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateUpdatePoliticalPartyDto {
    private String name;
    private String dateOfEstablishment;
}

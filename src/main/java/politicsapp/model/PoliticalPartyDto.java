package politicsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class PoliticalPartyDto {
    private String name;
    private LocalDate dateOfEstablishment;
    private int members;
}

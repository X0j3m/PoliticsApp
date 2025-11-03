package politicsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateUpdateMemberDto {
    private String name;
    private String surname;
    private String dateOfBirth;
    private String placeOfBirth;
    private int constituency;
    private String politicalParty;
}

package politicsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import politicsapp.model.Member;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ReadMemberDto{
    private UUID id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String placeOfBirth;
    private int constituency;
    private UUID politicalPartyId;

    public static List<ReadMemberDto> mapMembersListToReadMemberDto(List<Member> members) {
        return members.stream().map(m ->
                ReadMemberDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .surname(m.getSurname())
                        .dateOfBirth(m.getDateOfBirth().toString())
                        .placeOfBirth(m.getPlaceOfBirth())
                        .constituency(m.getConstituency())
                        .politicalPartyId(m.getSimplifiedPoliticalPartyId().getId())
                        .build()
        ).toList();
    }
}
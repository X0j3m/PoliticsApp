package politicsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class MemberDto implements Comparable<MemberDto> {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private int constituency;
    private String politicalParty;

    @Override
    public int compareTo(MemberDto o) {
        if (this.surname.compareTo(o.getSurname()) == 0) {
            if (this.name.compareTo(o.getName()) == 0) {
                if (this.dateOfBirth.isEqual(o.getDateOfBirth())) {
                    if (this.placeOfBirth.compareTo(o.getPlaceOfBirth()) == 0) {
                        if (this.constituency == o.getConstituency()) {
                            return this.politicalParty.compareTo(o.getPoliticalParty());
                        }
                        return this.constituency - o.getConstituency();
                    }
                    return this.placeOfBirth.compareTo(o.getPlaceOfBirth());
                }
                return this.dateOfBirth.compareTo(o.getDateOfBirth());
            }
            return this.name.compareTo(o.getName());
        }
        return this.surname.compareTo(o.getSurname());
    }
}
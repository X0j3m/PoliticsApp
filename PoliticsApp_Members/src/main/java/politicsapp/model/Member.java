package politicsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member implements Serializable {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "constituency")
    private int constituency;
    @ManyToOne
    @JoinColumn(name = "political_party_id")
    private SimplifiedPoliticalParty simplifiedPoliticalPartyId;
}

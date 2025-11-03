package politicsapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "political_party")
public class PoliticalParty implements Serializable {
    @Id
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "date_of_establishment")
    private LocalDate dateOfEstablishment;
    @ToString.Exclude
    @OneToMany(mappedBy = "politicalParty", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Member> members;
}
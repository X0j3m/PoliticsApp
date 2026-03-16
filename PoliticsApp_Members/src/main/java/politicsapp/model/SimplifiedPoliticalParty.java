package politicsapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
@Entity
@Table(name = "simplified-political-party")
public class SimplifiedPoliticalParty {
    @Id
    @Column(name = "id")
    private final UUID id;

    public static SimplifiedPoliticalParty of(String name) {
        return new SimplifiedPoliticalParty(UUID.nameUUIDFromBytes(name.getBytes()));
    }
}

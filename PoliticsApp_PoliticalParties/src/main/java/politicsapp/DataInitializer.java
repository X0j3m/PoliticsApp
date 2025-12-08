package politicsapp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import politicsapp.model.PoliticalParty;
//import politicsapp.service.MemberService;
import politicsapp.service.PoliticalPartyService;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final PoliticalPartyService politicalPartyService;

    @PostConstruct
    public void init() {
        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("prawo_i_sprawiedliwosc".getBytes()))
                        .name("Prawo i Sprawiedliwość")
                        .dateOfEstablishment(LocalDate.of(2001, 5, 29))
                        .build()
        );

        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("platforma_obywatelska".getBytes()))
                        .name("Platforma Obywatelska")
                        .dateOfEstablishment(LocalDate.of(2001, 1, 24))
                        .build()
        );

        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("nowa_nadzieja".getBytes()))
                        .name("Nowa Nadzieja")
                        .dateOfEstablishment(LocalDate.of(2015, 1, 22))
                        .build()
        );

        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("ruch_narodowy".getBytes()))
                        .name("Ruch Narodowy")
                        .dateOfEstablishment(LocalDate.of(2012, 11, 11))
                        .build()
        );

        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("razem".getBytes()))
                        .name("Razem")
                        .dateOfEstablishment(LocalDate.of(2015, 5, 16))
                        .build()
        );

        politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.nameUUIDFromBytes("nowa_lewica".getBytes()))
                        .name("Nowa Lewica")
                        .dateOfEstablishment(LocalDate.of(2021, 10, 9))
                        .build()
        );
    }
}

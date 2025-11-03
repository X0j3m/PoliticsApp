package politicsapp;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import politicsapp.model.Member;
import politicsapp.model.PoliticalParty;
import politicsapp.service.MemberService;
import politicsapp.service.PoliticalPartyService;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DataInitializer {
    private PoliticalPartyService politicalPartyService;
    private MemberService memberService;

    public DataInitializer(PoliticalPartyService politicalPartyService, MemberService memberService) {
        this.politicalPartyService = politicalPartyService;
        this.memberService = memberService;
    }

    @PostConstruct
    public void init() {
        UUID id;

        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Prawo_i_Sprawiedliwość")
                        .dateOfEstablishment(LocalDate.of(2001, 5, 29))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Jarosław")
                        .surname("Kaczyński")
                        .dateOfBirth(LocalDate.of(1949, 6, 18))
                        .placeOfBirth("Warszawa")
                        .constituency(33)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Mariusz")
                        .surname("Błaszczak")
                        .dateOfBirth(LocalDate.of(1969, 9, 19))
                        .placeOfBirth("Legionowo")
                        .constituency(20)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Joanna")
                        .surname("Borowiak")
                        .dateOfBirth(LocalDate.of(1967, 8, 20))
                        .placeOfBirth("Włocławek")
                        .constituency(5)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Przemysław")
                        .surname("Czarnek")
                        .dateOfBirth(LocalDate.of(1977, 6, 11))
                        .placeOfBirth("Koło")
                        .constituency(6)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Radosław")
                        .surname("Fogiel")
                        .dateOfBirth(LocalDate.of(1982, 2, 16))
                        .placeOfBirth("Radom")
                        .constituency(17)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Platforma_Obywatelska")
                        .dateOfEstablishment(LocalDate.of(2001, 1, 24))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Donald")
                        .surname("Tusk")
                        .dateOfBirth(LocalDate.of(1957, 4, 12))
                        .placeOfBirth("Gdańsk")
                        .constituency(19)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Andrzej")
                        .surname("Domański")
                        .dateOfBirth(LocalDate.of(1981, 8, 27))
                        .placeOfBirth("Kraków")
                        .constituency(19)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Roman")
                        .surname("Giertych")
                        .dateOfBirth(LocalDate.of(1971, 2, 27))
                        .placeOfBirth("Śrem")
                        .constituency(33)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Izabela")
                        .surname("Leszczyna")
                        .dateOfBirth(LocalDate.of(1962, 9, 3))
                        .placeOfBirth("Częstochowa")
                        .constituency(28)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Cezary")
                        .surname("Tomczyk")
                        .dateOfBirth(LocalDate.of(1984, 8, 25))
                        .placeOfBirth("Gryfice")
                        .constituency(11)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()

        );

        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Nowa_Nadzieja")
                        .dateOfEstablishment(LocalDate.of(2015, 1, 22))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Sławomir")
                        .surname("Mentzen")
                        .dateOfBirth(LocalDate.of(1986, 11, 20))
                        .placeOfBirth("Toruń")
                        .constituency(19)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Konrad")
                        .surname("Berkowicz")
                        .dateOfBirth(LocalDate.of(1984, 5, 27))
                        .placeOfBirth("Kraków")
                        .constituency(13)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Przemysław")
                        .surname("Wipler")
                        .dateOfBirth(LocalDate.of(1978, 7, 15))
                        .placeOfBirth("Pierkary_Śląskie")
                        .constituency(5)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Grzegorz")
                        .surname("Płaczek")
                        .dateOfBirth(LocalDate.of(1978, 5, 13))
                        .placeOfBirth("Tarnowskie_Góry")
                        .constituency(31)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Ryszard")
                        .surname("Wilk")
                        .dateOfBirth(LocalDate.of(1987, 3, 24))
                        .placeOfBirth("Krynica-Zdrój")
                        .constituency(14)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Ruch_Narodowy")
                        .dateOfEstablishment(LocalDate.of(2012, 11, 11))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Krzysztof")
                        .surname("Bosak")
                        .dateOfBirth(LocalDate.of(1982, 6, 13))
                        .placeOfBirth("Zielona_Góra")
                        .constituency(24)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Karina")
                        .surname("Bosak")
                        .dateOfBirth(LocalDate.of(1988, 7, 8))
                        .placeOfBirth("Szczecin")
                        .constituency(20)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Krzysztof")
                        .surname("Tuduj")
                        .dateOfBirth(LocalDate.of(1981, 4, 25))
                        .placeOfBirth("Warszawa")
                        .constituency(3)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );


        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Michał")
                        .surname("Wawer")
                        .dateOfBirth(LocalDate.of(1989, 4, 1))
                        .placeOfBirth("Warszawa")
                        .constituency(33)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Witold")
                        .surname("Tumanowicz")
                        .dateOfBirth(LocalDate.of(1986, 11, 4))
                        .placeOfBirth("Lublin")
                        .constituency(7)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Razem")
                        .dateOfEstablishment(LocalDate.of(2015, 5, 16))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Adrian")
                        .surname("Zandberg")
                        .dateOfBirth(LocalDate.of(1979, 12, 4))
                        .placeOfBirth("Aalborg")
                        .constituency(19)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Marcelina")
                        .surname("Zawisza")
                        .dateOfBirth(LocalDate.of(1989, 5, 3))
                        .placeOfBirth("Katowice")
                        .constituency(21)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Marta")
                        .surname("Stożek")
                        .dateOfBirth(LocalDate.of(1976, 8, 5))
                        .placeOfBirth("Wrocław")
                        .constituency(3)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Paulina")
                        .surname("Matysiak")
                        .dateOfBirth(LocalDate.of(1984, 10, 2))
                        .placeOfBirth("Kutno")
                        .constituency(11)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Maciej")
                        .surname("Konieczny")
                        .dateOfBirth(LocalDate.of(1980, 10, 14))
                        .placeOfBirth("Gliwice")
                        .constituency(31)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );


        id = politicalPartyService.create(
                PoliticalParty.builder()
                        .id(UUID.randomUUID())
                        .name("Nowa_Lewica")
                        .dateOfEstablishment(LocalDate.of(2021, 10, 9))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Włodzimierz")
                        .surname("Czarzasty")
                        .dateOfBirth(LocalDate.of(1960, 5, 3))
                        .placeOfBirth("Warszawa")
                        .constituency(32)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );

        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Agnieszka")
                        .surname("Dziemianowicz-Bąk")
                        .dateOfBirth(LocalDate.of(1984, 1, 20))
                        .placeOfBirth("Wrocław")
                        .constituency(26)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );


        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Katarzyna")
                        .surname("Kotula")
                        .dateOfBirth(LocalDate.of(1977, 2, 1))
                        .placeOfBirth("Gryfino")
                        .constituency(25)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );


        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Anna")
                        .surname("Żukowska")
                        .dateOfBirth(LocalDate.of(1983, 6, 11))
                        .placeOfBirth("Warszawa")
                        .constituency(19)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );


        memberService.create(
                Member.builder()
                        .id(UUID.randomUUID())
                        .name("Tomasz")
                        .surname("Trela")
                        .dateOfBirth(LocalDate.of(1979, 12, 30))
                        .placeOfBirth("Łódź")
                        .constituency(9)
                        .politicalParty(politicalPartyService.getById(id))
                        .build()
        );
    }
}

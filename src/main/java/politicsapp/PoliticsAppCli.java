package politicsapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import politicsapp.model.Member;
import politicsapp.model.MemberDto;
import politicsapp.model.PoliticalParty;
import politicsapp.model.PoliticalPartyDto;
import politicsapp.service.MemberService;
import politicsapp.service.PoliticalPartyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class PoliticsAppCli implements CommandLineRunner {
    private final DataInitializer dataInitializer;
    private final PoliticalPartyService politicalPartyService;
    private final MemberService memberService;


    public PoliticsAppCli(DataInitializer dataInitializer,
                          PoliticalPartyService politicalPartyService,
                          MemberService memberService) {
        this.dataInitializer = dataInitializer;
        this.politicalPartyService = politicalPartyService;
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("\n\n\n\n\n");
        dataInitializer.init();
        Scanner scanner = new Scanner(System.in);
        String command = "help";
        while (true) {
            try {
                if (command.startsWith("exit")) {
                    System.exit(0);
                } else if (command.startsWith("add")) {
                    String[] parts = command.split(" ");
                    if (parts[1].equals("party")) {
                        if (parts[2].equals("help")) {
                            System.out.println("add party <id>/random <name> <date_of_establishment>");
                        } else {
                            UUID id = idFromString(parts[2]);
                            PoliticalParty newParty = PoliticalParty.builder()
                                    .id(id)
                                    .name(parts[3])
                                    .dateOfEstablishment(stringToDate(parts[4]))
                                    .build();
                            politicalPartyService.save(newParty);
                            System.out.println("Party added");
                        }
                    } else if (parts[1].equals("member")) {
                        if (parts[2].equals("help")) {
                            System.out.println("add member <id>/random <name> <surname> <date_of_birth> <place_of_birth> <constituency> <party_name>");
                        } else {
                            UUID id = idFromString(parts[2]);
                            Member newMember = Member.builder()
                                    .id(id)
                                    .name(parts[3])
                                    .surname(parts[4])
                                    .dateOfBirth(stringToDate(parts[5]))
                                    .placeOfBirth(parts[6])
                                    .constituency(Integer.valueOf(parts[7]))
                                    .politicalParty(politicalPartyService.getByName(parts[8]))
                                    .build();
                            memberService.save(newMember);
                            System.out.println("Member added");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (command.startsWith("list")) {
                    String[] parts = command.split(" ");
                    if (parts[1].equals("help")) {
                        System.out.println("list parties - Prints all parties");
                        System.out.println("list members - Prints all members");
                        System.out.println("list parties dto - Prints all parties as DTO");
                        System.out.println("list members dto - Prints all members as DTO");
                    } else if (parts[1].equals("parties")) {
                        try {
                            if (parts[2].equals("dto")) {
                                List<PoliticalParty> parties = (List<PoliticalParty>) politicalPartyService.getAll();
                                parties.stream().map(p -> PoliticalPartyDto.builder()
                                        .name(p.getName())
                                        .dateOfEstablishment(p.getDateOfEstablishment())
                                        .members(p.getMembers().size())
                                        .build()
                                ).forEach(System.out::println);
                            } else {
                                System.out.println("Invalid command");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            try {
                                politicalPartyService.getAll().forEach(System.out::println);
                            } catch (Exception ex) {
                                System.out.println("Invalid command");
                            }
                        }
                    } else if (parts[1].equals("members")) {
                        try {
                            if (parts[2].equals("dto")) {
                                List<Member> members = (List<Member>) memberService.getAll();
                                members.stream().map(m -> MemberDto.builder()
                                        .name(m.getName())
                                        .surname(m.getSurname())
                                        .dateOfBirth(m.getDateOfBirth())
                                        .placeOfBirth(m.getPlaceOfBirth())
                                        .constituency(m.getConstituency())
                                        .politicalParty(m.getPoliticalParty().getName())
                                        .build()
                                ).forEach(System.out::println);

                            } else {
                                System.out.println("Invalid command");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            memberService.getAll().forEach(System.out::println);
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (command.startsWith("delete")) {
                    String[] parts = command.split(" ");
                    if (parts[1].equals("help")) {
                        System.out.println("delete party <name> - Deletes party with given name");
                        System.out.println("delete member <name> <surname> - Deletes member with given name and surname if there is only one");
                        System.out.println("delete member <id> - Deletes member with given id");
                    } else if (parts[1].equals("party")) {
                        if (parts[2].equals("help")) {
                            System.out.println("delete party <name>");
                        } else {
                            PoliticalParty found = politicalPartyService.getByName(parts[2]);
                            if (found != null) {
                                politicalPartyService.deleteById(found.getId());
                                System.out.println("Party deleted");
                            } else {
                                System.out.println("No party found with given name");
                            }
                        }
                    } else if (parts[1].equals("member")) {
                        if (parts[2].equals("help")) {
                            System.out.println("delete member <name> <surname> - Deletes member with given name and surname if there is only one");
                            System.out.println("delete member <id> - Deletes member with given id");
                        } else {
                            if (parts.length == 4) {
                                List<Member> found = (List<Member>) memberService.findByNameAndSurname(parts[2], parts[3]);
                                if (found.size() == 1) {
                                    memberService.deleteById(found.getFirst().getId());
                                    System.out.println("Member deleted");
                                } else if (found.size() > 1) {
                                    System.out.println("Multiple members found with given name and surname");
                                } else {
                                    System.out.println("No member found with given name and surname");
                                }
                            } else {
                                Member member = memberService.getById(idFromString(parts[2]));
                                if (member != null) {
                                    memberService.deleteById(member.getId());
                                    System.out.println("Member deleted");
                                } else {
                                    System.out.println("No member found with given id");
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (command.startsWith("help")) {
                    printHelp();
                } else {
                    System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Invalid command");
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
            command = scanner.nextLine();
        }
    }

    private static void printHelp() {
        System.out.println("---------------------------------------------------------------------------HELP---------------------------------------------------------------------------");
        System.out.println("add party <id>/random <name> <date_of_establishment> - Adds new party");
        System.out.println("add member <id>/random <name> <surname> <date_of_birth> <place_of_birth> <constituency> <party_name> - Adds new member");
        System.out.println("list parties - Prints all parties");
        System.out.println("list members - Prints all members");
        System.out.println("list parties dto - Prints all parties as DTO");
        System.out.println("list members dto - Prints all members as DTO");
        System.out.println("delete party <name> - Deletes party with given name");
        System.out.println("delete member <name> <surname> - Deletes member with given name and surname if there is only one");
        System.out.println("delete member <id> - Deletes member with given id");
        System.out.println("help - Prints this help");
        System.out.println("exit - Exits the application");
    }

    private LocalDate stringToDate(String date) {
        try {
            String[] parts = date.split("-");
            return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
        } catch (Exception e) {
            return null;
        }
    }

    private UUID idFromString(String stringId) {
        if (stringId.equals("random")) {
            return UUID.randomUUID();
        } else {
            return UUID.fromString(stringId);
        }
    }
}

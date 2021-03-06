package com.richard.tamingthymeleaf.application.init;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.google.common.collect.Streams;
import com.richard.tamingthymeleaf.domain.team.CreateTeamParameters;
import com.richard.tamingthymeleaf.domain.team.TeamPlayerParameters;
import com.richard.tamingthymeleaf.domain.team.TeamService;
import com.richard.tamingthymeleaf.domain.user.CreateUserParameters;
import com.richard.tamingthymeleaf.domain.user.UserService;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.PlayerPosition;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Email;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Gender;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.PhoneNumber;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.User;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@Profile("local")
public class DatabaseInitializer implements CommandLineRunner {

    private static final String[] TEAM_NAMES = new String[]{
            "Initiates",
            "Knights",
            "Padawans",
            "Rookies",
            "Wizards"
    };

    private final Faker faker = new Faker();
    private final UserService userService;
    private final TeamService teamService;

    public DatabaseInitializer(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) {

        Set<User> generatedUsers = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            CreateUserParameters parameters = newRandomUserParameters();
            generatedUsers.add(userService.createUser(parameters));
        }

        UserName userName = randomUserName();
        CreateUserParameters parameters = new CreateUserParameters(userName,
                userName.getFirstName(), // <.>
                randomGender(),
                LocalDate.parse("2000-01-01"),
                generateEmailForUserName(userName),
                randomPhoneNumber());

        userService.createAdministrator(parameters);

        userName = new UserName("Ricardo", "Viana");
        parameters = new CreateUserParameters(userName,
                "admin", // <.>
                randomGender(),
                LocalDate.parse("2000-01-01"),
                new Email("ricardo.viana@teste.com"),
                randomPhoneNumber());


        userService.createAdministrator(parameters);

        userName = new UserName("Nathalia", "Batista");
        parameters = new CreateUserParameters(userName,
                "admin", // <.>
                randomGender(),
                LocalDate.parse("2000-01-01"),
                new Email("nathalia.batista@teste.com"),
                randomPhoneNumber());


        userService.createAdministrator(parameters);


        Streams.forEachPair(generatedUsers.stream().limit(TEAM_NAMES.length),
                Arrays.stream(TEAM_NAMES),
                (user, teamName) -> {
                    Set<TeamPlayerParameters> players = Set.of(
                            new TeamPlayerParameters(randomUser(generatedUsers),
                                    PlayerPosition.SMALL_FORWARD),
                            new TeamPlayerParameters(randomUser(generatedUsers),
                                    PlayerPosition.SHOOTING_GUARD),
                            new TeamPlayerParameters(randomUser(generatedUsers),
                                    PlayerPosition.CENTER)
                    );

                    CreateTeamParameters teamParameters = new CreateTeamParameters(teamName,
                            user.getId(),
                            players);

                    if (!teamService.existsByName(teamParameters.getName())) {
                        teamService.createTeam(teamParameters);
                    }

                });

    }

    private UserId randomUser(Set<User> users) {
        int index = faker.random().nextInt(users.size());
        Iterator<User> iter = users.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next().getId();
    }

    private CreateUserParameters newRandomUserParameters() {
        UserName userName = randomUserName();
        Gender gender = randomGender();
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault());
        Email email = generateEmailForUserName(userName);
        PhoneNumber phoneNumber = randomPhoneNumber();
        return new CreateUserParameters(userName, userName.getFirstName(), gender, birthday, email, phoneNumber);
    }

    @Nonnull
    private UserName randomUserName() {
        Name name = faker.name();
        return new UserName(name.firstName(), name.lastName());
    }

    @Nonnull
    private PhoneNumber randomPhoneNumber() {
        return new PhoneNumber(faker.phoneNumber().phoneNumber());
    }

    @Nonnull
    private Email generateEmailForUserName(UserName userName) {
        return new Email(faker.internet().emailAddress(generateEmailLocalPart(userName)));
    }

    @Nonnull
    private Gender randomGender() {
        return faker.bool().bool() ? Gender.MALE : Gender.FEMALE;
    }

    @Nonnull
    private String generateEmailLocalPart(UserName userName) {
        return String.format("%s.%s",
                StringUtils.remove(userName.getFirstName().toLowerCase(), "'"),
                StringUtils.remove(userName.getLastName().toLowerCase(), "'"));
    }

}

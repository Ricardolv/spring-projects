package com.richard.tamingthymeleaf.domain.team;

import com.richard.tamingthymeleaf.application.exceptions.NotFoundException;
import com.richard.tamingthymeleaf.domain.user.UserService;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.Team;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamId;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamPlayer;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamRepository;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamSummary;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.User;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository repository;
    private final UserService userService;

    public TeamService(TeamRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Page<TeamSummary> getTeams(Pageable pageable) {
        return repository.findAllSummary(pageable);
    }

    public Team createTeam(CreateTeamParameters parameters) {

        String name = parameters.getName();
        User coach = getUser(parameters.getCoachId());
        LOGGER.info("Creating team {} with coach {} ({})", name, coach.getUserName().getFullName(), coach.getId());

        Team team = new Team(repository.nextId(), name, coach);
        parameters.getPlayers().forEach(player -> {
            team.addPlayer(new TeamPlayer(repository.nextPlayerId(), getUser(player.getPlayerId()), player.getPosition()));
        });

        return repository.save(team);
    }


    public Team editTeam(TeamId teamId, EditTeamParameters parameters) {
        Team team = getTeam(teamId)
                .orElseThrow(() -> new NotFoundException(String.format("Team with id %s not found", teamId.asString())));

        if (team.getVersion() != parameters.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(User.class, team.getId().asString());
        }

        team.setName(parameters.getName());
        team.setCoach(getUser(parameters.getCoachId()));
        team.setPlayers(parameters.getPlayers().stream()
                .map(teamPlayerParameters -> new TeamPlayer(repository.nextPlayerId(), getUser(teamPlayerParameters.getPlayerId()), teamPlayerParameters.getPosition()))
                .collect(Collectors.toSet()));

        return team;
    }

    public Optional<Team> getTeam(TeamId teamId) {
        return repository.findById(teamId);
    }

    public Optional<Team> getTeamWithPlayers(TeamId teamId) {
        return repository.findTeamWithPlayers(teamId);
    }

    public void deleteTeam(TeamId teamId) {
        repository.deleteById(teamId);
    }

    public void deleteAllTeams() {
        repository.deleteAll();
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    private User getUser(UserId userId) {
        return userService.getUser(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId.asString())));
    }

}

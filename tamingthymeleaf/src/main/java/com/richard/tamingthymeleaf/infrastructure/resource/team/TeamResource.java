package com.richard.tamingthymeleaf.infrastructure.resource.team;

import com.richard.tamingthymeleaf.application.exceptions.NotFoundException;
import com.richard.tamingthymeleaf.domain.team.TeamService;
import com.richard.tamingthymeleaf.domain.user.UserService;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.PlayerPosition;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.Team;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamId;
import com.richard.tamingthymeleaf.infrastructure.resource.EditMode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Nonnull;
import javax.validation.Valid;

@Controller
@RequestMapping("/teams")
public class TeamResource {

    private final TeamService service;
    private final UserService userService;

    public TeamResource(TeamService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new RemoveUnusedTeamPlayersValidator(binder.getValidator()));
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults(@SortDefault("name")) Pageable pageable) {

        model.addAttribute("teams", service.getTeams(pageable));

        return "teams/list";
    }

    @GetMapping("/create")
    @Secured("ROLE_ADMIN")
    public String createTeamForm(Model model) {

        model.addAttribute("team", new CreateTeamFormData());
        model.addAttribute("users", userService.getAllUsersNameAndId());
        model.addAttribute("positions", PlayerPosition.values());

        return "teams/edit";
    }

    @PostMapping("/create")
    @Secured("ROLE_ADMIN")
    public String doCreateTeam(@Valid @ModelAttribute("team") CreateTeamFormData formData,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", EditMode.CREATE);
            model.addAttribute("users", userService.getAllUsersNameAndId());
            model.addAttribute("positions", PlayerPosition.values());
            return "teams/edit";
        }

        service.createTeam(formData.toParameters());

        return "redirect:/teams";
    }

    @GetMapping("/{id}")
    public String editTeamForm(@PathVariable("id") TeamId teamId,
                               Model model) {
        Team team = service.getTeamWithPlayers(teamId)
                .orElseThrow(() -> new NotFoundException(String.format("Team with id %s not found", teamId.asString())));

        model.addAttribute("team", EditTeamFormData.fromTeam(team));
        model.addAttribute("users", userService.getAllUsersNameAndId());
        model.addAttribute("positions", PlayerPosition.values());
        model.addAttribute("editMode", EditMode.UPDATE);

        return "teams/edit";
    }

    @PostMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public String doEditTeam(@PathVariable("id") TeamId teamId,
                             @Valid @ModelAttribute("team") EditTeamFormData formData,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", EditMode.UPDATE);
            model.addAttribute("users", userService.getAllUsersNameAndId());
            model.addAttribute("positions", PlayerPosition.values());
            return "teams/edit";
        }

        service.editTeam(teamId,
                formData.toParameters());

        return "redirect:/teams";
    }

    @PostMapping("/{id}/delete")
    @Secured("ROLE_ADMIN")
    public String doDeleteTeam(@PathVariable("id") TeamId teamId,
                               RedirectAttributes redirectAttributes) {

        Team team = service.getTeam(teamId)
                .orElseThrow(() -> new NotFoundException(String.format("Team with id %s not found", teamId.asString())));

        service.deleteTeam(teamId);

        redirectAttributes.addFlashAttribute("deletedTeamName",
                team.getName());

        return "redirect:/teams";
    }

    @GetMapping("/edit-teamplayer-fragment")
    @Secured("ROLE_ADMIN")
    public String getEditTeamPlayerFragment(Model model,
                                            @RequestParam("index") int index) {
        model.addAttribute("index", index);
        model.addAttribute("users", userService.getAllUsersNameAndId());
        model.addAttribute("positions", PlayerPosition.values());
        model.addAttribute("teamObjectName", "dummyTeam");
        model.addAttribute("dummyTeam", new DummyTeamForTeamPlayerFragment());
        return "teams/edit-teamplayer-fragment :: teamplayer-form";
    }

    private static class DummyTeamForTeamPlayerFragment {
        private TeamPlayerFormData[] players;

        public TeamPlayerFormData[] getPlayers() {
            return players;
        }

        public void setPlayers(TeamPlayerFormData[] players) {
            this.players = players;
        }
    }

    private static class RemoveUnusedTeamPlayersValidator implements Validator {
        private final Validator validator;

        private RemoveUnusedTeamPlayersValidator(Validator validator) {
            this.validator = validator;
        }

        @Override
        public boolean supports(@Nonnull Class<?> clazz) {
            return validator.supports(clazz);
        }

        @Override
        public void validate(@Nonnull Object target, @Nonnull Errors errors) {

            if (target instanceof CreateTeamFormData) {
                CreateTeamFormData formData = (CreateTeamFormData) target;
                formData.removeEmptyTeamPlayerForms();
            }

            validator.validate(target, errors);
        }
    }

}

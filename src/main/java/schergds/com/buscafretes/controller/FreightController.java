package schergds.com.buscafretes.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import schergds.com.buscafretes.dto.FreightForm;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.User;
import schergds.com.buscafretes.entity.enums.StatusFrete;
import schergds.com.buscafretes.entity.enums.TipoCaminhao;
import schergds.com.buscafretes.service.FreightService;
import schergds.com.buscafretes.service.UserService;

@Controller
@RequestMapping("/freights")
public class FreightController {

    private final FreightService freightService;
    private final UserService userService;

    public FreightController(FreightService freightService, UserService userService) {
        this.freightService = freightService;
        this.userService = userService;
    }

    private User getAuthenticatedUser(Authentication auth) {
        if (auth == null) return null;
        return userService.findByEmail(auth.getName()).orElse(null);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Freight freight = freightService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("freight", freight);
        return "freight/detail";
    }

    @GetMapping("/my-freights")
    public String myFreights(Authentication auth, Model model) {
        User user = getAuthenticatedUser(auth);
        model.addAttribute("freights", freightService.findUserFreights(user));
        return "freight/my-freights";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("freightForm", new FreightForm());
        model.addAttribute("tiposCaminhao", TipoCaminhao.values());
        return "freight/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("freightForm") FreightForm form,
                         BindingResult result,
                         Authentication auth,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tiposCaminhao", TipoCaminhao.values());
            return "freight/form";
        }

        User user = getAuthenticatedUser(auth);
        Freight freight = form.toEntity(new Freight());
        freight.setUser(user);
        freightService.save(freight);

        return "redirect:/freights/my-freights";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Authentication auth, Model model) {
        Freight freight = freightService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = getAuthenticatedUser(auth);
        if (!freight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        model.addAttribute("freightForm", new FreightForm(freight));
        model.addAttribute("tiposCaminhao", TipoCaminhao.values());
        return "freight/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("freightForm") FreightForm form,
                       BindingResult result,
                       Authentication auth,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tiposCaminhao", TipoCaminhao.values());
            return "freight/form";
        }

        Freight freight = freightService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = getAuthenticatedUser(auth);
        if (!freight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        form.toEntity(freight);
        freightService.save(freight);

        return "redirect:/freights/my-freights";
    }

    @PostMapping("/{id}/finish")
    public String finish(@PathVariable Long id, Authentication auth) {
        Freight freight = freightService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = getAuthenticatedUser(auth);
        if (!freight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        freight.setStatus(StatusFrete.FINALIZADO);
        freightService.save(freight);

        return "redirect:/freights/my-freights";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Authentication auth) {
        Freight freight = freightService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = getAuthenticatedUser(auth);
        if (!freight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        freightService.delete(id);

        return "redirect:/freights/my-freights";
    }
}

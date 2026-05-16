package schergds.com.buscafretes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.enums.TipoCaminhao;
import schergds.com.buscafretes.service.FreightService;

@Controller
public class HomeController {

    private final FreightService freightService;

    public HomeController(FreightService freightService) {
        this.freightService = freightService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String origem,
            @RequestParam(required = false) String destino,
            @RequestParam(required = false) TipoCaminhao tipoCaminhao,
            @RequestParam(defaultValue = "0") int page,
            Model model,
            @RequestParam(value = "htmx", required = false) String htmxHeader) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        Page<Freight> freights = freightService.findAvailableFreights(origem, destino, tipoCaminhao, pageable);

        model.addAttribute("freights", freights);
        model.addAttribute("tiposCaminhao", TipoCaminhao.values());
        
        // Preserve filter parameters for pagination links
        model.addAttribute("origem", origem);
        model.addAttribute("destino", destino);
        model.addAttribute("tipoCaminhaoSel", tipoCaminhao);

        // If request comes from HTMX, return only the fragment
        if ("true".equals(htmxHeader)) {
            return "fragments/freight-list :: list";
        }

        return "home";
    }
}

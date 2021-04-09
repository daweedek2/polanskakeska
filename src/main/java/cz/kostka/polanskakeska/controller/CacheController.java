package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.CacheFormDTO;
import cz.kostka.polanskakeska.entity.Cache;
import cz.kostka.polanskakeska.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/cache")
public class CacheController {
    private final CacheService cacheService;

    @Autowired
    public CacheController(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping
    public String cacheOverview(final Model model) {
        model.addAttribute("allCaches", cacheService.getAllCaches());
        model.addAttribute("cacheFormDTO", new CacheFormDTO());
        return "admin-cache";
    }

    @GetMapping("/getAll")
    public List<Cache> getAll() {
        return cacheService.getAllCaches();
    }

    @PostMapping("/save")
    public String save(final @ModelAttribute("cacheFormDTO") CacheFormDTO cacheFormDTO,
                       final RedirectAttributes redirectAttributes) {
        Cache cache = cacheService.saveFromDTO(cacheFormDTO);
        if (cache != null) {
            redirectAttributes.addFlashAttribute("cacheCreated", true);
        } else {
            redirectAttributes.addFlashAttribute("cacheCreated", false);
        }

        return "redirect:/admin/cache";
    }
}

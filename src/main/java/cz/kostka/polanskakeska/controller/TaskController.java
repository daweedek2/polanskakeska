package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.CacheValidationDTO;
import cz.kostka.polanskakeska.service.CacheService;
import cz.kostka.polanskakeska.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class TaskController {
    public static final String CACHE_VALIDATION_DTO_ATTR = "cacheValidationDTO";
    public static final String CACHE_NUMBERS_ATTR = "caches";
    private final ValidationService validationService;
    private final CacheService cacheService;

    @Autowired
    public TaskController(final ValidationService validationService, final CacheService cacheService) {
        this.validationService = validationService;
        this.cacheService = cacheService;
    }

    @GetMapping
    public String view(final Model model) {
        model.addAttribute(CACHE_NUMBERS_ATTR, cacheService.getAllCachesNumbers());
        model.addAttribute(CACHE_VALIDATION_DTO_ATTR, new CacheValidationDTO());
        return "cache";
    }

    @PostMapping("/verify")
    public String verify(final @ModelAttribute CacheValidationDTO cacheValidationDTO,
                         final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(CACHE_VALIDATION_DTO_ATTR, cacheValidationDTO);
        redirectAttributes.addFlashAttribute("result", validationService.verify(cacheValidationDTO));
        return "redirect:/";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
}

package cz.kostka.polanskakeska.controller;

import cz.kostka.polanskakeska.dto.ResultDTO;
import cz.kostka.polanskakeska.dto.ValidationDTO;
import cz.kostka.polanskakeska.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:65301", maxAge = 3600)
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(final ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/verify")
    public ResultDTO verifyAnswer(final @RequestBody ValidationDTO validationDTO) {
        return validationService.verify(validationDTO);
    }
}

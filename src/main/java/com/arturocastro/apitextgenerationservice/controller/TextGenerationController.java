package com.arturocastro.apitextgenerationservice.controller;

import com.arturocastro.apitextgenerationservice.model.TGModel;
import com.arturocastro.apitextgenerationservice.service.TextGenerationService;
import com.openai.models.responses.ResponseOutputItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai/text-generation")
public class TextGenerationController {

    private final TextGenerationService textGenerationService;

    private TextGenerationController(TextGenerationService textGenerationService) {
        this.textGenerationService = textGenerationService;
    }

    @PostMapping
    public ResponseEntity<ResponseOutputItem> getQuestion(@RequestBody TGModel tgm){
        return ResponseEntity.ok(textGenerationService.getQuestion(tgm).output().getFirst());
    }

}

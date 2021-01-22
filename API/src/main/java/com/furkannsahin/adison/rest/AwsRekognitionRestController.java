package com.furkannsahin.adison.rest;

import com.furkannsahin.adison.service.AwsRekognitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AwsRekognitionRestController {

    private AwsRekognitionService awsRekognitionService;

    public AwsRekognitionRestController(AwsRekognitionService awsRekognitionService) {
        this.awsRekognitionService = awsRekognitionService;
    }

    @PostMapping("/images/detect-labels")
    public Object detectLabels(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(awsRekognitionService.detectLabels(image));
    }

    @PostMapping("/images/detect-texts")
    public Object detectTexts(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(awsRekognitionService.detectTexts(image));
    }

}
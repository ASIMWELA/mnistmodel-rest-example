package com.mnict.model.demo.modelservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ModelServiceController {
    private final ModelService modelService;
    @PostMapping("/detect")
    public void detect(@RequestParam("file") MultipartFile imageFile){
        modelService.detect(imageFile);
    }
}

package com.mnict.model.demo.modelservice;

import org.springframework.web.multipart.MultipartFile;

public interface ModelService {
    void detect(MultipartFile multipartFile);
}

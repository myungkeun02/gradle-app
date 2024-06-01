package org.myungkeun.app_gradle;

import lombok.RequiredArgsConstructor;
import org.myungkeun.app_gradle.redis.service.MinioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class MinioController {
    private final MinioServiceImpl minioService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            minioService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while uploading file: " + e.getMessage());
        }
    }
}

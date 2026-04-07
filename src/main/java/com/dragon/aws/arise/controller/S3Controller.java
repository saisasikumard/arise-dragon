package com.dragon.aws.arise.controller;

import com.dragon.aws.arise.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Request received to upload file: {}", file.getOriginalFilename());
        try {
            String fileName = s3Service.uploadFile(file);
            log.info("File uploaded successfully. Assigned name: {}", fileName);
            return ResponseEntity.ok("File uploaded successfully. File name: " + fileName);
        } catch (Exception e) {
            log.error("Error occurred while uploading file: {}. Error: {}", file.getOriginalFilename(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> fetchFile(@PathVariable("fileName") String fileName) {
        log.info("Request received to download file: {}", fileName);
        try {
            byte[] fileBytes = s3Service.fetchFile(fileName);
            log.info("File fetched successfully: {}", fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while downloading file: {}. Error: {}", fileName, e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to download file. Please try again."));
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable("fileName") String fileName) {
        log.info("Request received to delete file: {}", fileName);
        try {
            s3Service.deleteFile(fileName);
            log.info("File deleted successfully: {}", fileName);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            log.error("Error occurred while deleting file: {}. Error: {}", fileName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting file: " + e.getMessage());
        }
    }
}

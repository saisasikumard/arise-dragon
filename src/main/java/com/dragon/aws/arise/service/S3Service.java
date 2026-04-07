package com.dragon.aws.arise.service;

import com.dragon.aws.arise.exception.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

@Slf4j
@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        log.info("Starting upload of file to S3. Bucket: {}, Generated Key: {}", bucketName, fileName);
        
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();
                
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        log.info("File upload to S3 completed successfully. Key: {}", fileName);
        
        return fileName;
    }

    public byte[] fetchFile(String fileName) {
        log.info("Initiating file fetch from S3. Bucket: {}, Key: {}", bucketName, fileName);
        
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try {
            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
            log.info("Successfully retrieved file bytes for Key: {}. Size: {} bytes", fileName, objectBytes.asByteArray().length);
            return objectBytes.asByteArray();
        } catch (NoSuchKeyException e) {
            log.error("File not found in S3: {}. Exception: {}", fileName, e.getMessage());
            throw new FileNotFoundException("No file exist with this name", e);

        } catch (Exception e) {
            log.error("Error retrieving file from S3: {}. Error: {}", fileName, e.getMessage());
            throw e;
        }
    }

    public void deleteFile(String fileName) {
        log.info("Requesting file deletion from S3. Bucket: {}, Key: {}", bucketName, fileName);
        
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
                
        s3Client.deleteObject(deleteObjectRequest);
        log.info("File deletion from S3 requested for Key: {}", fileName);
    }
}

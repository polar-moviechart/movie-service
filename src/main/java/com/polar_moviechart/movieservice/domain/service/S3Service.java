package com.polar_moviechart.movieservice.domain.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.polar_moviechart.movieservice.domain.service.dtos.HasThumbnail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${spring.profiles.active}")
    private String activeProfile;
    private final AmazonS3Client s3Client;

    public <T extends HasThumbnail> void setS3ImageUrl(List<T> response) {
        for(T item : response) {
            String presignedUrl = generatePresignedUrl(item.getThumbnailUrl());
            item.setThumbnailUrl(presignedUrl);
        }
    }

    public <T extends HasThumbnail> void setS3ImageUrl(T response) {
            String presignedUrl = generatePresignedUrl(response.getThumbnailUrl());
            response.setThumbnailUrl(presignedUrl);
    }

    private String generatePresignedUrl(String imagePath) {
        if (imagePath == null) {
            return null;
        }
        if (!activeProfile.equals("prod")) {
            return null;
        }
        // Presigned URL 생성
        Date expiration = new Date();
        long expTimeMillis = System.currentTimeMillis() + 1000 * 60 * 60; // 1시간 후 만료
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, imagePath)
                .withMethod(com.amazonaws.HttpMethod.GET)
                .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString(); // 생성된 Presigned URL 반환
    }
}
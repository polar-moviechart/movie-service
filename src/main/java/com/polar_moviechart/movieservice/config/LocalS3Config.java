package com.polar_moviechart.movieservice.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local | test")
public class LocalS3Config {

    @Bean
    public AmazonS3Client amazonS3Client() {
        AmazonS3Client mockAmazonS3 = Mockito.mock(AmazonS3Client.class);
        Mockito.when(mockAmazonS3.generatePresignedUrl(Mockito.any(GeneratePresignedUrlRequest.class)))
               .thenReturn(null);  // 로컬에서는 URL 생성하지 않음
        return mockAmazonS3;
    }
}

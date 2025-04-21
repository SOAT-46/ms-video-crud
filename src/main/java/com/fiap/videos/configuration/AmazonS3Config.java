package com.fiap.videos.configuration;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonS3Config {

    @Value("${aws.s3.endpoint}")
    private String awsS3EndPoint;

    @Value("${aws.credentials.access-key}")
    protected String awsAccessKey;

    @Value("${aws.credentials.secret-key}")
    protected String awsSecretKey;

    @Value("${aws.region}")
    protected String awsRegion;

    protected AwsCredentialsProvider amazonAWSCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKey, awsSecretKey));
    }

    @Bean
    public S3Client s3() {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(amazonAWSCredentialsProvider())
                .endpointOverride(URI.create(awsS3EndPoint))
                .build();
    }

}
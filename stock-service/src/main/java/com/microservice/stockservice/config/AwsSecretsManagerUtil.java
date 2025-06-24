package com.microservice.stockservice.config;


import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.*;

@Component
public class AwsSecretsManagerUtil {

    public String getSecret(String secretName) {
        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.EU_NORTH_1) // Set your region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build()) {

            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse response = client.getSecretValue(getSecretValueRequest);
            return response.secretString();

        } catch (SecretsManagerException e) {
            throw new RuntimeException("Unable to fetch secret", e);
        }
    }
}

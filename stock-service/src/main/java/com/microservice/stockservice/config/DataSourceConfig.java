package com.microservice.stockservice.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private AwsSecretsManagerUtil secretsManagerUtil;
    @Value("${aws.secrets.rds.name}")
    private String secretName;


    @Bean
    public DataSource dataSource() {
        try {
            String secretJson = secretsManagerUtil.getSecret(secretName); // Replace with actual secret name
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(secretJson);

            String host = jsonNode.get("host").asText();
            String port = jsonNode.get("port").asText();
            String dbname = jsonNode.get("dbname").asText();
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();

            String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbname);


            HikariDataSource dataSource = new HikariDataSource();

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 👇 Print debug information
            System.out.println("🔐 URL: " + url);
            System.out.println("🔐 Username: " + username);
            System.out.println("🔐 Driver: com.mysql.cj.jdbc.Driver");

            dataSource.setJdbcUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return dataSource;

        } catch (SecretsManagerException e) {
            throw new RuntimeException("Failed to load DB credentials from Secrets Manager", e);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing DB secret", e);
        }
    }
}

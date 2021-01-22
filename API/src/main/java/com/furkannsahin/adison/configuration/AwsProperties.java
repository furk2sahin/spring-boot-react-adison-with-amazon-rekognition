package com.furkannsahin.adison.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("aws")
@Component
public class AwsProperties {

    private String accessKey;

    private String secretKey;
}
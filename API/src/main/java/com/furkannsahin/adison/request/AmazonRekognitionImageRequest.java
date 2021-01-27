package com.furkannsahin.adison.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonRekognitionImageRequest {
    private String base64Image;
}

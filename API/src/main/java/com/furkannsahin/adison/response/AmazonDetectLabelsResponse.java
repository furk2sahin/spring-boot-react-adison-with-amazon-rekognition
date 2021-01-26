package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonDetectLabelsResponse {
    private DetectLabelsResult detectLabelsResult;
}

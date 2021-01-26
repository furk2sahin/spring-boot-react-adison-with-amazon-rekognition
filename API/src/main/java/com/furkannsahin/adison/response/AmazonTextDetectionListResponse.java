package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.TextDetection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonTextDetectionListResponse {
    private List<TextDetection> textDetectionList;
}

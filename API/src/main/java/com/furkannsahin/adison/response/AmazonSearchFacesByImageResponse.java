package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonSearchFacesByImageResponse {
    private SearchFacesByImageResult searchFacesByImageResult;
}

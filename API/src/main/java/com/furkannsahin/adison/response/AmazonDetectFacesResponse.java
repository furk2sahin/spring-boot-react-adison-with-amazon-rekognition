package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.DetectFacesResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonDetectFacesResponse {
    private DetectFacesResult detectFacesResult;
}

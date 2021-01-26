package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.IndexFacesResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonIndexFacesResponse {
    private IndexFacesResult indexFacesResult;
}

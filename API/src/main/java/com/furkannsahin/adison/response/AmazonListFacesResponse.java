package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.ListFacesResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonListFacesResponse {
    private ListFacesResult listFacesResult;
}

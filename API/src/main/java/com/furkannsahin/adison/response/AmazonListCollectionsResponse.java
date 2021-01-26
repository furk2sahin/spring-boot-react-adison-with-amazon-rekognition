package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.ListCollectionsResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonListCollectionsResponse {
    private ListCollectionsResult listCollectionsResult;
}

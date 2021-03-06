package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.DescribeCollectionResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonDescribeCollectionResponse {
    private DescribeCollectionResult describeCollectionResult;
}

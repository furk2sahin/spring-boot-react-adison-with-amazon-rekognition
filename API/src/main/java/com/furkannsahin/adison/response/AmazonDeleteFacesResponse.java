package com.furkannsahin.adison.response;

import com.amazonaws.services.rekognition.model.DeleteFacesResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmazonDeleteFacesResponse {
    private DeleteFacesResult deleteFacesResult;
}

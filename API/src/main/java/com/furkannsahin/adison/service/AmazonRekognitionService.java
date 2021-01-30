package com.furkannsahin.adison.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AmazonRekognitionService {
    DetectLabelsResult detectLabels(String base64Image);
    List<TextDetection> detectTexts(String base64Image);
    DetectFacesResult detectFaces(String base64Image);
    ListCollectionsResult listCollections();
    CreateCollectionResult createCollection(String collectionName);
    DeleteCollectionResult deleteCollection(String collectionName);
    DescribeCollectionResult describeCollection(String collectionName);
    IndexFacesResult addFacesToCollection(String base64Image, String collectionId, Long userId);
    ListFacesResult listFacesInCollection(String collectionId);
    DeleteFacesResult deleteFacesInCollection(String collectionId, String faceId);
    SearchFacesByImageResult searchFacesInCollection(String collectionId, String base64Image);
    Integer getFaceCount(String base64Image); // if returns 1 we can save this person in collection with user id
    Long getMatchedFaceUserId(String collectionId, String base64Image); // returns matched user's id
    String getFaceIdByUserId(String collectionId, Long userId); // return face's id with matched user id
}
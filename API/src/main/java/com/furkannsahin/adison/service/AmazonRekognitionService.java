package com.furkannsahin.adison.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface AmazonRekognitionService {
    DetectLabelsResult detectLabels(MultipartFile image) throws IOException;
    List<TextDetection> detectTexts(MultipartFile image) throws IOException;
    DetectFacesResult detectFaces(MultipartFile image) throws IOException;
    ListCollectionsResult listCollections();
    CreateCollectionResult createCollection(String collectionName);
    DeleteCollectionResult deleteCollection(String collectionName);
    DescribeCollectionResult describeCollection(String collectionName);
    IndexFacesResult addFacesToCollection(MultipartFile image, String collectionId, Long userId) throws IOException;
    ListFacesResult listFacesInCollection(String collectionId);
    DeleteFacesResult deleteFacesInCollection(String collectionId, String faceId);
    SearchFacesByImageResult searchFacesInCollection(String collectionId, Image image);
    Integer getFaceCount(MultipartFile image) throws IOException; // if returns 1 we can save this person in collection with user id
    Long getMatchedFaceUserId(String collectionId, MultipartFile image) throws IOException; // returns matched user's id
    String getFaceIdByUserId(String collectionId, Long userId); // return face's id with matched user id
}
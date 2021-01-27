package com.furkannsahin.adison.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.furkannsahin.adison.service.AmazonRekognitionService;
import com.furkannsahin.adison.util.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class AmazonRekognitionServiceImpl  implements AmazonRekognitionService {
    private AmazonRekognition amazonClient;
    private ImageConverter imageConverter = new ImageConverter();

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public DetectLabelsResult detectLabels(String base64Image) {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(imageConverter.base64ToImage(base64Image));

        return amazonClient.detectLabels(request);
    }

    @Override
    public List<TextDetection> detectTexts(String base64Image) {
        DetectTextRequest request = new DetectTextRequest()
                .withImage(imageConverter.base64ToImage(base64Image));
        DetectTextResult result = amazonClient.detectText(request);
        List<TextDetection> textDetections = result.getTextDetections();
        return textDetections;
    }

    @Override
    public DetectFacesResult detectFaces(String base64Image){
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image))
                .withAttributes(Attribute.ALL);

        return amazonClient.detectFaces(request);
    }

    // Collection operations

    @Override
    public ListCollectionsResult listCollections() {
        ListCollectionsRequest request = new ListCollectionsRequest()
                .withMaxResults(100);

        return amazonClient.listCollections(request);
    }

    @Override
    public CreateCollectionResult createCollection(String collectionName) {
        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionName);

        return amazonClient.createCollection(request);
    }

    @Override
    public DeleteCollectionResult deleteCollection(String collectionName) {
        DeleteCollectionRequest request = new DeleteCollectionRequest()
                .withCollectionId(collectionName);

        return amazonClient.deleteCollection(request);
    }

    @Override
    public DescribeCollectionResult describeCollection(String collectionName) {
        DescribeCollectionRequest request = new DescribeCollectionRequest()
                .withCollectionId(collectionName);

        return amazonClient.describeCollection(request);
    }

    @Override
    public IndexFacesResult addFacesToCollection(String base64Image, String collectionId, Long userId){
        IndexFacesRequest request = new IndexFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image))
                .withQualityFilter(QualityFilter.AUTO)
                .withExternalImageId(userId.toString())
                .withCollectionId(collectionId)
                .withDetectionAttributes("ALL");
        return amazonClient.indexFaces(request);
    }

    @Override
    public ListFacesResult listFacesInCollection(String collectionId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);
        return amazonClient.listFaces(request);
    }

    @Override
    public DeleteFacesResult deleteFacesInCollection(String collectionId, String faceId){
        String[] faces = {faceId};
        DeleteFacesRequest request = new DeleteFacesRequest()
                .withCollectionId(collectionId)
                .withFaceIds(faces);
        return amazonClient.deleteFaces(request);
    }

    @Override
    public SearchFacesByImageResult searchFacesInCollection(String collectionId, String base64Image) {
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withQualityFilter(QualityFilter.AUTO)
                .withCollectionId(collectionId)
                .withImage(imageConverter.base64ToImage(base64Image))
                .withFaceMatchThreshold(70F);
        SearchFacesByImageResult result = amazonClient.searchFacesByImage(request);
        return result;
    }

    @Override
    public Integer getFaceCount(String base64Image) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image));

        DetectFacesResult result = amazonClient.detectFaces(request);
        return result.getFaceDetails().size();
    }

    @Override
    public Long getMatchedFaceUserId(String collectionId, String base64Image) {
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withQualityFilter(QualityFilter.AUTO)
                .withCollectionId(collectionId)
                .withImage(imageConverter.base64ToImage(base64Image))
                .withFaceMatchThreshold(70F)
                .withMaxFaces(1);
        SearchFacesByImageResult result = amazonClient.searchFacesByImage(request);
        List<FaceMatch> faces = result.getFaceMatches();
        if(faces.isEmpty())
            return 0L;
        else
            return Long.parseLong(result.getFaceMatches().get(0).getFace().getExternalImageId());
    }

    @Override
    public String getFaceIdByUserId(String collectionId, Long userId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);
        ListFacesResult result = amazonClient.listFaces(request);
        List<Face> faces = result.getFaces();
        for(Face face : faces){
            if(Long.parseLong(face.getExternalImageId()) == userId){
                return face.getFaceId();
            }
        }
        return null;
    }
}

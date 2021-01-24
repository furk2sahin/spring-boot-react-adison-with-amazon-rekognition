package com.furkannsahin.adison.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.furkannsahin.adison.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class AmazonRekognitionServiceImpl  implements AmazonRekognitionService {
    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public DetectLabelsResult detectLabels(MultipartFile image) throws IOException {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));

        return amazonClient.detectLabels(request);
    }

    @Override
    public List<TextDetection> detectTexts(MultipartFile image) throws IOException {
        DetectTextRequest request = new DetectTextRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
        DetectTextResult result = amazonClient.detectText(request);
        List<TextDetection> textDetections = result.getTextDetections();
        return textDetections;
    }

    @Override
    public DetectFacesResult detectFaces(MultipartFile image) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
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
    public IndexFacesResult addFacesToCollection(MultipartFile image, String collectionId, Long userId) throws IOException {
        IndexFacesRequest request = new IndexFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
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
    public SearchFacesByImageResult searchFacesInCollection(String collectionId, Image image) {
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withQualityFilter(QualityFilter.AUTO)
                .withCollectionId(collectionId)
                .withImage(image)
                .withFaceMatchThreshold(70F);
        SearchFacesByImageResult result = amazonClient.searchFacesByImage(request);
        return result;
    }

    @Override
    public Integer getFaceCount(MultipartFile image) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));

        DetectFacesResult result = amazonClient.detectFaces(request);
        return result.getFaceDetails().size();
    }

    @Override
    public Long getMatchedFaceUserId(String collectionId, MultipartFile image) throws IOException{
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withQualityFilter(QualityFilter.AUTO)
                .withCollectionId(collectionId)
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
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

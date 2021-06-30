package com.furkannsahin.adison.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.furkannsahin.adison.service.AmazonRekognitionService;
import com.furkannsahin.adison.util.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonRekognitionServiceImpl  implements AmazonRekognitionService {

    private ImageConverter imageConverter = new ImageConverter();

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public List<TextDetection> detectTexts(String base64Image) {
        DetectTextRequest request = new DetectTextRequest()
                .withImage(imageConverter.base64ToImage(base64Image));
        List<TextDetection> textDetections;

        try{
            DetectTextResult detectTextResult = amazonClient.detectText(request);
            textDetections = detectTextResult.getTextDetections();
        } catch (AmazonRekognitionException e){
            return null;
        }
        return textDetections;
    }

    @Override
    public DetectLabelsResult detectLabels(String base64Image) {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(imageConverter.base64ToImage(base64Image));

        DetectLabelsResult detectLabelsResult;

        try{
            detectLabelsResult = amazonClient.detectLabels(request);
        } catch (AmazonRekognitionException e){
            return null;
        }
        return detectLabelsResult;
    }



    @Override
    public DetectFacesResult detectFaces(String base64Image){
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image))
                .withAttributes(Attribute.ALL);

        DetectFacesResult detectFacesResult;

        try{
            detectFacesResult = amazonClient.detectFaces(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return detectFacesResult;
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
        CreateCollectionResult createCollectionResult;
        try{
            createCollectionResult = amazonClient.createCollection(request);
        } catch (ResourceAlreadyExistsException e){
            return null;
        }
        return createCollectionResult;
    }

    @Override
    public DeleteCollectionResult deleteCollection(String collectionName) {
        DeleteCollectionRequest request = new DeleteCollectionRequest()
                .withCollectionId(collectionName);

        DeleteCollectionResult  deleteCollectionResult;

        try{
            deleteCollectionResult = amazonClient.deleteCollection(request);
        } catch (ResourceNotFoundException e){
            return null;
        }

        return deleteCollectionResult;
    }

    @Override
    public DescribeCollectionResult describeCollection(String collectionName) {
        DescribeCollectionRequest request = new DescribeCollectionRequest()
                .withCollectionId(collectionName);

        DescribeCollectionResult describeCollectionResult;

        try{
            describeCollectionResult = amazonClient.describeCollection(request);
        } catch (ResourceNotFoundException e){
            return null;
        }

        return describeCollectionResult;
    }

    @Override
    public IndexFacesResult addFacesToCollection(String base64Image, String collectionId, Long userId){
        IndexFacesRequest request = new IndexFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image))
                .withQualityFilter(QualityFilter.AUTO)
                .withExternalImageId(userId.toString())
                .withCollectionId(collectionId)
                .withDetectionAttributes("ALL");

        IndexFacesResult indexFacesResult;

        try{
            indexFacesResult = amazonClient.indexFaces(request);
        } catch (ResourceNotFoundException e){
            return null;
        }
        catch (AmazonRekognitionException e){
            return null;
        }

        return indexFacesResult;
    }

    @Override
    public ListFacesResult listFacesInCollection(String collectionId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);

        ListFacesResult listFacesResult;

        try{
            listFacesResult = amazonClient.listFaces(request);
        } catch (ResourceNotFoundException e){
            return null;
        }
        return listFacesResult;
    }

    @Override
    public DeleteFacesResult deleteFacesInCollection(String collectionId, String faceId){
        String[] faces = {faceId};
        DeleteFacesRequest request = new DeleteFacesRequest()
                .withCollectionId(collectionId)
                .withFaceIds(faces);

        DeleteFacesResult deleteFacesResult;

        try{
            deleteFacesResult = amazonClient.deleteFaces(request);
        } catch (ResourceNotFoundException e){
            return null;
        }
        catch (AmazonRekognitionException e){
            return null;
        }

        return deleteFacesResult;
    }

    @Override
    public SearchFacesByImageResult searchFacesInCollection(String collectionId, String base64Image) {
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
            .withQualityFilter(QualityFilter.AUTO)
            .withCollectionId(collectionId)
            .withImage(imageConverter.base64ToImage(base64Image))
            .withFaceMatchThreshold(70F);

        SearchFacesByImageResult searchFacesByImageResult;
        try{
            searchFacesByImageResult = amazonClient.searchFacesByImage(request);
        } catch (ResourceNotFoundException e){
            return null;
        } catch (AmazonRekognitionException e){
            return null;
        }
        return searchFacesByImageResult;
    }

    @Override
    public Integer getFaceCount(String base64Image) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(imageConverter.base64ToImage(base64Image));

        int faceCount;

        try{
            DetectFacesResult result = amazonClient.detectFaces(request);
            faceCount = result.getFaceDetails().size();
        } catch (AmazonRekognitionException e){
            return 0;
        }

        return faceCount;
    }

    @Override
    public Integer getModerationLabelsCount(String base64Image) {
        DetectModerationLabelsRequest request = new DetectModerationLabelsRequest()
                .withImage(imageConverter.base64ToImage(base64Image));

        int faceCount;

        try{
            DetectModerationLabelsResult result = amazonClient.detectModerationLabels(request);
            faceCount = result.getModerationLabels().size();
        } catch (AmazonRekognitionException e){
            return 0;
        }

        return faceCount;
    }

    @Override
    public Long getMatchedFaceUserId(String collectionId, String base64Image) {
        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withQualityFilter(QualityFilter.AUTO)
                .withCollectionId(collectionId)
                .withImage(imageConverter.base64ToImage(base64Image))
                .withFaceMatchThreshold(70F)
                .withMaxFaces(1);

        List<FaceMatch> faces;

        try{
            SearchFacesByImageResult result = amazonClient.searchFacesByImage(request);
            faces = result.getFaceMatches();
        } catch (ResourceNotFoundException e){
            return 0L;
        } catch (AmazonRekognitionException e){
            return 0L;
        }

        if(faces.isEmpty())
            return 0L;
        else
            return Long.parseLong(faces.get(0).getFace().getExternalImageId());
    }

    @Override
    public boolean compareFaces(String sourceBase64Image, String targetBase64Image) {
        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(imageConverter.base64ToImage(sourceBase64Image))
                .withTargetImage(imageConverter.base64ToImage(targetBase64Image));
        try{
            CompareFacesResult result = amazonClient.compareFaces(request);
            return !result.getFaceMatches().isEmpty();
        } catch (AmazonRekognitionException e){
            return false;
        }
    }

    @Override
    public String getFaceIdByUserId(String collectionId, Long userId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);

        List<Face> faces;

        try{
            ListFacesResult result = amazonClient.listFaces(request);
            faces = result.getFaces();
        } catch (ResourceNotFoundException e){
            return null;
        }

        for(Face face : faces){
            if(Long.parseLong(face.getExternalImageId()) == userId){
                return face.getFaceId();
            }
        }
        return null;
    }
}

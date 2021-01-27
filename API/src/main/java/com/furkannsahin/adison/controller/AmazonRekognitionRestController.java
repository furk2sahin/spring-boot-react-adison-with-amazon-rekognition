package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.request.AmazonRekognitionImageRequest;
import com.furkannsahin.adison.response.*;
import com.furkannsahin.adison.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PostMapping("/images/detect-labels")
    public ResponseEntity<AmazonDetectLabelsResponse> detectLabels(@RequestBody AmazonRekognitionImageRequest imageRequest) {
        return ResponseEntity.ok(new AmazonDetectLabelsResponse(amazonRekognitionService.detectLabels(imageRequest.getBase64Image())));
    }

    @PostMapping("/images/detect-texts")
    public ResponseEntity<AmazonTextDetectionListResponse> detectTexts(@RequestBody AmazonRekognitionImageRequest imageRequest) {
        return ResponseEntity.ok(new AmazonTextDetectionListResponse(amazonRekognitionService.detectTexts(imageRequest.getBase64Image())));
    }

    @PostMapping("/images/detect-faces")
    public ResponseEntity<AmazonDetectFacesResponse> detectFaces(@RequestBody AmazonRekognitionImageRequest imageRequest)  {
        return ResponseEntity.ok(new AmazonDetectFacesResponse(amazonRekognitionService.detectFaces(imageRequest.getBase64Image())));
    }

    // -> Collection operations

    @GetMapping("/collections")
    public ResponseEntity<AmazonListCollectionsResponse> listCollection(){
        return ResponseEntity.ok(new AmazonListCollectionsResponse(amazonRekognitionService.listCollections()));
    }

    @PostMapping("/collection")
    public ResponseEntity<AmazonCreateCollectionResponse> createCollection(@RequestParam("collectionName") String collectionName) {
        return ResponseEntity.ok(new AmazonCreateCollectionResponse(amazonRekognitionService.createCollection(collectionName)));
    }

    @DeleteMapping("/collection")
    public ResponseEntity<AmazonDeleteCollectionResponse> deleteCollection(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(new AmazonDeleteCollectionResponse(amazonRekognitionService.deleteCollection(collectionName)));
    }

    @GetMapping("/collection/describe-collection")
    public ResponseEntity<AmazonDescribeCollectionResponse> describeCollection(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(new AmazonDescribeCollectionResponse(amazonRekognitionService.describeCollection(collectionName)));
    }

    @PostMapping("/collection/index-faces/{id}")
    public ResponseEntity<AmazonIndexFacesResponse> indexFaces(@RequestBody AmazonRekognitionImageRequest imageRequest, @RequestParam("collectionId") String collectionId, @PathVariable("id") Long userId) throws IOException {
        return ResponseEntity.ok(new AmazonIndexFacesResponse(amazonRekognitionService.addFacesToCollection(imageRequest.getBase64Image(), collectionId, userId)));
    }

    @GetMapping("/collection/list-faces")
    public ResponseEntity<AmazonListFacesResponse> listFacesInCollection(@RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(new AmazonListFacesResponse(amazonRekognitionService.listFacesInCollection(collectionId)));
    }

    @DeleteMapping("/collection/delete-faces")
    public ResponseEntity<AmazonDeleteFacesResponse> deleteFacesInCollection(@RequestParam("collectionId") String collectionId, @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(new AmazonDeleteFacesResponse(amazonRekognitionService.deleteFacesInCollection(collectionId, faceId)));
    }

    @PostMapping("/collection/search-faces")
    public ResponseEntity<AmazonSearchFacesByImageResponse> searchFacesInCollection(@RequestParam("collectionId") String collectionId,
                                                                                    @RequestBody AmazonRekognitionImageRequest imageRequest) {
        return ResponseEntity.ok(new AmazonSearchFacesByImageResponse(amazonRekognitionService.searchFacesInCollection(collectionId, imageRequest.getBase64Image())));
    }

    @PostMapping("/collection/get-face-count")
    public ResponseEntity<Integer> getFaceCount(@RequestBody AmazonRekognitionImageRequest imageRequest) {
        return ResponseEntity.ok(amazonRekognitionService.getFaceCount(imageRequest.getBase64Image()));
    }

    @PostMapping("/collection/get-face-user-id")
    public ResponseEntity<Long> getFaceUserId(@RequestParam("collectionId") String collectionId, @RequestBody AmazonRekognitionImageRequest imageRequest) {
        return ResponseEntity.ok(amazonRekognitionService.getMatchedFaceUserId(collectionId, imageRequest.getBase64Image()));
    }

    @GetMapping("/collection/get-face-id-by-user-id/{id}")
    public ResponseEntity<String> getFaceUserId(@RequestParam("collectionId") String collectionId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(amazonRekognitionService.getFaceIdByUserId(collectionId, id));
    }
}
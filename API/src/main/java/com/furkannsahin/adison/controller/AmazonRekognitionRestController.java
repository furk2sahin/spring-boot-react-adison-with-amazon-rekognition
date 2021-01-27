package com.furkannsahin.adison.controller;

import com.amazonaws.services.rekognition.model.*;
import com.furkannsahin.adison.response.*;
import com.furkannsahin.adison.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PostMapping("/images/detect-labels")
    public ResponseEntity<AmazonDetectLabelsResponse> detectLabels(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(new AmazonDetectLabelsResponse(amazonRekognitionService.detectLabels(image)));
    }

    @PostMapping("/images/detect-texts")
    public ResponseEntity<AmazonTextDetectionListResponse> detectTexts(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(new AmazonTextDetectionListResponse(amazonRekognitionService.detectTexts(image)));
    }

    @PostMapping("/images/detect-faces")
    public ResponseEntity<AmazonDetectFacesResponse> detectFaces(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(new AmazonDetectFacesResponse(amazonRekognitionService.detectFaces(image)));
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
    public ResponseEntity<AmazonIndexFacesResponse> indexFaces(@RequestParam MultipartFile image, @RequestParam("collectionId") String collectionId, @PathVariable("id") Long userId) throws IOException {
        return ResponseEntity.ok(new AmazonIndexFacesResponse(amazonRekognitionService.addFacesToCollection(image, collectionId, userId)));
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
                                                                     @RequestParam("image") MultipartFile image)  throws IOException{
        Image amazonImage = new Image().withBytes(ByteBuffer.wrap(image.getBytes()));
        return ResponseEntity.ok(new AmazonSearchFacesByImageResponse(amazonRekognitionService.searchFacesInCollection(collectionId, amazonImage)));
    }

    @PostMapping("/collection/get-face-count")
    public ResponseEntity<Integer> getFaceCount(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.getFaceCount(image));
    }

    @PostMapping("/collection/get-face-user-id")
    public ResponseEntity<Long> getFaceUserId(@RequestParam("collectionId") String collectionId, @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.getMatchedFaceUserId(collectionId, image));
    }

    @GetMapping("/collection/get-face-id-by-user-id/{id}")
    public ResponseEntity<String> getFaceUserId(@RequestParam("collectionId") String collectionId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(amazonRekognitionService.getFaceIdByUserId(collectionId, id));
    }
}
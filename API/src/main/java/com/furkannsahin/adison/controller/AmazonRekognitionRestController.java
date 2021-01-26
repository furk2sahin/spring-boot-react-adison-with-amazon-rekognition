package com.furkannsahin.adison.controller;

import com.amazonaws.services.rekognition.model.*;
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
    public ResponseEntity<DetectLabelsResult> detectLabels(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.detectLabels(image));
    }

    @PostMapping("/images/detect-texts")
    public ResponseEntity<List<TextDetection>> detectTexts(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.detectTexts(image));
    }

    @PostMapping("/images/detect-faces")
    public ResponseEntity<DetectFacesResult> detectFaces(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.detectFaces(image));
    }

    // -> Collection operations

    @GetMapping("/collections")
    public ResponseEntity<ListCollectionsResult> listCollection(){
        return ResponseEntity.ok(amazonRekognitionService.listCollections());
    }

    @PostMapping("/collection")
    public ResponseEntity<CreateCollectionResult> createCollection(@RequestParam("collectionName") String collectionName) {
        return ResponseEntity.ok(amazonRekognitionService.createCollection(collectionName));
    }

    @DeleteMapping("/collection")
    public ResponseEntity<DeleteCollectionResult> deleteCollection(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(amazonRekognitionService.deleteCollection(collectionName));
    }

    @GetMapping("/collection/describe-collection")
    public ResponseEntity<DescribeCollectionResult> describeCollection(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(amazonRekognitionService.describeCollection(collectionName));
    }

    @PostMapping("/collection/index-faces/{id}")
    public ResponseEntity<IndexFacesResult> indexFaces(@RequestParam MultipartFile image, @RequestParam("collectionId") String collectionId, @PathVariable("id") Long userId) throws IOException {
        return ResponseEntity.ok(amazonRekognitionService.addFacesToCollection(image, collectionId, userId));
    }

    @GetMapping("/collection/list-faces")
    public ResponseEntity<ListFacesResult> listFacesInCollection(@RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.listFacesInCollection(collectionId));
    }

    @DeleteMapping("/collection/delete-faces")
    public ResponseEntity<DeleteFacesResult> deleteFacesInCollection(@RequestParam("collectionId") String collectionId, @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(amazonRekognitionService.deleteFacesInCollection(collectionId, faceId));
    }

    @PostMapping("/collection/search-faces")
    public ResponseEntity<SearchFacesByImageResult> searchFacesInCollection(@RequestParam("collectionId") String collectionId,
                                                                     @RequestParam("image") MultipartFile image)  throws IOException{
        Image amazonImage = new Image().withBytes(ByteBuffer.wrap(image.getBytes()));
        return ResponseEntity.ok(amazonRekognitionService.searchFacesInCollection(collectionId, amazonImage));
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
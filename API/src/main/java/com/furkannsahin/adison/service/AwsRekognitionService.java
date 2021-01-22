package com.furkannsahin.adison.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@Service
public class AwsRekognitionService {

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    public DetectLabelsResult detectLabels(MultipartFile image) throws IOException {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));

        return amazonClient.detectLabels(request);
    }

    public DetectTextResult detectTexts(MultipartFile image) throws IOException {
        DetectTextRequest request = new DetectTextRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));

        return amazonClient.detectText(request);
    }

}
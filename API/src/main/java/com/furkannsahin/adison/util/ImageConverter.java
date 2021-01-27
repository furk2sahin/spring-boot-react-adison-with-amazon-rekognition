package com.furkannsahin.adison.util;

import com.amazonaws.services.rekognition.model.Image;
import java.nio.ByteBuffer;
import java.util.Base64;

public class ImageConverter {
    public Image base64ToImage(String base64Str){
        byte[] imageArray = Base64.getDecoder().decode(base64Str);
        return new Image().withBytes(ByteBuffer.wrap(imageArray));
    }
}

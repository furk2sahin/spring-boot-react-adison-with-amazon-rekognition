package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.request.AdRequest;
import com.furkannsahin.adison.response.AdListResponse;
import com.furkannsahin.adison.response.AdResponse;
import com.furkannsahin.adison.service.AdService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AdRestController {

    private AdService adService;

    @Autowired
    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/ads")
    public ResponseEntity<AdListResponse> getAllAds(){
        return ResponseEntity.ok(new AdListResponse(adService.getAllAds()));
    }

    @PostMapping("/ad")
    public ResponseEntity<AdResponse> createAd(@RequestBody AdRequest adRequest){
        try{
            AdResponse adResponse = new AdResponse(adService.addAd(adRequest.getAdDto()));
            return ResponseEntity.ok(adResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/ad/{id}")
    public ResponseEntity<AdResponse> updateAd(@PathVariable("id") Long id, @RequestBody AdRequest adRequest){
        try{
            AdResponse adResponse = new AdResponse(adService.updateAd(id, adRequest.getAdDto()));
            return ResponseEntity.ok(adResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/ad/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable("id") Long id){
        try{
            adService.deleteAd(id);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Ad Successfully deleted by id: " + id, HttpStatus.OK);
    }

    @GetMapping("/ad/{id}")
    public ResponseEntity<AdResponse> getAdById(@PathVariable("id") Long id){
        try{
            AdDto adDto = adService.getAdById(id);
            return ResponseEntity.ok(new AdResponse(adDto));
        } catch (Exception e){
            return ResponseEntity.ok(new AdResponse(null));
        }

    }
}

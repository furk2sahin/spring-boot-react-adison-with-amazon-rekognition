package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.dto.CompanyDto;
import com.furkannsahin.adison.request.CompanyRequest;
import com.furkannsahin.adison.response.CompanyListResponse;
import com.furkannsahin.adison.response.CompanyResponse;
import com.furkannsahin.adison.service.CompanyService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompanyRestController {

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<CompanyListResponse> getAllCompanies(){
        return ResponseEntity.ok(new CompanyListResponse(companyService.getAllCompanies()));
    }

    @PostMapping("/company")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest){
        try{
            CompanyResponse companyResponse = new CompanyResponse(companyService.addCompany(companyRequest.getCompanyDto()));
            return ResponseEntity.ok(companyResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyRequest companyRequest){
        try{
            CompanyResponse companyResponse = new CompanyResponse(companyService.updateCompany(id, companyRequest.getCompanyDto()));
            return ResponseEntity.ok(companyResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id){
        try{
            companyService.deleteCompany(id);
            return new ResponseEntity<>("Company Successfully deleted by id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable("id") Long id){
        try{
            CompanyDto companyDto = companyService.getCompanyById(id);
            return ResponseEntity.ok(new CompanyResponse(companyDto));
        } catch (Exception e) {
            return ResponseEntity.ok(new CompanyResponse(null));
        }
    }
}

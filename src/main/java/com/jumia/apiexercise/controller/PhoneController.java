package com.jumia.apiexercise.controller;

import java.util.List;
import java.util.Map;

import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.model.PageModel;
import com.jumia.apiexercise.model.PageRequestModel;
import com.jumia.apiexercise.service.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "phones")
public class PhoneController {
    
    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/", produces="application/json")
    public ResponseEntity<List<PhoneDto>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(phoneService.listAll());
    }

    @GetMapping(value = "/filter", produces="application/json")
	public ResponseEntity<PageModel<PhoneDto>> findAllFiltering(@RequestParam Map<String, String> params) {
		PageRequestModel pageRequestModel = new PageRequestModel(params);
        return ResponseEntity.status(HttpStatus.FOUND).body(phoneService.listAllFilteringPagingAndOrdering(pageRequestModel));
	}

    @GetMapping(value = "/fillPhoneCountry", produces="application/json")
	public ResponseEntity<String> fillPhoneCountry() {
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.fillPhoneCountry());
	}

}

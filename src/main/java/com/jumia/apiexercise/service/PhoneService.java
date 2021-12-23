package com.jumia.apiexercise.service;

import java.util.List;

import javax.transaction.Transactional;

import com.jumia.apiexercise.domain.Phone;
import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.model.PageModel;
import com.jumia.apiexercise.model.PageRequestModel;
import com.jumia.apiexercise.repository.PhoneRepository;
import com.jumia.apiexercise.specification.PhoneSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    
    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional
    public List<PhoneDto> listAll(){
        List<Phone> list = phoneRepository.findAll();
        if(list.size() > 0){
            return PhoneDto.toList(list);
        }else{
            throw new NotFoundException("No phones were found.");
        }
    }

    @Transactional
    public PageModel<PhoneDto> listAllFilteringPagingAndOrdering(PageRequestModel pageRequestModel){
        Pageable pageable = pageRequestModel.toSpringPageRequest();
        
        Specification<Phone> spec = Specification.where(
            PhoneSpecification.equalCountryName(pageRequestModel.getCountry())).and(
            PhoneSpecification.equalState(pageRequestModel.getState()));
        
            Page<Phone> page = phoneRepository.findAll(spec,pageable);
        if(!page.isEmpty()){
            return new PageModel<PhoneDto>(
                                        (int) page.getTotalElements(),
                                        page.getSize(), 
                                        page.getTotalPages(),
                                        PhoneDto.toList(page.getContent()));
        }else{
            throw new NotFoundException("No phones were found.");
        }
    }
}

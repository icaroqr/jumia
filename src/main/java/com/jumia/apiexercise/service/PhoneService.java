package com.jumia.apiexercise.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jumia.apiexercise.domain.Country;
import com.jumia.apiexercise.domain.CountryFactory;
import com.jumia.apiexercise.domain.Phone;
import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.model.PageModel;
import com.jumia.apiexercise.model.PageRequestModel;
import com.jumia.apiexercise.repository.CountryRepository;
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

    @Autowired
    private CountryRepository countryRepository;

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
        if(page != null && !page.isEmpty()){
            return new PageModel<PhoneDto>(
                                        (int) page.getTotalElements(),
                                        page.getSize(), 
                                        page.getTotalPages(),
                                        PhoneDto.toList(page.getContent()));
        }else{
            throw new NotFoundException("No phones were found.");
        }
    }

    @Transactional
    public String fillPhoneCountry() {
        List<Phone> phoneList = phoneRepository.findAll();
        for (Phone phone : phoneList) {
            if(phone.getCountry() == null){
                Optional<Country> country = CountryFactory.getCountry(phone.getNumber());
                if(country.isPresent()){
                    phone.setCountry(countryRepository.findByName(country.get().getName()).get());
                    if(country.get().getName().equals("Invalid country")){
                        phone.setState("not valid");
                    }else{
                        phone.setState("valid");
                    }
                    phoneRepository.save(phone);
                }
            }
        }
        return "Phones Country updated";
    }

    public Phone getPhoneByNumber(String number) {
        return phoneRepository.findByNumber(number).orElseThrow(()-> new NotFoundException("Phone not found for number: "+number));
    }
}

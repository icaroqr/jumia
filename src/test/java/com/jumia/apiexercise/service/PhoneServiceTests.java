package com.jumia.apiexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jumia.apiexercise.domain.Phone;
import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.repository.PhoneRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PhoneServiceTests {

    @Autowired
    private PhoneService phoneService;

    @MockBean
    private PhoneRepository phoneRepository;

    @Before
    public void setUp() {
        Phone ugandaPhone = new Phone(0,"(256) 775069443","valid",null,null);
        Phone invalidPhone = new Phone(99,"(258) 042423566","not valid",null,null);
        
        List<Phone> phones = new ArrayList<Phone>();
        phones.add(ugandaPhone);
        phones.add(invalidPhone);
        
        when(phoneRepository.findByNumber(ugandaPhone.getNumber())).thenReturn(Optional.of(ugandaPhone));
        when(phoneRepository.findByNumber("(123) 999999999")).thenReturn(Optional.empty());
        when(phoneRepository.findById(invalidPhone.getId())).thenReturn(Optional.of(invalidPhone));
        when(phoneRepository.findAll()).thenReturn(phones);
    }

    @Test
    public void whenValidNumber_thenPhoneShouldBeFound() {
        String number = "(256) 775069443";
        Phone found = phoneService.getPhoneByNumber(number);

        assertThat(found.getNumber()).isEqualTo(number);
    }

    @Test(expected = NotFoundException.class)
    public void whenInValidNumber_thenPhoneShouldNotBeFound() {
        Phone fromDb = phoneService.getPhoneByNumber("(123) 999999999");
        assertThat(fromDb).isNull();

        verifyFindByNumberIsCalledOnce("(123) 999999999");
    }

    @Test
    public void given2Phones_whengetAll_thenReturn2Records() {
        Phone ugandaPhone = new Phone(0,"(256) 775069443","valid",null,null);
        Phone invalidPhone = new Phone(99,"(258) 042423566","not valid",null,null);
        
        List<PhoneDto> allPhones = phoneService.listAll();
        verifyFindAllCustomersIsCalledOnce();
        assertThat(allPhones).hasSize(2).extracting(PhoneDto::getNumber).contains(ugandaPhone.getNumber(), invalidPhone.getNumber());
    }

    private void verifyFindByNumberIsCalledOnce(String number) {
        verify(phoneRepository, VerificationModeFactory.times(1)).findByNumber(number);
        reset(phoneRepository);
    }

    private void verifyFindAllCustomersIsCalledOnce() {
        verify(phoneRepository, VerificationModeFactory.times(1)).findAll();
        reset(phoneRepository);
    }
    
}

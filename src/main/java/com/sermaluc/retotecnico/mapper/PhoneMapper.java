package com.sermaluc.retotecnico.mapper;

import com.sermaluc.retotecnico.dto.PhoneDto;
import com.sermaluc.retotecnico.model.Phone;

import java.util.List;

public class  PhoneMapper {

    public static List<Phone> mapPhone(List<PhoneDto> listPhoneDto) {
        if (listPhoneDto == null || listPhoneDto.isEmpty()) {
            return null;
        } else {
            return listPhoneDto.stream()
                    .map(phoneDto ->
                            Phone.builder().countryCode(phoneDto.getCountryCode())
                                    .cityCode(phoneDto.getCityCode())
                                    .number(phoneDto.getNumber()).build()
                    ).toList();
        }
    }

}

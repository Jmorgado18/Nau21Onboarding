package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingAddressDTO {
    private String street;
    private String city;
    private String countryCode;
    private String zipCode;
}

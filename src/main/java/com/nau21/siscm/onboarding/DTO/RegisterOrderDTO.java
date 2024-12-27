package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterOrderDTO {
    private String customerName;
    private List<ItemDetailsDTO> items;
    private ShippingAddressDTO shippingAddress;
}

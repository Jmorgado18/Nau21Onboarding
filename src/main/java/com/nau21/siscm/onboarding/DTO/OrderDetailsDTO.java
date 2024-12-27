package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailsDTO {
    private String reference;
    private String customerName;
    private String shippingAddress;
    private List<ItemDetailsDTO> items;
}

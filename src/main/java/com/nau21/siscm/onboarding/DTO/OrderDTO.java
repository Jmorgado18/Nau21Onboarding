package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String reference;
    private int items;
    private String customerName;
    private int id;
    private int customerId;
    private int shippingAddressId;
    private List<Integer> itemIds;
}

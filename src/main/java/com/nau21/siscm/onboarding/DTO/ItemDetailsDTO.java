package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
    public class ItemDetailsDTO {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String currency;
    private String formattedPrice;
    private List<ItemOptionDTO> options;
}

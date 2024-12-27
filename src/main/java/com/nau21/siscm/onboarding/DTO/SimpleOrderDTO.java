package com.nau21.siscm.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleOrderDTO {
    private String reference;
    private int items;
    private String customerName;
}

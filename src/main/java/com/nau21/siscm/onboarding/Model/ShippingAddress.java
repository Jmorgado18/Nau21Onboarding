package com.nau21.siscm.onboarding.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "ShippingAddresses")
@Getter
@Setter
@NoArgsConstructor
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;
    private String city;

    @Column(length = 2)
    private String countryCode;

    private String zipCode;
}

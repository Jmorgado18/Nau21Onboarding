package com.nau21.siscm.onboarding.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Items")
@Getter
@Setter
@NoArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "quantity", nullable = false)
    private int quantity=0; // Valor padrão - this might not work - for testing purposes

    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL)
    private List<ItemOptions> options;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}

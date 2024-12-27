package com.nau21.siscm.onboarding.Model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "item_options")
@Getter
@Setter
public class ItemOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "option_name")
    @Enumerated(EnumType.STRING)
    private optionName name;

    @Column(name = "option_value")
    @Enumerated(EnumType.STRING)
    private optionValue value;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items items;
}

package com.sermaluc.retotecnico.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name  = "phones")
@Getter
@Setter
@Builder
public class Phone {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    private User user;


}

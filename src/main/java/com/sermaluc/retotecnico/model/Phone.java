package com.sermaluc.retotecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name  = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;
    private String cityCode;
    private String countryCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}

package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Company company;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private double amount;
    private double price;
}

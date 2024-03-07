package com.example.crud_exercise.BankSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private String id;
    private String username;
    private double balance;
}

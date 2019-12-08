package com.jiaren.pizzaapp.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    //to be defined
    private enum RoleName {Admin, Role1, Role2}

    @Column
    private RoleName roleName;
}

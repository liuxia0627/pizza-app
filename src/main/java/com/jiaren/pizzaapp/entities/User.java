package com.jiaren.pizzaapp.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class User extends BaseIdentity{
    @Column
    private Set<Role> role;
}

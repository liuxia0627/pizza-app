package com.jiaren.pizzaapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
public class User extends BaseIdentity {
    @ManyToMany
    private Set<Role> roleSet;
}

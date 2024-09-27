package com.dette.model;

import jakarta.persistence.*;
//import lombok.Data;
import java.util.Collection;

@Entity
//@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

}

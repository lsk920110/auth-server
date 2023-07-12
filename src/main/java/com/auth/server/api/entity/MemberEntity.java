package com.auth.server.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth_token")
    private String auth_token;

    /**
     *
     */
    @Column(name = "stat")
    private String stat;
}

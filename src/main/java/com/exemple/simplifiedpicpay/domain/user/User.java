package com.exemple.simplifiedpicpay.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;


    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.balance = userDTO.balance();
        this.document = userDTO.document();
        this.userType = userDTO.userType();
        this.password = userDTO.password();
        this.email = userDTO.email();
    }
}

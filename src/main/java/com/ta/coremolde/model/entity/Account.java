package com.ta.coremolde.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account", schema = "public")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "email")
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "phone_no")
    protected String phoneNo;

    @Column(name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    protected Role role;

}

package com.jpa.tacademy.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    private int age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
}

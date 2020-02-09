package com.jpa.tacademy.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
// @NameQuery => 컴파일시 에러 확인가능!
@NamedQuery(
        name = "Member.findByName",
        query = "select m from Member m where m.name = :username")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    private int age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Override
    public String toString() {
        return "id: " + id + ", " + "name: " + name;
    }

}

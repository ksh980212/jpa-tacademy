package com.jpa.tacademy.demo;

import com.jpa.tacademy.demo.entity.Member;
import com.jpa.tacademy.demo.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        System.out.println("Hello JPA");
        emf.close();
    }

    private static void logic(EntityManager em) {
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

        Member member = new Member();
        member.setName("hello");
        member.setTeamId(team.getId());

        em.persist(member);

        // 조회
        Member findMember = em.find(Member.class, member.getId());
        Long teamId = findMember.getTeamId();

        Team findTeam = em.find(Team.class, teamId);
        System.out.println(findTeam.getName());
        /**
         * 연관관계가 없어 직접 하나 하나 가져와야함..
         * 객체지향 방법이 아니다.
         * */
    }
}

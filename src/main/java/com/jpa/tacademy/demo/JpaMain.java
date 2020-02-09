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
        } catch (Exception e) {
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
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        String jpql = "select m From Member m where m.name like '%hello%'";
        List<Member> result = em.createQuery(jpql, Member.class)
                .getResultList();

        for (Member member1 : result) {
            System.out.println(member1.getName() + ", " + member1.getTeam());
        }

        String jpql1 = "select m From Member m where m.name like '%hello%'";
        List<Member> result1 = em.createQuery(jpql1, Member.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();
        for (Member member1 : result1) {
            System.out.println(member1.getName() + ", " + member1.getTeam());
        }


        // fetch join 즉시로딩
        String jpql2 = "select m From Member m join fetch m.team where m.name like '%hello%'";
        List<Member> result2 = em.createQuery(jpql2, Member.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();
        for (Member member1 : result2) {
            System.out.println(member1.getName() + ", " + member1.getTeam());
        }

        //NamedQuery
        List<Member> resultList = em.createNamedQuery("Member.findByName", Member.class)
                .setParameter("username", "hello")
                .getResultList();

        for (Member member1 : resultList) {
            System.out.println("member: " + member1.getName());
        }
    }

//    private static void logic(EntityManager em) {
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member = new Member();
//        member.setName("hello");
//        member.setTeam(team);
//        em.persist(member);
//
//        em.flush();
//        em.clear();
//        /**
//         * 조회 Query 로그 보기 위해
//         * */
//
//        // 조회
//        Member findMember = em.find(Member.class, member.getId());
//        Team findTeam = findMember.getTeam();
//        System.out.println(findTeam.getName());
//
//        // 팀에서 멤버 데이터 가져오기
//        List<Member> members = findTeam.getMembers();
//        for (Member member1 : members) {
//            System.out.println("member1 = " + member1);
//        }
//
//        /**
//         *  객체 지향 모델링
//         *  연관 관계 매핃
//         */
//    }

//    private static void logic(EntityManager em) {
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member = new Member();
//        member.setName("hello");
//        member.setTeamId(team.getId());
//
//        em.persist(member);
//
//        // 조회
//        Member findMember = em.find(Member.class, member.getId());
//        Long teamId = findMember.getTeamId();
//
//        Team findTeam = em.find(Team.class, teamId);
//        System.out.println(findTeam.getName());
//        /**
//         * 연관관계가 없어 직접 하나 하나 가져와야함..
//         * 객체지향 방법이 아니다.
//         * */
//    }
}

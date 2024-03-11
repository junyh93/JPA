package com.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
    // 엔티티 매니저 팩토리 - 생성
    // persistence.xml에 설정한 이름을 넘겨준다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        save(em);

        tx.commit();
        em.close();
        emf.close();
        /*
        // 엔티티 매니저 - 생성
        // 엔티티 매니저 팩토리에서 엔티티 매니저를 생성한다.
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();
        // 트랜잭션 - 시작
       try{
            tx.begin(); // 트랜잭션 - 시작
            logic(em);  // 비즈니스 로직 실행
            tx.commit();    // 트랜잭션 - 커밋
        } catch (Exception e){
            tx.rollback();  // 트랜잭션 - 롤백
        } finally {
            em.close(); // 엔티티 매니저 - 종료
        }
        emf.close();    // 엔티티 매니저 팩토리 - 종료*/
    }
    private static void logic(EntityManager em){
        Member member = new Member();
        member.setUsername("jyh");
        em.persist(member);
        System.out.println("member.getId() = " + member.getId());
    }

    private static void save(final EntityManager em){
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        em.persist(member2);
    }
}

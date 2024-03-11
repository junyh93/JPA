package com.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExampleMergeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember(150L, "회원1");
        member.setUsername("회원명변경"); // 2. 준영속 상태에서 변경
        mergeMember(member); // 3. 준영속 상태의 엔티티를 영속 상태로 변경
    }

    static Member createMember(Long id, String username){
        // 영속성 컨텍스트 1 시작
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 - 시작
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        em.persist(member); // 등록
        tx.commit();    // 트랜잭션 - 커밋
        em.close(); // 영속성 컨텍스트 1 종료,
                    // member 엔티티는 준영속 상태가 된다.
        return member;
    }

    static void mergeMember(Member member){
        // 영속성 컨텍스트 2 시작
        EntityManager em2 = emf.createEntityManager();
        // 트랜잭션 - 획득
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin(); // 트랜잭션 - 시작
        //Member mergeMember = em2.merge(member); // 병합
        member = em2.merge(member); // 병합
        tx2.commit();    // 트랜잭션 - 커밋

        // 준영속 상태
        //System.out.println("member=" + member.getUsername());

        // 영속 상태
        //System.out.println("mergeMember=" + mergeMember.getUsername());
        System.out.println("member=" + member.getUsername());
        System.out.println("em2 contains member=" + em2.contains(member));
        //System.out.println("em2 contains mergeMember=" + em2.contains(mergeMember));
        em2.close(); // 영속성 컨텍스트 2 종료
    }
}
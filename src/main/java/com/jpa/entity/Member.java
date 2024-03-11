package com.jpa.entity;

import com.jpa.type.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table  = "MY_SEQUENCES",
        pkColumnValue="MEMBER_SEQ", allocationSize = 1)
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME", nullable = false, length = 10)
    private String username;

    // 매핑 정보가 없는 필드
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}

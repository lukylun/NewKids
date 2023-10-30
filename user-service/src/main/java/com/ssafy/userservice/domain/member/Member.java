package com.ssafy.userservice.domain.member;

import com.ssafy.userservice.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 회원 entity
 *
 * @author 임우택
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String encryptedPwd;

    @Column(nullable = false, updatable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int exp;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String memberKey;

    @Builder
    private Member(String email, String encryptedPwd, String name, int age, int level, int exp, String nickname, Boolean active, String memberKey) {
        this.email = email;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.age = age;
        this.level = level;
        this.exp = exp;
        this.nickname = nickname;
        this.active = active;
        this.memberKey = memberKey;
    }

    //== 비즈니스 로직 ==//
    public void editEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd;
    }

    public void editNickname(String nickname) {
        this.nickname = nickname;
    }

    public void deActive() {
        this.active = false;
    }

    public void increaseExp(int exp) {
        int temp = this.exp + exp;
        this.level += temp / 100;
        this.exp = temp % 100;
    }
}


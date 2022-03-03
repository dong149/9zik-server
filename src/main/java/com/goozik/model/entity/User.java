package com.goozik.model.entity;

import com.goozik.model.constants.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저
 *
 * @author ryu
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 이름
     */
    @Column(nullable = false)
    private String name;
    /**
     * 이메일 주소
     */
    @Column(nullable = false)
    private String email;
    /**
     * 사진
     */
    @Column
    private String picture;
    /**
     * 권한
     */
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

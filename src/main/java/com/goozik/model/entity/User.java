package com.goozik.model.entity;

import com.goozik.model.constants.ProviderType;
import com.goozik.model.constants.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    //    @Id
//    @GeneratedValue
//    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column
    private String picture;
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(
        ProviderType providerType,
        String name,
        String nickName,
        String password,
        String email,
        String picture,
        Role role) {

        this.providerType = providerType;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
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

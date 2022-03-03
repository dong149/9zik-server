package com.goozik.model.entity.vo;

import com.goozik.model.entity.User;
import java.io.Serializable;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ryu
 */
@Getter
@ToString
public class SessionUser implements Serializable {

    private final String name;
    private final String email;
    private final String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

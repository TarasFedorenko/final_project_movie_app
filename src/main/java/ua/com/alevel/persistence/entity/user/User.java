package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column()
    private String username;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Column(nullable = false)
    private Boolean enabled;

    public User() {
        super();
        this.enabled = true;
    }

}

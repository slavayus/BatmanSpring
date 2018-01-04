package database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;
    private String passwordHash;
    private String hashCode;
    @OneToMany(mappedBy = "userEntity")
    private List<PointEntity> pointEntityList;

    public UserEntity(String login, String passwordHash, String hashCode) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.hashCode = hashCode;
    }

    public UserEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getHashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return " id : " + id + "\n" +
                " login " + login + "\n" +
                " passwordHash " + passwordHash + "\n" +
                " hashCode " + hashCode + "\n";
    }
}

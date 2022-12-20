package s.praveen.cms.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import s.praveen.cms.model.ro.UserRequest;


/**
 * The type User.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@With
@Table(name = "users")
public class User {
    /**
     * The Id.
     */
    @Id
    @Column(name = "user_id")
    long id;
    /**
     * The User name.
     */
    @Column(name = "user_name")
    String userName;
    /**
     * The User role.
     */
    @Column(name = "user_role")
    String userRole;

    /**
     * Instantiates a new User.
     *
     * @param request the request
     */
    public User(UserRequest request) {
        this.setUserName(request.userName());
        this.setId(System.nanoTime());
    }
}

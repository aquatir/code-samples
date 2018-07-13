package learn_to_code.springboot.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * The reason why we user the same class for DB entity and for UserDetails is for simplicity only.
 * Most likely you should NOT do the same, because passing around an object with password (even encrypted) is not
 * the smartest idea. Ideally the only thing you should be doing with passwords is resetting then. All password
 * reading / matching should be done on spring security level.
 */
@Data
@Entity
@Table(name="users") // 'user' table name is reserved by postgres, so we use users instead
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

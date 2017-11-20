package pl.projekt.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.projekt.daoimpl.UsersDAOImpl;
import pl.projekt.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 15.11.2017.
 */
@Service
@Transactional
public class MyDBAuthenticationService implements UserDetailsService {

    private UsersDAOImpl usersDAOImpl = new UsersDAOImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDAOImpl.findUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("Users" + username + "was not founf in the database");
        }

        String role = "klient";
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);
        grantList.add(grantedAuthority);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails =(UserDetails) new User(user.getLogin(),user.getHaslo(),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,grantList); {
        return userDetails;
        }
    }
}

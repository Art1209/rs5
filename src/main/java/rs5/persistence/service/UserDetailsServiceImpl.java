package rs5.persistence.service;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs5.persistence.entity.Role;
import rs5.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;


@Log4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService us;

    @Autowired
    RoleService rs;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = us.getUser(username);
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : user.getRolesList()){
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),auths);
        return userDetails;
    }
}

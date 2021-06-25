package application.service;

import application.dao.UserDAO;
import application.entity.Role;
import application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        System.out.println("Инициализация UserDetailsServiceImpl ");
        this.userDAO = userDAO;
    }

    private Collection <? extends GrantedAuthority> setGrantedAuthorities(Collection<Role> roles) {
        System.out.println("Инициализация setGrantedAuthorities ");
        return roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getRole())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername запустился");

        User user = userDAO.getByUserName(s);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), setGrantedAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

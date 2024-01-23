package it.ricette.service;

import java.util.List;

import it.ricette.dto.*;
import it.ricette.model.User;


public interface UserService {
    void saveUser(UserDto userDto);
    
    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}

//@Service
//public class UserService implements UserDetailsService {
//    @Autowired    
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(
//            user.getUsername(), 
//            user.getPassword(), 
//            getAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//}
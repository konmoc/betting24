package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Role;
import pl.coderslab.betting.entity.User;
import pl.coderslab.betting.repository.RoleRepository;
import pl.coderslab.betting.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;


    public UserService(UserRepository userRepository,
                           RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * this method puts new user into database and encodes his password - it is only used when user
     * is savet to DB for the first time - we do not want out password encoded twice
     * @param user
     */

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void saveUserWithoutEncoding(User user){
        userRepository.save(user);
    }

    public User findUserById(Long id){
        return  userRepository.findUserById(id);
    }

    public User findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void deleteUserWithId(Long id){
        userRepository.deleteUserById(id);
    }
}

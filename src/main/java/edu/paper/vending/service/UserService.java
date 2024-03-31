package edu.paper.vending.service;

import edu.paper.vending.dto.LoginForm;
import edu.paper.vending.dto.RegistrationForm;
import edu.paper.vending.repo.ProductCardRepository;
import edu.paper.vending.repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    ProductCardRepository productCardRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByUsername(username) != null) {
            return userRepository.findByUsername(username);
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository repository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = repository;
    }

    public void addUser(RegistrationForm form) {
        User user = new User();

        user.setUsername(form.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());

        System.out.println("\n\nUser registered!\n\n");
        userRepository.save(user);
    }

    public boolean checkUser(LoginForm form) {
        User user = userRepository.findByEmail(form.getEmail());

        if (user == null) {
            return false;
        }
        return bCryptPasswordEncoder.matches(form.getPassword(), user.getPassword());
    }

    public boolean checkNames(String name) {
        /**for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(name)) {
                return false;
            }
        }*/
        return true;
    }

    public String nameByEmail(String email) {
        return userRepository.findByEmail(email).getUsername();
    }

    public User getByName(String name) {
        return userRepository.findByUsername(name);
    }
}

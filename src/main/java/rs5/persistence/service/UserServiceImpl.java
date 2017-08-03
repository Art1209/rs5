package rs5.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs5.persistence.entity.User;
import rs5.persistence.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    @Transactional
    public User getUser(String login){
        return userRepository.findByLogin(login);
    }

    @Transactional
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user){
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void updateUser(User user){
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    @Transactional
    public Boolean userExists(Long id){
        return userRepository.exists(id);
    }

}

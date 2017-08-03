package rs5.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import rs5.persistence.entity.User;

import java.util.List;

public interface UserService {


    public User getUser(Long id);

    public User getUser(String login);


    public List<User> getAll();

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public void deleteUser(Long id);

    Boolean userExists(Long id);
}

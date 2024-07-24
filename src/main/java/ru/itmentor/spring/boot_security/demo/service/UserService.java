package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    User saveOrUpdateUser(User user);
    void removeUserById(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    List<User> findUserByLastName(String lastName);
    User findByLogin(String login);
}

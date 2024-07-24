package ru.itmentor.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByIdAsc();
    List<User> findAllByLastName(String lastName);
    User findByLogin(String login);
}

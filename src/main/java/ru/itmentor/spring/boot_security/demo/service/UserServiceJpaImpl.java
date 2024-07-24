package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceJpaImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceJpaImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveOrUpdateUser(User user) {

            Set<Role> roles = new HashSet<>();
            for (Role role : user.getRoles()) {
                roles.add(roleRepository.findByName(role.getName()).orElse(role));
            }
            user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void removeUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NullPointerException("User ID not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }


    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

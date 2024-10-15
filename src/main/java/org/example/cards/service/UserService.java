package org.example.cards.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.*;
import org.example.cards.database.dao.UserDAO;
import org.example.cards.database.dao.UserRoleDAO;
import org.example.cards.database.entity.User;
import org.example.cards.database.entity.UserRole;
import org.example.cards.form.CreateUserFormBean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(@Valid CreateUserFormBean form) {
        // there were no errors so we can create the new user in the database
        User user = new User();

        // encrypt the password before saving it to the database
        user.setEmail(form.getEmail());

        // we are getting in a plain text password because the user entered it into the form
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        user.setCreateDate(new Date());

        // save the user to the database
        userDao.save(user);

        // create a user role for the user
        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        userRole.setUserId(user.getId());

        userRoleDao.save(userRole);

        return user;
    }

}

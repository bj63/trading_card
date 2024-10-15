package org.example.cards.database.dao;

import org.example.cards.database.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {


    User findByEmailIgnoreCase(String email);
    @Query("select u from User u where " +
            "lower(u.email) like lower(concat('%', :search, '%')) or " +
            "u.id = :searchId or " +
            "lower(u.name) like lower(concat('%', :search, '%'))")
    List<User> searchUser(@Param("search") String searchName, @Param("searchId") Integer searchId);

    User findById (Integer id);

}
package org.example.cards.database.dao;
import org.example.cards.database.entity.UserRole;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {


    List<UserRole> findByUserId(Integer userId);

}
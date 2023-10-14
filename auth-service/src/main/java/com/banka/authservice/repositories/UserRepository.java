package com.banka.authservice.repositories;

import com.banka.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByUserId(Long id);
/*
    User findByUsername(String username);
*/
    User findByEmail(String email);


}

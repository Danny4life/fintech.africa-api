package com.osiki.fintechafricaui.respository;

import com.osiki.fintechafricaui.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Optional<Users> findOneByEmailOrPassword(String email, String password);
}

package com.sumukh.fabricos.Repositories;

import com.sumukh.fabricos.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}

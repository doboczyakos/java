package com.example.gyakorlat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

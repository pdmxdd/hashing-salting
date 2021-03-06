package dev.paulmatthews.hashingsalting.repositories;

import dev.paulmatthews.hashingsalting.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUserName(String userName);
}

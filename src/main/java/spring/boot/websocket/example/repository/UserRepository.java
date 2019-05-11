package spring.boot.websocket.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.websocket.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByIdNotIn(int id);
}
package spring.boot.websocket.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.websocket.example.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query("select m from Message m where (m.to.id=:toId and m.from.id=:fromId) or (m.to.id=:fromId and m.from.id=:toId)")
    List<Message> findByToAndFrom(@Param("toId") int toId, @Param("fromId") int fromId);
}
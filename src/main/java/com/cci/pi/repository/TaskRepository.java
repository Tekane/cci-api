package com.cci.pi.repository;

import com.cci.pi.model.Task;
import com.cci.pi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.id=:id AND t.user =:user")
    Optional<Task> findByUserAndTask(@Param("user") User User,@Param("id")  long id);

    List<Task> findByUser(User user);

    Optional<Task> findById(Long id);

}

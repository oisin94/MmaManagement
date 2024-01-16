package com.mmamanagement.repository;

import com.mmamanagement.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    // Jpa intuitively reads the method signature and sees findmy has Url. Looks
    // into the entity to find url attribute and bind it.
    // The string parameter is the string it will match with.
    Optional<Session> findByUrl(String url);

    @Query("SELECT s FROM Session s WHERE s.sessionTime BETWEEN :startOfWeek AND :endOfWeek")
    List<Session> findSessionsBetween(@Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek);}
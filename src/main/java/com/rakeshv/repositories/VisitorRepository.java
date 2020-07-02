package com.rakeshv.repositories;

import com.rakeshv.models.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findVisitorByBadgeNumber(Long badgeNumber);
}

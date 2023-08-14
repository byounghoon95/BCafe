package com.example.bcafe.api.repository.points;

import com.example.bcafe.entity.points.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointsRepository extends JpaRepository<Points, Long> {
    @Modifying
    @Query(value = "UPDATE Points p SET p.isDeleted = true WHERE p.phoneNumber = :phoneNumber")
    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

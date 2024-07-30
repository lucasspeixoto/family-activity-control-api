package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID>{

    @Query("SELECT i FROM Image i WHERE i.user.id = :id")
    Optional<Image> findByUserId(@Param("id") UUID id);
}
package com.example.diffbookdepthstreams.repository;


import com.example.diffbookdepthstreams.entity.StreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamRepository extends JpaRepository<StreamEntity, Long> {
}

package com.example.diffbookdepthstreams.repository;

import com.example.diffbookdepthstreams.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {
}

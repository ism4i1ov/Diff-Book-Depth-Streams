package com.example.diffbookdepthstreams.repository;

import com.example.diffbookdepthstreams.entity.StreamBidAskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamBidAskRepository extends JpaRepository<StreamBidAskEntity, Long> {
}

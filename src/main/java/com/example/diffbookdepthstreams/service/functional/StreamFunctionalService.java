package com.example.diffbookdepthstreams.service.functional;

import com.example.diffbookdepthstreams.entity.StreamBidAskEntity;
import com.example.diffbookdepthstreams.entity.StreamEntity;
import com.example.diffbookdepthstreams.repository.StreamBidAskRepository;
import com.example.diffbookdepthstreams.repository.StreamRepository;
import org.springframework.stereotype.Service;

@Service
public class StreamFunctionalService {

    private final StreamRepository streamRepository;
    private final StreamBidAskRepository streamBidAskRepository;

    public StreamFunctionalService(StreamRepository streamRepository, StreamBidAskRepository streamBidAskRepository) {
        this.streamRepository = streamRepository;
        this.streamBidAskRepository = streamBidAskRepository;
    }

    public StreamEntity save(StreamEntity streamEntity) {
        return streamRepository.save(streamEntity);
    }

    public StreamBidAskEntity save(StreamBidAskEntity streamBidAskEntity) {
        return streamBidAskRepository.save(streamBidAskEntity);
    }
}

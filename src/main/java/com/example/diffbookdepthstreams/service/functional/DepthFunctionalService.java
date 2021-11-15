package com.example.diffbookdepthstreams.service.functional;

import com.example.diffbookdepthstreams.config.ApplicationConfiguration;
import com.example.diffbookdepthstreams.dto.DepthResponseDto;
import com.example.diffbookdepthstreams.util.RestClient;
import org.springframework.stereotype.Service;

@Service
public class DepthFunctionalService {

    private final RestClient restClient;

    public DepthFunctionalService(RestClient restClient) {
        this.restClient = restClient;
    }

    public DepthResponseDto getDepth() {
        return restClient.getForObject(ApplicationConfiguration.getBinanceDepthUrl(), DepthResponseDto.class);
    }
}

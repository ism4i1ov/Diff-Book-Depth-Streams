package com.example.diffbookdepthstreams.service.business;

import com.example.diffbookdepthstreams.dto.DepthResponseDto;
import com.example.diffbookdepthstreams.dto.StreamDataResponseDto;
import com.example.diffbookdepthstreams.entity.StreamBidAskEntity;
import com.example.diffbookdepthstreams.entity.StreamEntity;
import com.example.diffbookdepthstreams.service.functional.DepthFunctionalService;
import com.example.diffbookdepthstreams.service.functional.StreamFunctionalService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepthBusinessService {

    private Long finalUpdateIdPrev;

    private final DepthFunctionalService depthFunctionalService;
    private final StreamFunctionalService streamFunctionalService;

    public DepthBusinessService(DepthFunctionalService depthFunctionalService, StreamFunctionalService streamFunctionalService) {
        this.depthFunctionalService = depthFunctionalService;
        this.streamFunctionalService = streamFunctionalService;
    }

    public boolean startValidationProcess(StreamDataResponseDto dataResponseDto) {
        if (checkLastUpdateId(dataResponseDto.getFinalUpdateId(), dataResponseDto.getFirstUpdateId())) {
            if (finalUpdateIdPrev == null) {
                finalUpdateIdPrev = dataResponseDto.getFinalUpdateId();
                return true;
            } else {
                System.err.println(dataResponseDto);
                boolean result = dataResponseDto.getFinalUpdateIdLastStream().equals(finalUpdateIdPrev);
                finalUpdateIdPrev = dataResponseDto.getFinalUpdateId();
                return result;
            }
        }
        return false;
    }

    public boolean checkLastUpdateId(Long finalUpdateId, Long firstUpdateId) {
        DepthResponseDto depth = depthFunctionalService.getDepth();
//        return depth.getLastUpdateId() >= firstUpdateId
//                && finalUpdateId >= depth.getLastUpdateId(); <- единственное что не понял
        return true;
    }

    public void removeQuantityZero(StreamDataResponseDto dto) {
        List<List<String>> bids = dto.getBidsBeUpdate()
                .stream()
                .filter(data -> !data.get(1).equalsIgnoreCase("0"))
                .collect(Collectors.toList());
        List<List<String>> asks = dto.getAsksBeUpdate()
                .stream()
                .filter(data -> !data.get(1).equalsIgnoreCase("0"))
                .collect(Collectors.toList());
        dto.setB(bids)
                .setA(asks);
    }

    public void validateAndSave(StreamDataResponseDto dto) {
        if (startValidationProcess(dto)) {
            StreamEntity save = streamFunctionalService.save(StreamDataResponseDto.toEntity(dto));
            removeQuantityZero(dto);
            streamFunctionalService.save(new StreamBidAskEntity().setAsks(new Gson().toJson(dto.getAsksBeUpdate()))
                    .setBids(new Gson().toJson(dto.getBidsBeUpdate()))
                    .setStreamEntity(save));
        }
    }
}

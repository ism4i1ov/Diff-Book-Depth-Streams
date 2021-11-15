package com.example.diffbookdepthstreams.controller;

import com.example.diffbookdepthstreams.enums.BusinessStatus;
import com.example.diffbookdepthstreams.enums.MillisecondsTypes;
import com.example.diffbookdepthstreams.service.functional.StartStreamFunctionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("diff/depth")
public class StartController {

    private final StartStreamFunctionalService startStreamFunctionalService;

    public StartController(StartStreamFunctionalService startStreamFunctionalService) {
        this.startStreamFunctionalService = startStreamFunctionalService;
    }

    @GetMapping("/start/{ms}")
    public CommonResponse<Object> startDepth(@PathVariable("ms") String ms) throws ExecutionException, InterruptedException {
        MillisecondsTypes.searchMilliseconds(ms);
        startStreamFunctionalService.startDiffSocketClient(ms);
        return CommonResponse.response(null, BusinessStatus.DEFAULT_SUCCESS, "Success");
    }
}

package com.example.diffbookdepthstreams.service.functional;

import com.example.diffbookdepthstreams.websocket.DiffSocketClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class StartStreamFunctionalService {
    private final DiffSocketClient diffSocketClient;

    public StartStreamFunctionalService(DiffSocketClient diffSocketClient) {
        this.diffSocketClient = diffSocketClient;
    }

    public void startDiffSocketClient(String ms) throws ExecutionException, InterruptedException {
        diffSocketClient.connect(ms);
    }
}

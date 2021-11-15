package com.example.diffbookdepthstreams.service.functional;

import com.example.diffbookdepthstreams.entity.ErrorEntity;
import com.example.diffbookdepthstreams.repository.ErrorRepository;
import org.springframework.stereotype.Service;

@Service
public class ErrorFunctionalService {

    private final ErrorRepository errorRepository;

    public ErrorFunctionalService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    public void loggingErrorDb(ErrorEntity errorEntity) {
        errorRepository.save(errorEntity);
    }
}

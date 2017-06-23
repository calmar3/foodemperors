package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.repository.BatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Caim03 on 23/06/17.
 */

@Service
public class BatchService {

    @Inject
    private BatchRepository batchRepository;

    public List<Batch> searchAll() {
        return batchRepository.findAll();
    }
}

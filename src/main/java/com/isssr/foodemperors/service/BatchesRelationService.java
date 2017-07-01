package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.BatchesRelation;
import com.isssr.foodemperors.repository.BatchesRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 23/06/17.
 */
@Service
public class BatchesRelationService {

    @Inject
    BatchesRelationRepository batchesRelationRepository;

    public BatchesRelation saveBatch(BatchesRelation batchesRelation) {
        return batchesRelationRepository.save(batchesRelation);
    }


    public List<BatchesRelation> getBatchesRelation() {
        return batchesRelationRepository.findAll();
    }

    public List<BatchesRelation> getBatchesRelationByIdProd(String idProd) {


        List<BatchesRelation> allBatchesRelation = batchesRelationRepository.findAll();

        List<BatchesRelation> batchesRelationList = new ArrayList<>();

        for(BatchesRelation b : allBatchesRelation)
        {
            if(b.getBatch().getProduct().getId().equals(idProd))
                batchesRelationList.add(b);

        }

        return batchesRelationList;
    }


    public List<BatchesRelation> getBatchesRelationByNameProd(String nameProd) {


        List<BatchesRelation> allBatchesRelation = batchesRelationRepository.findAll();

        List<BatchesRelation> batchesRelationList = new ArrayList<>();

        for(BatchesRelation b : allBatchesRelation)
        {
            if(b.getBatch().getProduct().getName().equals(nameProd))
                batchesRelationList.add(b);

        }

        return batchesRelationList;
    }
}

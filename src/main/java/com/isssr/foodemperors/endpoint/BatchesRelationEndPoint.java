package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.BatchesRelation;
import com.isssr.foodemperors.model.Catalogue;
import com.isssr.foodemperors.repository.BatchRepository;
import com.isssr.foodemperors.repository.BatchesRelationRepository;
import com.isssr.foodemperors.service.BatchesRelationService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 23/06/17.
 */

@RestController
@CrossOrigin(origins = "*")
public class BatchesRelationEndPoint {


    @Inject
    BatchesRelationService batchesRelationService;

    @Inject
    BatchesRelationRepository batchesRelationRepository;

    @Inject
    BatchRepository batchRepository;

    @RequestMapping(path = "api/batchesRelation", method = RequestMethod.POST)
    public BatchesRelation saveBatch(@RequestBody BatchesRelation batchesRelation) {
        return batchesRelationService.saveBatch(batchesRelation);
    }

    @RequestMapping(path = "api/batchesRelation", method = RequestMethod.GET)
    public List<BatchesRelation> getBatchesRelation() {
        return batchesRelationService.getBatchesRelation();
    }
}

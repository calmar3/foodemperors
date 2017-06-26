package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.Catalogue;
import com.isssr.foodemperors.model.Commission;
import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.service.BatchService;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.BatchRepository;
import com.isssr.foodemperors.repository.CatalogueRepository;
import com.isssr.foodemperors.repository.CommissionRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by simone on 29/05/17.
 */


@RestController
@CrossOrigin(origins = "*")
public class BatchEndpoint {


    @Inject
    BatchService batchService;

    @Inject
    BatchRepository batchRepository;

    /**
     * Salva i batches aggiornati e restituisce il commissionDTO con la commission aggiornata
     */
    @RequestMapping(path = "api/batch/saveBatches", method = RequestMethod.POST)
    public CommissionDTO saveBatch(@RequestBody ArrayList<Batch> batches) {

        return batchService.saveBatch(batches);

    }


    @RequestMapping(path = "api/batch/getbatchesbyprod", method = RequestMethod.POST)
    public List<Batch> getBatchesByProd(@RequestBody Product product) {
        return batchService.getBatchesByProd(product);

    }

    @RequestMapping(path = "api/batch/sendBatches", method = RequestMethod.POST)
    public List<Batch> sendBatches(@RequestBody List<Batch> batches) {

        return batchService.sendBatches(batches);
    }
}

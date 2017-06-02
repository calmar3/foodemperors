package endpoint;

import dto.CommissionDTO;
import model.Batch;
import model.Commission;
import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.BatchRepository;
import repository.CommissionRepository;
import repository.ProductRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 29/05/17.
 */


@RestController
@CrossOrigin(origins = "*")
public class BatchEndpoint {


    @Inject
    private BatchRepository batchRepository;


    @Inject
    private CommissionRepository commissionRepository;

    /**
     *  Salva i batches aggiornati e restituisce il commissionDTO con la commission aggiornata
     */
    @RequestMapping(path = "api/saveBatches", method = RequestMethod.POST)
    public CommissionDTO saveBatch(@RequestBody ArrayList<Batch> batches) {


        for(Batch b : batches) {
            batchRepository.save(b);
        }


        Commission commission = commissionRepository.findOne(batches.get(0).getCommission().getId());

        List<Batch> allBatches = batchRepository.findByCommissionId(commission.getId());

        Boolean found = false;

        for(Batch b : allBatches)
        {
            if(b.getDelivered().equals("false"))
                found = true;

        }


        if(!found) {
            commission.setCompleted("true");
            commissionRepository.save(commission);


        }
        CommissionDTO cDTO = new CommissionDTO();
        cDTO.setCommission(commission);
        cDTO.setBatches(allBatches);
        return cDTO;
    }
}

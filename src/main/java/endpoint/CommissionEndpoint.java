package endpoint;

import dto.CommissionDTO;
import model.Batch;
import model.Commission;
import org.springframework.web.bind.annotation.*;
import repository.BatchRepository;
import repository.CommissionRepository;

import javax.inject.Inject;

/**
 * Created by marco on 26/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CommissionEndpoint {

    @Inject
    private CommissionRepository commissionRepository;

    @Inject
    private BatchRepository batchRepository;

    @RequestMapping(path = "api/commission", method = RequestMethod.POST)
    public Commission saveCommission(@RequestBody CommissionDTO commissionDTO) {
        /**
         * Control over commission fields
         */
        Commission comm = commissionRepository.save(commissionDTO.getCommission());
        for (Batch batch: commissionDTO.getBatches()){
            batch.setCommission(comm);
            batchRepository.save(batch);
        }
        return comm;
    }


    @RequestMapping(path = "api/commission/findby/number/{number}", method = RequestMethod.GET)
    public Commission searchCommissionByNumber(@PathVariable int number) {
        return commissionRepository.findByNumber(String.valueOf(number));

    }


}

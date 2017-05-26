package endpoint;

import dto.CommissionDTO;
import model.Batch;
import model.Commission;
import org.springframework.web.bind.annotation.*;
import repository.BatchRepository;
import repository.CommissionRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    public CommissionDTO searchCommissionByNumber(@PathVariable int number) {
        Commission commission = commissionRepository.findByNumber(String.valueOf(number));
        CommissionDTO commissionDTO = new CommissionDTO();
        commissionDTO.setCommission(commission);
        commissionDTO.setBatches(batchRepository.findByCommissionId(commission.getId()));
        return commissionDTO;

    }

    @RequestMapping(path = "api/commissions", method = RequestMethod.GET)
    public List<CommissionDTO> getAllCommissions() {
        List<Commission> commissions = commissionRepository.findAll();
        List<CommissionDTO> dtoCommissions = new ArrayList<>();
        for (Commission cms: commissions){
            CommissionDTO cDTO = new CommissionDTO();
            cDTO.setCommission(cms);
            cDTO.setBatches(batchRepository.findByCommissionId(cms.getId()));
            dtoCommissions.add(cDTO);
        }
        return dtoCommissions;
    }



}

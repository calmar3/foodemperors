package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.Commission;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.BatchRepository;
import com.isssr.foodemperors.repository.CommissionRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(path = "api/commission", method = RequestMethod.PUT)
    public Commission updateCommission(@RequestBody CommissionDTO commissionDTO) {
        Commission comm = commissionRepository.save(commissionDTO.getCommission());
        List<Batch> batches = batchRepository.findByCommissionId(comm.getId());
        for (Batch batch: commissionDTO.getBatches()){
            if (batch.getCommission() == null)
                batch.setCommission(comm);
            batchRepository.save(batch);
        }
        for (Batch batch : batches){
            boolean found = false;
            for (int i = 0 ; i < commissionDTO.getBatches().size() && !found; i++){
                found = (batch.getId().equals(commissionDTO.getBatches().get(i).getId()));
            }
            if (!found)
                batchRepository.deleteById(batch.getId());
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

    @RequestMapping(path = "api/commission/{id}", method = RequestMethod.DELETE)
    public Long deleteCommission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

        batchRepository.deleteByCommissionId(id);
        Long commission =  commissionRepository.deleteById(id);
        if (commission == 0){
            response.setStatus(404);
            return null;
        }
        return commission;
    }



}

package com.isssr.foodemperors.service;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.Commission;
import com.isssr.foodemperors.repository.BatchRepository;
import com.isssr.foodemperors.repository.CommissionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 20/06/17.
 */
@Service
public class CommissionService{

    @Inject
    private CommissionRepository commissionRepository;

    @Inject
    private BatchRepository batchRepository;


    public Commission saveCommission(CommissionDTO commissionDTO) {

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

    public Commission updateCommission(CommissionDTO commissionDTO) {
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
        return comm;    }

    public CommissionDTO searchCommissionByNumber(int number) {
        Commission commission = commissionRepository.findByNumber(String.valueOf(number));
        CommissionDTO commissionDTO = new CommissionDTO();
        commissionDTO.setCommission(commission);
        commissionDTO.setBatches(batchRepository.findByCommissionId(commission.getId()));
        return commissionDTO;    }

    public List<CommissionDTO> getAllCommissions() {
        List<Commission> commissions = commissionRepository.findAll();
        List<CommissionDTO> dtoCommissions = new ArrayList<>();
        for (Commission cms: commissions){
            CommissionDTO cDTO = new CommissionDTO();
            cDTO.setCommission(cms);
            cDTO.setBatches(batchRepository.findByCommissionId(cms.getId()));
            dtoCommissions.add(cDTO);

        }
        return dtoCommissions;    }

    public Long deleteCommission(String id, HttpServletRequest request, HttpServletResponse response) {
        batchRepository.deleteByCommissionId(id);
        Long commission =  commissionRepository.deleteById(id);
        if (commission == 0){
            response.setStatus(404);
            return null;
        }
        return commission;    }
}

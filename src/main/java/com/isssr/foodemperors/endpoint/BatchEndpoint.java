package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.service.BatchService;
import com.isssr.foodemperors.service.PeripheralWarehouseService;
import com.isssr.foodemperors.service.RequestService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simone on 29/05/17.
 */


@RestController
@CrossOrigin(origins = "*")
//TODO check users permissions
public class BatchEndpoint {


    @Inject
    private BatchService batchService;

    @Inject
    private TokenService tokenService;

    @Inject
    private PeripheralWarehouseService peripheralWarehouseService;

    @Inject
    private RequestService requestService;

    /**
     *  Salva i batches aggiornati e restituisce il commissionDTO con la commission aggiornata
     */
    @RequestMapping(path = "api/batch/saveBatches", method = RequestMethod.POST)
    public CommissionDTO saveBatch(@RequestBody ArrayList<Batch> batches, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchService.saveBatch(batches);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/batch/getbatchesbyprod", method = RequestMethod.POST)
    public List<Batch> getBatchesByProd(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchService.getBatchesByProd(product);
        else{
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/batch/sendBatches", method = RequestMethod.POST)
    public List<Batch> sendBatches(@RequestBody List<Batch> batches, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman"))){
            CommissionDTO commissionDTO = batchService.sendBatches(batches);
            String host = "http://" + peripheralWarehouseService.findAddressByName(commissionDTO.getCommission().getSource()) + ":8080/api/mc/commission";
            requestService.sendCommissionDTO(commissionDTO,host);
            return commissionDTO.getBatches();
        }
        else{
            response.setStatus(401);
            return null;
        }
    }


    @RequestMapping(path = "api/batches/expiring", method = RequestMethod.GET)
    public List<Batch> getExpiringBatches(HttpServletRequest request, HttpServletResponse response){
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchService.getExpiringBatches();
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/batches", method = RequestMethod.PUT)
    public List<Batch> updateBatches(@RequestBody List<Batch> batches, HttpServletRequest request, HttpServletResponse response){
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchService.updateBatches(batches);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/batch/getallbatches", method = RequestMethod.GET)
    public List<Batch> getAllBatches(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchService.getAllBatches();
        else{
            response.setStatus(401);
            return null;
        }
    }

}

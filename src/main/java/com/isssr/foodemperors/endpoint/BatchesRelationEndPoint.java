package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.BatchesRelation;
import com.isssr.foodemperors.service.BatchesRelationService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by simone on 23/06/17.
 */
//TODO verify users permissions
@RestController
@CrossOrigin(origins = "*")
public class BatchesRelationEndPoint {


    @Inject
    private BatchesRelationService batchesRelationService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/batchesRelation", method = RequestMethod.POST)
    public BatchesRelation saveBatch(@RequestBody BatchesRelation batchesRelation, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchesRelationService.saveBatch(batchesRelation);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/batchesRelation", method = RequestMethod.GET)
    public List<BatchesRelation> getBatchesRelation(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return batchesRelationService.getBatchesRelation();
        else{
            response.setStatus(401);
            return null;
        }
    }


    //API PER DATA ANALISYS
    @RequestMapping(path = "api/dataAnalysis/allBatchesRelation", method = RequestMethod.GET)
    public List<BatchesRelation> getAllBatchesRelations() {
        return batchesRelationService.getBatchesRelation();
    }


    //API PER DATA ANALISYS
    @RequestMapping(path = "api/dataAnalysis/batchesRelationByIdProd/{idProd}", method = RequestMethod.GET)
    public List<BatchesRelation> getBatchesRelationIdByProd(@PathVariable String idProd) {
        return batchesRelationService.getBatchesRelationByIdProd(idProd);
    }


    //API PER DATA ANALISYS
    @RequestMapping(path = "api/dataAnalysis/batchesRelationByNameProd/{nameProd}", method = RequestMethod.GET)
    public List<BatchesRelation> getBatchesRelationByNameProd(@PathVariable String nameProd) {
        return batchesRelationService.getBatchesRelationByNameProd(nameProd);
    }


}

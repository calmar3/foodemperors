package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Commission;
import com.isssr.foodemperors.service.CommissionService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by marco on 26/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CommissionEndpoint {

    @Inject
    private CommissionService commissionService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/commission", method = RequestMethod.POST)
    public Commission saveCommission(@RequestBody CommissionDTO commissionDTO,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return  commissionService.saveCommission(commissionDTO.getCommission(),commissionDTO.getBatches());
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/commission", method = RequestMethod.PUT)
    public Commission updateCommission(@RequestBody CommissionDTO commissionDTO,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return commissionService.updateCommission(commissionDTO.getCommission(),commissionDTO.getBatches());
        else{
            response.setStatus(401);
            return null;
        }
    }


    @RequestMapping(path = "api/commission/findby/number/{number}", method = RequestMethod.GET)
    public CommissionDTO searchCommissionByNumber(@PathVariable int number,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return commissionService.searchCommissionByNumber(String.valueOf(number));
        else{
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/commissions", method = RequestMethod.GET)
    public List<CommissionDTO> getAllCommissions(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return commissionService.getAllCommissions();
        else{
            response.setStatus(401);
            return null;
        }
    }


    @RequestMapping(path = "api/commission/{id}", method = RequestMethod.DELETE)
    public Long deleteCommission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return commissionService.deleteCommission(id);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/pos/commission", method = RequestMethod.POST)
    public CommissionDTO createIncomingCommission(@RequestBody CommissionDTO commissionDTO,HttpServletRequest request, HttpServletResponse response){
        CommissionDTO responseDTO = commissionService.createIncomingCommission(commissionDTO.getCommission(),commissionDTO.getBatches());
        if (responseDTO == null){
            response.setStatus(403);
            return null;
        }
        return responseDTO;
    }


}

package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.PeripheralWarehouseDTO;
import com.isssr.foodemperors.model.PeripheralWarehouse;
import com.isssr.foodemperors.service.PeripheralWarehouseService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 29/05/17.
 */
//TODO verify users permissions
@RestController
@CrossOrigin(origins = "*")
public class PeripheralWarehouseEndpoint {

    @Inject
    private TokenService tokenService;

    @Inject
    private PeripheralWarehouseService peripheralWarehouseService;

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.POST)
    public PeripheralWarehouse savePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return peripheralWarehouseService.savePeripheralWarehouse(peripheralWarehouseDTO.getPeripheralWarehouse());
        else{
            response.setStatus(401);
            return null;
        }
    }


    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.PUT)
    public PeripheralWarehouse updatePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return peripheralWarehouseService.updatePeripheralWarehouse(peripheralWarehouseDTO.getPeripheralWarehouse());
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.GET)
    public List<PeripheralWarehouseDTO> searchAll(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return peripheralWarehouseService.findAllPW();
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/peripheralWarehouse/findby/ID/{ID}", method = RequestMethod.GET)
    public PeripheralWarehouseDTO searchPeripheralWarehouse(@PathVariable int id,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return peripheralWarehouseService.findPWById(String.valueOf(id));
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/peripheralWarehouse/{id}", method = RequestMethod.DELETE)
    public Long deleteCommission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman"))){
            Long peripheralWarehouse =  peripheralWarehouseService.deletePWById(id);
            if (peripheralWarehouse == 0){
                response.setStatus(404);
                return null;
            }
            return peripheralWarehouse;
        }
        else{
            response.setStatus(401);
            return null;
        }

    }

}
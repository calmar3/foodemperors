package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.ExternalSupplier;
import com.isssr.foodemperors.service.ExternalSupplierService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by marco on 26/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class ExternalSupplierEndpoint {

    @Inject
    private ExternalSupplierService externalSupplierService;

    @Inject
    private TokenService tokenService;


    @RequestMapping(path = "api/supplier", method = RequestMethod.POST)
    public ExternalSupplier saveExternalSupplier(@RequestBody ExternalSupplier externalSupplier, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return externalSupplierService.saveExternalSupllier(externalSupplier);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/supplier", method = RequestMethod.PUT)
    public ExternalSupplier updateExternalSupplier(@RequestBody ExternalSupplier externalSupplier, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")))
            return externalSupplierService.saveExternalSupllier(externalSupplier);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/supplier/{id}", method = RequestMethod.DELETE)
    public Long deleteExternalSupplier(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin"))){
            if (externalSupplierService.deleteExternalSupplier(id)){
                response.setStatus(404);
                return null;
            }
            return 0L;
        }
        else{
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/suppliers", method = RequestMethod.GET)
    public List<ExternalSupplier> getAllExternalSuppliers( HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return externalSupplierService.getAllExternalSuppliers();
        else{
            response.setStatus(401);
            return null;
        }
    }
}

package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.ExternalSupplier;
import com.isssr.foodemperors.service.ExternalSupplierService;
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


    @RequestMapping(path = "api/supplier", method = RequestMethod.POST)
    public ExternalSupplier saveExternalSupplier(@RequestBody ExternalSupplier externalSupplier) {
        return externalSupplierService.saveExternalSupllier(externalSupplier);
    }

    @RequestMapping(path = "api/supplier", method = RequestMethod.PUT)
    public ExternalSupplier updateExternalSupplier(@RequestBody ExternalSupplier externalSupplier) {
        return externalSupplierService.saveExternalSupllier(externalSupplier);
    }

    @RequestMapping(path = "api/supplier/{id}", method = RequestMethod.DELETE)
    public Long deleteExternalSupplier(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

        if (externalSupplierService.deleteExternalSupplier(id)){
            response.setStatus(404);
            return null;
        }
        return 0L;
    }

    @RequestMapping(path = "api/suppliers", method = RequestMethod.GET)
    public List<ExternalSupplier> getAllExternalSuppliers() {
        return externalSupplierService.getAllExternalSuppliers();
    }
}

package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.ExternalSupplier;
import com.isssr.foodemperors.repository.ExternalSupplierRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by marco on 26/06/17.
 */
@Service
public class ExternalSupplierService {

    @Inject
    private ExternalSupplierRepository externalSupplierRepository;

    public ExternalSupplier saveExternalSupllier(ExternalSupplier externalSupplier){
        return externalSupplierRepository.save(externalSupplier);
    }

    public List<ExternalSupplier> getAllExternalSuppliers(){
        return externalSupplierRepository.findAll();
    }

    public boolean deleteExternalSupplier(String id) {
        return (externalSupplierRepository.deleteById(id) != 0) ;
    }
}

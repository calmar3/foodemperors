package com.isssr.foodemperors.service;

import com.isssr.foodemperors.dto.PeripheralWarehouseDTO;
import com.isssr.foodemperors.model.PeripheralWarehouse;
import com.isssr.foodemperors.repository.PeripheralWarehouseRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morga on 21/06/2017.
 */
@Service
public class PeripheralWarehouseService{
    @Inject
    private PeripheralWarehouseRepository peripheralWarehouseRepository;

    public PeripheralWarehouse savePeripheralWarehouse(PeripheralWarehouse peripheralWarehouse) {
        PeripheralWarehouse pW = peripheralWarehouseRepository.save(peripheralWarehouse);
        return pW;
    }

    public PeripheralWarehouse updatePeripheralWarehouse(PeripheralWarehouse peripheralWarehouse) {
        PeripheralWarehouse pW = peripheralWarehouseRepository.save(peripheralWarehouse);
        return pW;
    }

    public List<PeripheralWarehouseDTO> findAllPW(){
        List<PeripheralWarehouse> peripheralWarehouses = peripheralWarehouseRepository.findAll();
        List<PeripheralWarehouseDTO> dtoPeripheralWarehouses = new ArrayList<>();
        for (PeripheralWarehouse pwh: peripheralWarehouses){
            PeripheralWarehouseDTO pDTO = new PeripheralWarehouseDTO();
            pDTO.setPeripheralWarehouse(pwh);
            dtoPeripheralWarehouses.add(pDTO);
        }
        return dtoPeripheralWarehouses;
    }

    public Long deletePWById(String id){
        return peripheralWarehouseRepository.deleteById(id);
    }

    public PeripheralWarehouseDTO findPWById(String id){
        PeripheralWarehouse peripheralWarehouse = peripheralWarehouseRepository.findById(id);
        PeripheralWarehouseDTO peripheralWarehouseDTO = new PeripheralWarehouseDTO();
        peripheralWarehouseDTO.setPeripheralWarehouse(peripheralWarehouse);
        return peripheralWarehouseDTO;
    }

}

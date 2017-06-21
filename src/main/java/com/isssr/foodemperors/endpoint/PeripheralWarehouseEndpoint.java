package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.PeripheralWarehouseDTO;
import com.isssr.foodemperors.model.PeripheralWarehouse;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.PeripheralWarehouseRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 29/05/17.
 */
@RestController
@CrossOrigin(origins ="*")

public class PeripheralWarehouseEndpoint {

    @Inject
    private PeripheralWarehouseRepository peripheralWarehouseRepository;


    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.POST)
    public PeripheralWarehouse savePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO) {
        /**
         * Control over PeripheralWarehouse fields
         */
    	PeripheralWarehouse peripheralWarehouse = peripheralWarehouseRepository.save(peripheralWarehouseDTO.getPeripheralWarehouse());
        return peripheralWarehouse;
    }

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.PUT)
    public PeripheralWarehouse updatePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO) {
    	PeripheralWarehouse peripheralWarehouse = peripheralWarehouseRepository.save(peripheralWarehouseDTO.getPeripheralWarehouse());
        return peripheralWarehouse;
    }


    @RequestMapping(path = "api/peripheralWarehouse/findby/ID/{ID}", method = RequestMethod.GET)
    public PeripheralWarehouseDTO searchPeripheralWarehouseByID(@PathVariable int ID) {
    	PeripheralWarehouse peripheralWarehouse = peripheralWarehouseRepository.findById(String.valueOf(ID));
    	PeripheralWarehouseDTO peripheralWarehouseDTO = new PeripheralWarehouseDTO();
    	peripheralWarehouseDTO.setPeripheralWarehouse(peripheralWarehouse);
        return peripheralWarehouseDTO;

    }

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.GET)
    public List<PeripheralWarehouseDTO> getAllPeripheralWarehouse() {
        List<PeripheralWarehouse> peripheralWarehouse = peripheralWarehouseRepository.findAll();
        List<PeripheralWarehouseDTO> dtoPeripheralWarehouse = new ArrayList<>();
        for (PeripheralWarehouse pws: peripheralWarehouse){
        	PeripheralWarehouseDTO pwDTO = new PeripheralWarehouseDTO();
            pwDTO.setPeripheralWarehouse(pws);
            dtoPeripheralWarehouse.add(pwDTO);
        }
        return dtoPeripheralWarehouse;
    }

    @RequestMapping(path = "api/peripheralWarehouse/{id}", method = RequestMethod.DELETE)
    public Long deletePeripheralWarehouse(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

        Long peripheralWarehouse =  peripheralWarehouseRepository.deleteById(id);
        if (peripheralWarehouse == 0){
            response.setStatus(404);
            return null;
        }
        return peripheralWarehouse;
    }



}
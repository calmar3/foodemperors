package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.PeripheralWarehouseDTO;
import com.isssr.foodemperors.model.PeripheralWarehouse;
import com.isssr.foodemperors.service.PeripheralWarehouseService;
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
@CrossOrigin(origins = "*")
public class PeripheralWarehouseEndpoint {

    @Inject
    private PeripheralWarehouseRepository peripheralWarehouseRepository;

    @Inject
    private PeripheralWarehouseService peripheralWarehouseService;

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.POST)
    public PeripheralWarehouse savePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO) {
        return peripheralWarehouseService.savePeripheralWarehouse(peripheralWarehouseDTO.getPeripheralWarehouse());
    }


    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.PUT)
    public PeripheralWarehouse updatePeripheralWarehouse(@RequestBody PeripheralWarehouseDTO peripheralWarehouseDTO) {
        return peripheralWarehouseService.updatePeripheralWarehouse(peripheralWarehouseDTO.getPeripheralWarehouse());
    }

    @RequestMapping(path = "api/peripheralWarehouse", method = RequestMethod.GET)
    public List<PeripheralWarehouseDTO> searchAll() {
        List<PeripheralWarehouse> peripheralWarehouses = peripheralWarehouseRepository.findAll();
        List<PeripheralWarehouseDTO> dtoPeripheralWarehouses = new ArrayList<>();
        for (PeripheralWarehouse pwh: peripheralWarehouses){
            PeripheralWarehouseDTO pDTO = new PeripheralWarehouseDTO();
            pDTO.setPeripheralWarehouse(pwh);
            dtoPeripheralWarehouses.add(pDTO);
        }
        return dtoPeripheralWarehouses;
    }

    @RequestMapping(path = "api/peripheralWarehouse/findby/ID/{ID}", method = RequestMethod.GET)
    public PeripheralWarehouseDTO searchPeripheralWarehouse(@PathVariable int id) {
        PeripheralWarehouse peripheralWarehouse = peripheralWarehouseRepository.findById(String.valueOf(id));
        PeripheralWarehouseDTO peripheralWarehouseDTO = new PeripheralWarehouseDTO();
        peripheralWarehouseDTO.setPeripheralWarehouse(peripheralWarehouse);
        return peripheralWarehouseDTO;
    }

    @RequestMapping(path = "api/peripheralWarehouse/{id}", method = RequestMethod.DELETE)
    public Long deleteCommission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        Long peripheralWarehouse =  peripheralWarehouseRepository.deleteById(id);
        if (peripheralWarehouse == 0){
            response.setStatus(404);
            return null;
        }
        return peripheralWarehouse;
    }

}
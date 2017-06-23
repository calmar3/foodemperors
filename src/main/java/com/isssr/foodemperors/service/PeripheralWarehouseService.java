package com.isssr.foodemperors.service;

import com.isssr.foodemperors.dto.PeripheralWarehouseDTO;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isssr.foodemperors.model.PeripheralWarehouse;
import com.isssr.foodemperors.repository.PeripheralWarehouseRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}

package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.DeliveryNote;
import com.isssr.foodemperors.service.DeliveryNoteService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Caim03 on 23/06/17.
 */

@RestController
@CrossOrigin(origins = "*")
public class DeliveryNoteEndPoint {

    @Inject
    private DeliveryNoteService deliveryNoteService;

    @RequestMapping(path = "api/delivery", method = RequestMethod.GET)
    public List<DeliveryNote> searchAll() {
        return deliveryNoteService.searchAll();
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.POST)
    public DeliveryNote saveDeliveryNote(@RequestBody DeliveryNote deliveryNote) {
        return deliveryNoteService.saveDeliveryNote(deliveryNote);
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.PUT)
    public DeliveryNote updateDeliveryNote(@RequestBody DeliveryNote deliveryNote) {
        return deliveryNoteService.updateDeliveryNote(deliveryNote);
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.DELETE)
    public DeliveryNote deleteDeliveryNote(@RequestBody DeliveryNote deliveryNote) {
        return deliveryNoteService.deleteDeliveryNote(deliveryNote);
    }

    @RequestMapping(path = "api/delivery/findBy/id/{id}", method = RequestMethod.GET)
    public DeliveryNote searchById(@RequestParam String id) {
        return deliveryNoteService.searchById(id);
    }
}

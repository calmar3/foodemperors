package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.DeliveryNote;
import com.isssr.foodemperors.service.DeliveryNoteService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Caim03 on 23/06/17.
 */
//TODO verify users permissions
@RestController
@CrossOrigin(origins = "*")
public class DeliveryNoteEndPoint {

    @Inject
    private DeliveryNoteService deliveryNoteService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/delivery", method = RequestMethod.GET)
    public List<DeliveryNote> searchAll(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return deliveryNoteService.searchAll();
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.POST)
    public DeliveryNote saveDeliveryNote(@RequestBody DeliveryNote deliveryNote, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return deliveryNoteService.saveDeliveryNote(deliveryNote);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.PUT)
    public DeliveryNote updateDeliveryNote(@RequestBody DeliveryNote deliveryNote, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return deliveryNoteService.updateDeliveryNote(deliveryNote);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/delivery", method = RequestMethod.DELETE)
    public DeliveryNote deleteDeliveryNote(@RequestBody DeliveryNote deliveryNote, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return deliveryNoteService.deleteDeliveryNote(deliveryNote);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/delivery/findBy/id/{id}", method = RequestMethod.GET)
    public DeliveryNote searchById(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return deliveryNoteService.searchById(id);
        else{
            response.setStatus(401);
            return null;
        }
    }
}

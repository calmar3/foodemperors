package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.DeliveryNote;
import com.isssr.foodemperors.repository.DeliveryNoteRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Caim03 on 23/06/17.
 */

@Service
public class DeliveryNoteService {

    @Inject
    private DeliveryNoteRepository deliveryNoteRepository;

    public List<DeliveryNote> searchAll() {
        return deliveryNoteRepository.findAll();
    }

    public DeliveryNote searchById(String id) {
        return deliveryNoteRepository.findById(id);
    }

    public DeliveryNote saveDeliveryNote(DeliveryNote deliveryNote) {
        return deliveryNoteRepository.save(deliveryNote);
    }

    public DeliveryNote updateDeliveryNote(DeliveryNote deliveryNote) {
        return deliveryNoteRepository.save(deliveryNote);
    }

    public DeliveryNote deleteDeliveryNote(DeliveryNote deliveryNote) {
        deliveryNoteRepository.delete(deliveryNote);
        return deliveryNote;
    }
}

package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.DeliveryNote;
import com.isssr.foodemperors.repository.BatchRepository;
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

    @Inject
    private BatchRepository batchRepository;

    public List<DeliveryNote> searchAll() {
        return deliveryNoteRepository.findAll();
    }

    public DeliveryNote searchById(String id) {
        return deliveryNoteRepository.findById(id);
    }

    public DeliveryNote saveDeliveryNote(DeliveryNote deliveryNote) {
        DeliveryNote saveDelivery = deliveryNoteRepository.save(deliveryNote);
        for (Batch batch : deliveryNote.getBatches()) {
            batchRepository.save(batch);
        }

        return saveDelivery;
    }

    public DeliveryNote updateDeliveryNote(DeliveryNote deliveryNote) {
        return deliveryNoteRepository.save(deliveryNote);
    }

    public DeliveryNote deleteDeliveryNote(DeliveryNote deliveryNote) {
        deliveryNoteRepository.delete(deliveryNote);
        return deliveryNote;
    }
}

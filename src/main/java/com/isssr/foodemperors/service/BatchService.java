package com.isssr.foodemperors.service;

import com.isssr.foodemperors.dto.CommissionDTO;
import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.model.Catalogue;
import com.isssr.foodemperors.model.Commission;
import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.repository.BatchRepository;
import com.isssr.foodemperors.repository.CatalogueRepository;
import com.isssr.foodemperors.repository.CommissionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by simone on 20/06/17.
 */
@Service
public class BatchService {

    @Inject
    private BatchRepository batchRepository;


    @Inject
    private CommissionRepository commissionRepository;

    @Inject
    private CatalogueRepository catalogueRepository;


    public CommissionDTO saveBatch(ArrayList<Batch> batches) {
        for (Batch b : batches) {
            batchRepository.save(b);
        }


        Commission commission = commissionRepository.findById(batches.get(0).getCommission().getId());

        List<Batch> allBatches = batchRepository.findByCommissionId(commission.getId());

        Boolean found = false;

        for (Batch b : allBatches) {
                if(!b.isDelivered())
                    found = true;
        }


        if (!found) {
            commission.setCompleted("true");
            commissionRepository.save(commission);
        }

        CommissionDTO cDTO = new CommissionDTO(commission,allBatches);

        for (Batch b : batches) {

            Catalogue catalogue = catalogueRepository.findById(b.getProduct().getId());

            if (catalogue == null)
                catalogue = new Catalogue(b.getProduct().getId(),b.getProduct(),0);

            int qt = catalogue.getQuantity();
            catalogue.setQuantity(qt + b.getQuantity());

            catalogueRepository.save(catalogue);


        }

        return cDTO;
    }

    public List<Batch> getBatchesByProd(Product product) {
        List<Batch> batchList = batchRepository.findByProductId(product.getId());

        ArrayList<Batch> whisper = new ArrayList<>();

        Iterator<Batch> iter = batchList.iterator();
        while (iter.hasNext()) {
            Batch b = iter.next();

            if (b.getRemaining() != null)
                if (b.getCommission().getDestination().equals("FoodEmperors") && b.getRemaining() >= 0)
                    whisper.add(b);

        }
        return whisper;
    }

    public List<Batch> sendBatches(List<Batch> batches) {

        List<Batch> ourBatches = new ArrayList<>();
        List<Batch> outBatches = new ArrayList<>();

        int i = 0;
        for(Batch b : batches){

            if(i%2 == 0)
                ourBatches.add(b);
            else
                outBatches.add(b);

            i++;
        }



        //Aggiorna batch in uscita + Diminuisce catalogo di quella quantità
        for(Batch b : outBatches){


            batchRepository.save(b);

            Catalogue c = catalogueRepository.findByProductId(b.getProduct().getId());

            c.setQuantity(c.getQuantity() - b.getQuantity());
            catalogueRepository.save(c);
        }


        //2)diminuisci quantità batch nostro

        for(Batch b : ourBatches){

            batchRepository.save(b);
        }



        //3)Controlla se l'ordine è completo
        Commission commission = outBatches.get(0).getCommission();
        List<Batch> allCommissionBatches = batchRepository.findByCommissionId(commission.getId());


        boolean found = false;
        for(Batch b : allCommissionBatches)
        {
            System.out.println(b.getDelivered());
            if(!b.getDelivered().equals("true") && !b.getDelivered().equals("ready"))
                found = true;

            System.out.println(found);

        }

        if(!found)
        {
            commission.setCompleted("true");
            commissionRepository.save(commission);
        }


        return null;






    }
}

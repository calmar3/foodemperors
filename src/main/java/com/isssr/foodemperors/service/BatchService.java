package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.Batch;
import com.isssr.foodemperors.repository.BatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marco on 23/06/17.
 */
@Service
public class BatchService {

    @Inject
    private BatchRepository batchRepository;

    public List<Batch> updateBatches(List<Batch> batches){
        return batchRepository.save(batches);
    }

    public List<Batch> getExpiringBatches(){

        Date toDay = null;
        Date expiration = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Calendar c = Calendar.getInstance();
            toDay = dateFormat.parse(dateFormat.format(c.getTime()));
            c.add(Calendar.DATE, 10);
            expiration = dateFormat.parse(dateFormat.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (toDay == null || expiration == null)
            return null;

        List<Batch> batches = batchRepository.findByRemainingGreaterThanEqual(1);
        for (int i = 0 ; i < batches.size() ; i++){
            try {
                Date compare = dateFormat.parse(batches.get(i).getExpDate());
                if (!(compare.after(toDay) && compare.before(expiration))){
                    batches.remove(i);
                    i--;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return batches;
    }
}

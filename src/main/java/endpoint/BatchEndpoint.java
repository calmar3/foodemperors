package endpoint;

import dto.CommissionDTO;
import model.Batch;
import model.Catalogue;
import model.Commission;
import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.BatchRepository;
import repository.CatalogueRepository;
import repository.CommissionRepository;
import repository.ProductRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by simone on 29/05/17.
 */


@RestController
@CrossOrigin(origins = "*")
public class BatchEndpoint {


    @Inject
    private BatchRepository batchRepository;


    @Inject
    private CommissionRepository commissionRepository;

    @Inject
    private CatalogueRepository catalogueRepository;

    /**
     *  Salva i batches aggiornati e restituisce il commissionDTO con la commission aggiornata
     */
    @RequestMapping(path = "api/batch/saveBatches", method = RequestMethod.POST)
    public CommissionDTO saveBatch(@RequestBody ArrayList<Batch> batches) {


        for(Batch b : batches) {
            batchRepository.save(b);
        }


        Commission commission = commissionRepository.findById(batches.get(0).getCommission().getId());

        List<Batch> allBatches = batchRepository.findByCommissionId(commission.getId());

        Boolean found = false;

        for(Batch b : allBatches)
        {
            if(b.getDelivered().equals("false"))
                found = true;

        }


        if(!found) {
            commission.setCompleted("true");
            commissionRepository.save(commission);


        }
        CommissionDTO cDTO = new CommissionDTO();
        cDTO.setCommission(commission);
        cDTO.setBatches(allBatches);

        for(Batch b : batches)
        {

            Catalogue catalogue = catalogueRepository.findById(b.getProduct().getId());

            if(catalogue == null) {
                catalogue = new Catalogue();
                catalogue.setId(b.getProduct().getId());
                catalogue.setProduct(b.getProduct());
                catalogue.setQuantity(0);
            }
            int qt = catalogue.getQuantity();
            catalogue.setQuantity(qt+b.getQuantity());

            catalogueRepository.save(catalogue);



        }

        return cDTO;
    }


    @RequestMapping(path = "api/batch/getbatchesbyprod", method = RequestMethod.POST)
    public List<Batch> getBatchesByProd(@RequestBody Product product) {

        List<Batch> batchList = batchRepository.findByProductId(product.getId());

        ArrayList<Batch> whisper = new ArrayList<>();

        Iterator<Batch> iter = batchList.iterator();
        while(iter.hasNext()) {
            Batch b = iter.next();

            if(b.getRemaining()!=null)
                if(b.getCommission().getDestination().equals("FoodEmperors") && b.getRemaining()>=0)
                    whisper.add(b);

        }




        ;




//        for(Batch b : batchList)
//        {
//            System.out.println(batchList.size());
//            System.out.println(b.getId());
//            System.out.println(b.getRemaining());
//
//            if(b.getRemaining()==null) {
//                batchList.remove(b);
//                System.out.println("SONO QUI");
//            }
//            else
//                if(b.getCommission().getDestination().equals("FoodEmperors") || b.getRemaining()==0)
//                    batchList.remove(b);
//
//
//            System.out.println("E MO QUI");
//        }


        return whisper;
    }
}

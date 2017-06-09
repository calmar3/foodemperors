package endpoint;

import dto.CategoryDTO;
import model.Batch;
import model.Catalogue;
import model.Category;
import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.CatalogueRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by simone on 09/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CatalogueEndPoint {

    @Inject
    CatalogueRepository catalogueRepository;

    @RequestMapping(path = "api/catalogue", method = RequestMethod.GET)
    public List<Catalogue> getCatalogue() {
        return catalogueRepository.findAll();

    }



}

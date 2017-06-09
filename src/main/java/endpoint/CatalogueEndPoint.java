package endpoint;

import model.Catalogue;
import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.CatalogueRepository;

import javax.inject.Inject;

/**
 * Created by simone on 09/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CatalogueEndPoint {

    @Inject
    CatalogueRepository catalogueRepository;

//    @RequestMapping(path = "api/saveCatalogue", method = RequestMethod.POST)
//    public Catalogue saveProduct(@RequestBody Catalogue catalogue) {
//
//
//
//    }

}

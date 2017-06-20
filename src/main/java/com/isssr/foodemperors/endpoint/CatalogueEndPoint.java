package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Catalogue;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.CatalogueRepository;

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

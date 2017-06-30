package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Catalogue;
import com.isssr.foodemperors.service.CatalogueService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by simone on 09/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CatalogueEndPoint {

    @Inject
    private CatalogueService catalogueService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/catalogue", method = RequestMethod.GET)
    public List<Catalogue> getCatalogue(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return catalogueService.getCatalogue();
        else{
            response.setStatus(401);
            return null;
        }

    }
}

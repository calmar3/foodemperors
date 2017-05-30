package endpoint;

import model.*;
import org.springframework.web.bind.annotation.*;
import repository.DrinkRepository;
import repository.DrinkTopologyRepository;
import repository.FoodRepository;
import repository.FoodTopologyRepository;

import javax.inject.Inject;

/**
 * Created by marco on 30/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class HierarchyEndpoint {

    @Inject
    private DrinkRepository drinkRepository;

    @Inject
    private FoodRepository foodRepository;

    @Inject
    private FoodTopologyRepository foodTopologyRepository;

    @Inject
    private DrinkTopologyRepository drinkTopologyRepository;

    @RequestMapping(path = "api/food", method = RequestMethod.POST)
    public Food saveFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @RequestMapping(path = "api/drink", method = RequestMethod.POST)
    public Drink saveDrink(@RequestBody Drink drink) {
        return drinkRepository.save(drink);
    }

    @RequestMapping(path = "api/foodtopology", method = RequestMethod.POST)
    public FoodTopology saveFoodTopology(@RequestBody FoodTopology foodTopology) {
        foodTopology.setFood(foodRepository.findAll().get(0));
        return foodTopologyRepository.save(foodTopology);
    }

    @RequestMapping(path = "api/drinktopology", method = RequestMethod.POST)
    public DrinkTopology saveDrinkTopology(@RequestBody DrinkTopology drinkTopology) {
        drinkTopology.setDrink(drinkRepository.findAll().get(0));
        return drinkTopologyRepository.save(drinkTopology);
    }
}

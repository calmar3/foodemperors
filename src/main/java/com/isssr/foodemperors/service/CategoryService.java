package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.Category;
import org.springframework.stereotype.Service;
import com.isssr.foodemperors.repository.CategoryRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 13/06/17.
 */
@Service
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public Category insertCategory(Category category, String father){
        if (father == null){
            return categoryRepository.save(category);
        }
        Category fatherCategory = categoryRepository.findById(father);
        List<Category> sons = fatherCategory.getSons();
        if (sons == null)
            sons = new ArrayList<>();
        sons.add(category);
        fatherCategory.setSons(sons);
        categoryRepository.save(fatherCategory);
        category.setFather(fatherCategory);
        return categoryRepository.save(category);
    }

    public List<Category> findLeafs(Object o) {
        return categoryRepository.findBySons(null);
    }

    public Category findById(String name) {
        return categoryRepository.findById(name);
    }
}

package com.amastigote.openserver.web;

import com.amastigote.openserver.data.model.local.Category;
import com.amastigote.openserver.data.model.remote.CategoryRequestBody;
import com.amastigote.openserver.data.model.remote.Response;
import com.amastigote.openserver.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response getCategories() {
        return new Response().setStat(Response.Status.COMPLETE).setObj(categoryService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response createCategory(
            @RequestBody CategoryRequestBody categoryRequestBody
    ) {
        Response response = new Response();
        String name = categoryRequestBody.getName();
        Category category = categoryService.findCategoryByName(name);
        if (category != null)
            response.setStat(Response.Status.ERROR);
        else {
            category = new Category().setName(name);
            categoryService.save(category);
            response.setStat(Response.Status.COMPLETE);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Response updateCategory(
            @RequestBody CategoryRequestBody categoryRequestBody
    ) {
        Response response = new Response();
        String name = categoryRequestBody.getName();
        String newName = categoryRequestBody.getNewName();
        Category category = categoryService.findCategoryByName(name);
        if (category == null || categoryService.findCategoryByName(newName) != null)
            response.setStat(Response.Status.ERROR);
        else {
            categoryService.save(category.setName(newName));
            response.setStat(Response.Status.COMPLETE);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response deleteCategory(
            @RequestBody CategoryRequestBody categoryRequestBody
    ) {
        Response response = new Response();
        String name = categoryRequestBody.getName();
        Category category = categoryService.findCategoryByName(name);
        if (category == null)
            response.setStat(Response.Status.ERROR);
        else {
            categoryService.deleteWithContainingItems(category);
            response.setStat(Response.Status.COMPLETE);
        }
        return response;
    }
}

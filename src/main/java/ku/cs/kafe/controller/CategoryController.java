/*
6510450569 Panithan Champahom
*/

package ku.cs.kafe.controller;


import jakarta.validation.Valid;
import ku.cs.kafe.request.CategoryRequest;
import ku.cs.kafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String getCategoryForm(Model model) {
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "category-add";
    }

    @PostMapping("/add")
    public String createCategory(@Valid CategoryRequest request, BindingResult result, Model model) {

        if (result.hasErrors())
            return "category-add";

        categoryService.createCategory(request);
        return "redirect:/menus";

    }

}

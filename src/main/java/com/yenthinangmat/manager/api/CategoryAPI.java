package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPI {
    private final CategoryService categoryService;

    public CategoryAPI(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/user/category")
    public List<CategoryDTO> showAll(){
        return categoryService.getAll();
    }
    @PostMapping("/api/admin/category/add")
    public CategoryDTO addCate(@RequestBody CategoryDTO categoryDTO){
        return categoryService.add(categoryDTO);
    }
    @DeleteMapping("/api/admin/category/delete")
    public ResponseEntity<String> deleteCate(@RequestParam(name="listId") Long[] listId){
        for(Long id:listId){
            categoryService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/admin/category/update")
    public void updateCate(@RequestBody CategoryDTO categoryDTO){
          categoryService.update(categoryDTO);
    }
}

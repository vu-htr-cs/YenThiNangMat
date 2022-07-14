package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.ComboOutput;
import com.yenthinangmat.manager.api.Output.ProductOutput;
import com.yenthinangmat.manager.dto.ProductAddDTO;
import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.entity.CategoryEntity;
import com.yenthinangmat.manager.entity.DescriptionEntity;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.entity.UnitEntity;
import com.yenthinangmat.manager.mapper.ProductMapper;
import com.yenthinangmat.manager.service.CategoryService;
import com.yenthinangmat.manager.service.DescriptionService;
import com.yenthinangmat.manager.service.ProductService;
import com.yenthinangmat.manager.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ProductAPI {
    private static final Path CURRENT_FOLDER= Paths.get(System.getProperty(("user.dir")));
    private final ProductService productService;
    private final UnitService unitService;
    private final CategoryService categoryService;
    private final DescriptionService descriptionService;
    @Autowired
    public ProductAPI(ProductService productService, UnitService unitService, CategoryService categoryService, DescriptionService descriptionService) {
        this.productService = productService;
        this.unitService = unitService;
        this.categoryService = categoryService;
        this.descriptionService = descriptionService;
    }
    @GetMapping("/api/product")
    public List<ProductDisplayDTO> showProduct(){
        return productService.showAllProduct();
    }
    @PostMapping("/api/product/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        Path imgPath=Paths.get("src","main","resources","static","img","product-img");
        String fileName=file.getOriginalFilename();
        try{
            file.transferTo(new File(CURRENT_FOLDER.resolve(imgPath).resolve(Paths.get(fileName)).toUri()));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/api/product/add")
    public ProductDisplayDTO addProduct(@RequestBody ProductAddDTO productAddDTO){
        UnitEntity ue= unitService.findOneE(productAddDTO.getUnitId());
        CategoryEntity ce=categoryService.findOneE(productAddDTO.getCategoryId());
        DescriptionEntity de=new DescriptionEntity();
        de.setContent(productAddDTO.getContent());
        de.setImg(productAddDTO.getImg());
        de= descriptionService.insertAndReturnEntity(de);
        return productService.saveA(ProductMapper.toEntity(productAddDTO,ue,ce,de));
    }
    @DeleteMapping("/api/product/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam(name="listId") Long[] listId){
        for(Long id: listId){
            productService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/product/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductAddDTO productAddDTO){
        ProductEntity productEntity=productService.findOneE(productAddDTO.getId());
        productEntity.setProduct_name(productAddDTO.getName());
        productEntity.setUnitEntity(unitService.findOneE(productAddDTO.getUnitId()));
        productEntity.setCategoryEntity(categoryService.findOneE(productAddDTO.getCategoryId()));
        productEntity.getDescriptionEntity().setContent(productAddDTO.getContent());
        productEntity.setPrice(productAddDTO.getPrice());
        productService.update(productEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/getproduct/{page}")
    public ProductOutput getProductPage(@PathVariable(name = "page")int page){
        ProductOutput productOutput=new ProductOutput();
        productOutput.setPage(page);
        productOutput.setList(productService.getListProduct(PageRequest.of(page-1,9)));
        productOutput.setTotalPage((int)Math.ceil((double)productService.count()/9));
        return productOutput;
    }
    @GetMapping("/api/productsell/{page}")
    public ProductOutput getProductSell(@RequestParam(name="categoryId",required = false,defaultValue = "0") Long categoryId,
                                        @PathVariable(name = "page")int page){
        ProductOutput productOutput=new ProductOutput();
        productOutput.setPage(page);
        if(categoryId==0){
            productOutput.setList(productService.getListProduct(PageRequest.of(page-1,9)));
            productOutput.setTotalPage((int)Math.ceil((double)productService.count()/9));
        }
        else{
            productOutput.setList(productService.getListByCategoryId(categoryId,PageRequest.of(page-1,9)));
            productOutput.setTotalPage((int)Math.ceil((double)productService.countListByCId(categoryId)/9));
        }
        return productOutput;
    }
}

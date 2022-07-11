package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.entity.ComboEntity;
import com.yenthinangmat.manager.entity.ComboProductEntity;
import com.yenthinangmat.manager.entity.Component;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.service.CPService;
import com.yenthinangmat.manager.service.ComboService;
import com.yenthinangmat.manager.service.ComboTempService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ComboTempAPI {
    private final ComboTempService comboTempService;
    private final ComboService comboService;
    private final ProductService productService;

    private final CPService cpService;
    public ComboTempAPI(ComboTempService comboTempService, ProductService productService, ComboService comboService, CPService cpService) {
        this.comboTempService = comboTempService;
        this.productService = productService;
        this.comboService = comboService;
        this.cpService = cpService;
    }
    @GetMapping("/api/combotemp/show")
    public Collection<Component> show(){
        return comboTempService.getAllItems();
    }
    @GetMapping("/api/combotemp/add/{id}")
    public ResponseEntity<?> addProduct(@PathVariable(name="id")Long id){
       ProductEntity pe= productService.findOneE(id);
       if(pe!=null){
           Component cp=new Component();
           cp.setProductID(pe.getId());
           cp.setImg(pe.getDescriptionEntity().getImg());
           cp.setPrice(pe.getPrice());
           cp.setProductName(pe.getProduct_name());
           comboTempService.add(cp);
       }
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/combotemp/subtract/{id}")
    public ResponseEntity<?> subtractProduct(@PathVariable(name="id")Long id){
        comboTempService.subtract(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/combotemp/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable(name="id")Long id){
        comboTempService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/combotemp/clear")
    public ResponseEntity<?> clear(){
        comboTempService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/combotemp/save")
    public ResponseEntity<?> saveCombo(@RequestParam int price,@RequestParam String comboName){
        ComboEntity combo=new ComboEntity();
        combo.setComboName(comboName);
        combo.setPrice(price);
        ComboEntity newcombo=comboService.saveE(combo);
        comboTempService.getAllItems().forEach(component -> {
            ComboProductEntity temp= new ComboProductEntity();
            temp.setQty(component.getQty());
            temp.setProduct(productService.findOneE(component.getProductID()));
            temp.setCombo(newcombo);
            cpService.saveE(temp);
        });
        comboTempService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

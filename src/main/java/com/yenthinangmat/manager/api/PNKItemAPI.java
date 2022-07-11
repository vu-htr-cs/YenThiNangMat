package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.entity.PNKItem;
import com.yenthinangmat.manager.service.PNKItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PNKItemAPI {
    @Autowired
    private PNKItemService pnkItemService;

    @GetMapping("/api/pnkitem/show")
    public Collection<PNKItem> showHang(){
        return pnkItemService.getAllItems();
    }

    @GetMapping("/api/pnkitem/add")
    public ResponseEntity<?> addItem(@RequestParam Long productId, @RequestParam int giavon, @RequestParam int soluong){
            pnkItemService.add(productId,giavon,soluong);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/pnkitem/remove/{id}")
    public ResponseEntity<?> removeItem(@PathVariable(name = "id")Long id){
        pnkItemService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/pnkitem/clear")
    public ResponseEntity<?> clear(){
        pnkItemService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.PnkAddDTO;
import com.yenthinangmat.manager.entity.PNKItem;
import com.yenthinangmat.manager.service.PNKItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PNKItemAPI {
    private final PNKItemService pnkItemService;

    public PNKItemAPI(PNKItemService pnkItemService) {
        this.pnkItemService = pnkItemService;
    }

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
    @PostMapping("/api/pnkitem/save")
    public ResponseEntity<?> savePNK(@RequestBody PnkAddDTO pnkAddDTO){
        pnkAddDTO.setSotien(pnkItemService.getSum());// map get amout - return tong tien
        pnkItemService.save(pnkAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

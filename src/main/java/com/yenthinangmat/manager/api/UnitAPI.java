package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnitAPI {
    private final UnitService unitService;

    public UnitAPI(UnitService unitService) {
        this.unitService = unitService;
    }
    @GetMapping("/api/UnitOfMeasure")
    public List<UnitDTO> showList(){
        return unitService.getAll();
    }
    @PostMapping("/api/UnitOfMeasure/add")
    public UnitDTO add(@RequestBody UnitDTO unitDTO){
        return unitService.add(unitDTO);
    }
    @DeleteMapping("/api/UnitOfMeasure/delete")
    public ResponseEntity<String> delete(@RequestParam(name="listId") Long[] listId){
        for(Long id:listId){
            unitService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/UnitOfMeasure/update")
    public void updateCate(@RequestBody UnitDTO unitDTO){
        unitService.update(unitDTO);
    }

}

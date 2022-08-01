package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.service.UnitService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class UnitAPI {
    private final UnitService unitService;

    public UnitAPI(UnitService unitService) {
        this.unitService = unitService;
    }
    @GetMapping("/api/user/UnitOfMeasure/show")
    public List<UnitDTO> showList(){
        return unitService.getAll();
    }
    @PostMapping("/api/admin/UnitOfMeasure/add")
    public UnitDTO add(@RequestBody UnitDTO unitDTO){
        return unitService.add(unitDTO);
    }
    @DeleteMapping("/api/admin/UnitOfMeasure/delete")
    public ResponseEntity<String> delete(@RequestParam(name="listId") Long[] listId){
        for(Long id:listId){
            unitService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/admin/UnitOfMeasure/update")
    public void updateCate(@RequestBody UnitDTO unitDTO){
        unitService.update(unitDTO);
    }
    @GetMapping("/api/user/UnitOfMeasure/{page}")
    public UnitResponse getByPage(@PathVariable(name="page")int page){
        UnitResponse response=new UnitResponse();
        response.setPage(page);
        response.setList(unitService.getByPage(PageRequest.of(page-1,9)));
        response.setTotalPage((int)Math.ceil((double)unitService.count()/9));
        return response;
    }
    @Getter
    @Setter
    public class UnitResponse{
        private int page;
        private int totalPage;
        private Collection<UnitDTO> list;
    }

}

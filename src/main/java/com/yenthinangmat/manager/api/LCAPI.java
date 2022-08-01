package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.service.LCService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class LCAPI {
    private final LCService lcService;

    public LCAPI(LCService lcService) {
        this.lcService = lcService;
    }

    @GetMapping("/api/employee/LocationStore/show")
    public List<LocationStoreDTO> showAll() {
        return lcService.getAll();
    }

    @PostMapping("/api/admin/LocationStore/add")
    public LocationStoreDTO addLC(@RequestBody LocationStoreDTO locationStoreDTO) {
        return lcService.add(locationStoreDTO);
    }

    @DeleteMapping("/api/admin/LocationStore/delete")
    public ResponseEntity<String> deleteLC(@RequestParam(name = "listId") Long[] listId) {
        for (Long id : listId) {
            lcService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/admin/LocationStore/update")
    public void updateLC(@RequestBody LocationStoreDTO locationStoreDTO) {
        lcService.update(locationStoreDTO);
    }
    @Getter
    @Setter
    public class LCResponse{
        private int page;
        private int totalPage;
        private Collection<LocationStoreDTO> list;
    }
    @GetMapping("/api/employee/LocationStore/{page}")
    public LCResponse getByPage(@PathVariable(name="page")int page){
        LCResponse response=new LCResponse();
        response.setPage(page);
        response.setList(lcService.getByPage(PageRequest.of(page-1,9)));
        response.setTotalPage((int)Math.ceil((double)lcService.count()/9));
        return response;
    }
}

package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.service.LCService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LCAPI {
    private final LCService lcService;

    public LCAPI(LCService lcService) {
        this.lcService = lcService;
    }

    @GetMapping("/api/LocationStore/show")
    public List<LocationStoreDTO> showAll() {
        return lcService.getAll();
    }

    @PostMapping("/api/LocationStore/add")
    public LocationStoreDTO addLC(@RequestBody LocationStoreDTO locationStoreDTO) {
        return lcService.add(locationStoreDTO);
    }

    @DeleteMapping("/api/LocationStore/delete")
    public ResponseEntity<String> deleteLC(@RequestParam(name = "listId") Long[] listId) {
        for (Long id : listId) {
            lcService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/LocationStore/update")
    public void updateLC(@RequestBody LocationStoreDTO locationStoreDTO) {
        lcService.update(locationStoreDTO);
    }

}

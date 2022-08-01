package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.service.PNKService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class PNKAPI {
    private final PNKService pnkService;
    public PNKAPI(PNKService pnkService) {
        this.pnkService = pnkService;
    }
    @GetMapping("/api/employee/pnk/show")
    public List<PNKDTO> showList(){
        return pnkService.getAll();
    }
    @GetMapping("/api/employee/pnk/{page}")
    public PNKResponse getByPage(@PathVariable(name="page")int page){
        PNKResponse pnkResponse=new PNKResponse();
        pnkResponse.setPage(page);
        pnkResponse.setList(pnkService.getByPage(PageRequest.of(page-1,9)));
        pnkResponse.setTotalPage((int)Math.ceil((double)pnkService.count()/9));
        return pnkResponse;
    }
    @Setter
    @Getter
    public class PNKResponse{
        private int page;
        private int totalPage;
        private Collection<PNKDTO> list;
    }
}

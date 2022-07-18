package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.CustomerDTO;
import com.yenthinangmat.manager.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class CustomerAPI {
    final
    CustomerService customerService;

    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/api/customer/{page}")
    public CustomerOuput show(@PathVariable(name="page")int page){
        CustomerOuput customerOuput=new CustomerOuput();
        customerOuput.setPage(page);
        customerOuput.setList(customerService.getByPage(PageRequest.of(page-1,9)));
        customerOuput.setTotalPage((int)Math.ceil((double)customerService.count()/9));
        return customerOuput;

    }
    @PostMapping("/api/customer/add")
    public CustomerDTO add(@RequestBody CustomerDTO customerDTO){
        return customerService.add(customerDTO);
    }
    public class CustomerOuput{
        private int page;
        private int totalPage;
        private Collection<CustomerDTO> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public Collection<CustomerDTO> getList() {
            return list;
        }

        public void setList(Collection<CustomerDTO> list) {
            this.list = list;
        }
    }
    @DeleteMapping("/api/customer/delete")
    public ResponseEntity<?> delete(@RequestParam(name="listId") Long[] listId){
        for(Long id:listId){
            customerService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

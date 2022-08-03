package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.service.CtpService;
import com.yenthinangmat.manager.service.InventoryService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class XNTController {
    final
    CtpService ctpService;
    final
    ProductService productService;
    public XNTController(CtpService ctpService, ProductService productService) {
        this.ctpService = ctpService;
        this.productService = productService;
    }
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/admin/xnt/{page}")
    public String getPage(@RequestParam(name="start",required = false,defaultValue = "None") String start,
                          @RequestParam(name="end",required = false,defaultValue = "None")String end, Model model,
                          @PathVariable(name="page")int page){
        if(!start.equals("None")&&!end.equals("None")){
            int offset=(page-1)*9;
            List<XNTOutput> res=new ArrayList<>();
            Map<Long,XNTOutput> daukytemp=ctpService.getXTNOutput(Date.valueOf("2022-07-01"),Date.valueOf(start));
            Map<Long,XNTOutput> giuakytemp=ctpService.getXTNOutput(Date.valueOf(start),new Date(Date.valueOf(end).getTime()+(1000*60*60*24)));
            daukytemp.forEach((id,obj)->{
               XNTOutput product=giuakytemp.get(id);
               if(product==null){
                   ProductEntity pe=productService.findOneE(id);
                   product=new XNTOutput();
                   product.setSlDauky(obj.getQty()-obj.getSlXuat());
                   product.setQty(0L);
                   product.setSlXuat(0L);
                   product.setProductId(pe.getId());
                   product.setName(pe.getProduct_name());
                   product.setDvt(pe.getUnitEntity().getName());
                   product.setGiaVon(inventoryService.layGiaVon(pe.getId()));//truyen vao product id
                   giuakytemp.put(product.getProductId(),product);
               }
               else{
                   product.setSlDauky(obj.getQty()-obj.getSlXuat());
               }
            });
            Collection<XNTOutput> temp=giuakytemp.values();
            List<XNTOutput> temp2 =temp.stream().toList();
            for(int i=offset;i<Math.min(offset+9,temp.size());i++){
                res.add(temp2.get(i));
            }
            long tongcong=0;
            for(XNTOutput item:temp2){
                tongcong+=(item.getSlDauky()+item.getQty() - item.getSlXuat())*item.getGiaVon();
            }
            model.addAttribute("res",res);
            model.addAttribute("page",page);
            model.addAttribute("totalPage",(int)Math.ceil((double)temp.size()/9));
            model.addAttribute("tongCong",tongcong);
        }
        return"XNT/index";
    }

}

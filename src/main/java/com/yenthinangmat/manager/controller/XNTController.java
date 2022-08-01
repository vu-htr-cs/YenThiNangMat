package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.service.CtpService;
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

@Controller
public class XNTController {
    final
    CtpService ctpService;
    @Autowired
    ProductService productService;
    public XNTController(CtpService ctpService) {
        this.ctpService = ctpService;
    }

    @GetMapping("/admin/xnt/{page}")
    public String getPage(@RequestParam(name="start",required = false,defaultValue = "None") String start,
                          @RequestParam(name="end",required = false,defaultValue = "None")String end, Model model,
                          @PathVariable(name="page")int page){
        if(!start.equals("None")&&!end.equals("None")){
            int offset=(page-1)*9;
            List<XNTOutput> res=new ArrayList<>();
            Collection<XNTOutput> temp=ctpService.getXTNOutput(Date.valueOf(start),new Date(Date.valueOf(end).getTime()+(1000*60*60*24))).values();
            List<XNTOutput> temp2 =temp.stream().toList();
            for(int i=offset;i<Math.min(offset+9,temp.size());i++){
                res.add(temp2.get(i));
            }
            Long tongcong=0L;
            for(int i=0;i<temp2.size();i++){
                if(temp2.get(i).getQty()!=0){
                    tongcong+=temp2.get(i).getTongnhap() -temp2.get(i).getSlXuat()*(temp2.get(i).getTongnhap()/temp2.get(i).getQty());
                }
                else{

                    tongcong+=temp2.get(i).getTongnhap() -temp2.get(i).getSlXuat()*productService.findOneE(temp2.get(i).getProductId()).getPrice();
                }
            }
            model.addAttribute("res",res);
            model.addAttribute("page",page);
            model.addAttribute("totalPage",(int)Math.ceil((double)temp.size()/9));
            model.addAttribute("tongCong",tongcong);
        }
        return"XNT/index";
    }

}

package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.api.Output.ProfitOutput;
import com.yenthinangmat.manager.service.CtpService;
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
public class ProfitController {
    final
    CtpService ctpService;

    public ProfitController(CtpService ctpService) {
        this.ctpService = ctpService;
    }

    @GetMapping("/admin/profit/{page}")
    public String getProfit(@RequestParam(name = "start", required = false, defaultValue = "None") String start,
                            @RequestParam(name = "end", required = false, defaultValue = "None") String end, Model model,
                            @PathVariable(name = "page") int page) {
        if (!start.equals("None") && !end.equals("None")) {
            int offset = (page - 1) * 9;
            List<ProfitOutput> res = new ArrayList<>();
            Collection<ProfitOutput> temp = ctpService.getProfit(Date.valueOf(start), Date.valueOf(end)).values();
            List<ProfitOutput> temp2 = temp.stream().toList();
            for (int i = offset; i < Math.min(offset + 9, temp.size()); i++) {
                res.add(temp2.get(i));
            }
            long tongcong=0;
            for (ProfitOutput profitOutput : temp2) {
                tongcong += (long) profitOutput.getTienBan() - profitOutput.getTongnhap();
            }
            model.addAttribute("res", res);
            model.addAttribute("page", page);
            model.addAttribute("totalPage", (int) Math.ceil((double) temp.size() / 9));
            model.addAttribute("tongCong",tongcong);
        }
        return "Profit/baocaodoanhthu";
    }
}

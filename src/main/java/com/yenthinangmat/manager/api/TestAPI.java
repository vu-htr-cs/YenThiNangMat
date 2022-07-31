package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.XTNOutput;
import com.yenthinangmat.manager.service.CtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collection;

@RestController
public class TestAPI {
    @Autowired
    CtpService ctpService;

    @GetMapping("/api/admin/test")
    public Collection<XTNOutput> methodTest(@RequestParam(name="start") String start, @RequestParam("end")String end) {
        return ctpService.getXTNOutput(Date.valueOf(start), Date.valueOf(end)).values();
    }
}

package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.service.CtpService;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collection;

@RestController
public class TestAPI {
    @Autowired
    UserService userService;

    @GetMapping("/api/admin/test/{id}")
    public ResponseEntity<?> methodTest(@PathVariable(name="id")Long id) {
        userService.deleteOne(id);
        return ResponseEntity.ok("OK");
    }
}

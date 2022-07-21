package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRoleDTO {
    private String username;
    private String password;
    private List< String> role;
    private byte enabled;
}

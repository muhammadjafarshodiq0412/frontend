package com.astra_life_testing.frontend.frontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserModel {
    private long id;
    private String user;
    private String created;
    private String status;
}

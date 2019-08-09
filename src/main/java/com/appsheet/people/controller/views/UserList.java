package com.appsheet.people.controller.views;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserList {
    private ArrayList<Integer> result;
    private String token;
}

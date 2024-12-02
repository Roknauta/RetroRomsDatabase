package com.roknauta.retroRomsDatabase.controller;

import com.roknauta.retroRomsDatabase.utils.JsfUtils;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class RetroRomsController {

    public void test(){
        JsfUtils.addInfoMessage("test");
    }

}

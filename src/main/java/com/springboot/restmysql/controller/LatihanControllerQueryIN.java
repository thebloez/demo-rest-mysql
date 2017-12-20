package com.springboot.restmysql.controller;

import com.springboot.restmysql.service.ServiceLatihanQueryIN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thebloez on 20/12/17.
 */
@RestController
public class LatihanControllerQueryIN {

    @Autowired private ServiceLatihanQueryIN serviceLatihanQueryIN;

    @GetMapping("/get-all")
    public List getAll(){

        List resp = serviceLatihanQueryIN.getForAll();
        return resp;
    }

    @GetMapping("/find")
    public List findById(@RequestParam(value = "id", required = false) String[] id
            , @RequestParam(value = "gender", required = false) String[] gender){
        List resp = serviceLatihanQueryIN.getAllFromDb(id, gender);

        return resp;
    }
}

package com.springboot.restmysql.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import com.springboot.restmysql.rest.Dummy;
import com.springboot.restmysql.service.ServiceLatihanQueryIN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @PostMapping(value = "/find-requestbody", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List findByIdUsingRequestBody(@RequestBody TextNode id){
        // TODO: parse JSON
        List resp = serviceLatihanQueryIN.findFromRequestBody(id.asInt());
        return resp;
    }

    @PostMapping(value = "/cek", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List findAll(String id){
        List resp = serviceLatihanQueryIN.getForAll();
        return resp;
    }
}

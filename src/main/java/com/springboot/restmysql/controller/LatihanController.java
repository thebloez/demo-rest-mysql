package com.springboot.restmysql.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.springboot.restmysql.rest.*;
import com.springboot.restmysql.service.LatihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Ryan on 12/11/17.
 */
@RestController
@RequestMapping("/latihan")
public class LatihanController {

    @Autowired LatihanService latihanService;

    @Value("www.facebook.com")
    private String linkFacebook;
    @Value("www.twitter.com")
    private String linkTwitter;


    @GetMapping(value = "/social")
    public SuccessRestResponse<JsonNode> getSocialMedia(){
        com.fasterxml.jackson.databind.node.ObjectNode rootNode = JsonNodeFactory.instance.objectNode();
        rootNode.put("link", linkTwitter);

        SuccessRestResponse<JsonNode> resp = new SuccessRestResponse<>(rootNode);
        return resp;
    }

    @RequestMapping(value = "/data")
    public ResponseEntity<DataRest> getAllDataAgain(){
        Object[] result = latihanService.getDataDummy();
        return new ResponseEntity<DataRest>(new DataRest((List<Object[]>) result[0], (List<Object[]>)result[1]),HttpStatus.OK);
    }

    @GetMapping(value = "/tes")
    public List<RootPartisipan> getAllData(@RequestParam(value = "date", required = false) String date){

        if (date.length() == 0){
            date = latihanService.dateFormat(latihanService.lastTransact());
        }

        List<RootPartisipan> result = latihanService.teslagihDistinct(date);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type type = new TypeToken<List<RootPartisipan>>() {}.getType();
        String json = gson.toJson(result, type);
        System.out.println(json);
        result = gson.fromJson(json, type);

        return result;
    }


    @RequestMapping(value = "/tesmap")
    public ResponseEntity<List<Map<String, Object>>> test(){
        List<Map<String, Object>> temp  = new ArrayList<>();
        Map<String, Object> a =  new HashMap<String,Object>();
        a.put("A", "AA");
        a.put("B", "BB");
        temp.add(a);

        a =  new HashMap<String,Object>();
        a.put("A", "AA");
        a.put("B", "CC");
        temp.add(a);

        a =  new HashMap<String,Object>();
        a.put("A", "AA");
        a.put("B", "DD");
        temp.add(a);

        a =  new HashMap<String,Object>();
        a.put("A", "AB");
        a.put("B", "DD");
        temp.add(a);


        List<String> distinct = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            distinct.add(temp.get(i).get("A").toString());
        }

        Set<String> result = new HashSet<>(distinct);

        List<Map<String, Object>> hasil = new ArrayList<Map<String,Object>>();
        Map<String, Object> aa = new HashMap<String, Object>();

        for (String set: result) {
            aa = new HashMap<String, Object>();
            aa.put("name", set);
            List<String> testt = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                if(temp.get(i).get("A").toString().equals(set)){
                    testt.add(temp.get(i).get("B").toString());
                }
            }
            aa.put("node", testt);

            hasil.add(aa);
        }

        return new ResponseEntity<List<Map<String, Object>>>(hasil,HttpStatus.OK);
    }

    @GetMapping(value = "/query")
    public List<NodeEmiten> getDataUsingIn(){

        List<NodeEmiten> result = latihanService.getPartisipanUsingInQuery();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type type = new TypeToken<List<NodeEmiten>>() {}.getType();
        String json = gson.toJson(result, type);
        System.out.println(json);
        result = gson.fromJson(json, type);

        return result;
    }

}

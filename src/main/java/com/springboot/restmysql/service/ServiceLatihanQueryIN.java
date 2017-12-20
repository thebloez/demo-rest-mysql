package com.springboot.restmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by thebloez on 20/12/17.
 */
@Service
public class ServiceLatihanQueryIN {

    @Autowired private NamedParameterJdbcTemplate jt;

    public List getForAll(){
        MapSqlParameterSource param = new MapSqlParameterSource();
        List result = jt.queryForList(dynamicQuery().toString(), param);
        return result;
    }

    public List getAllFromDb(String[] id, String[] gender){

        List<String> stringList = Arrays.asList(id);
        List<String> stringGender = Arrays.asList(gender);
        StringBuilder newQuery = dynamicQuery();

        if (id.length == 0 && gender.length == 0) {
            newQuery.append("");
        } else if (id.length > 0 && gender.length == 0){
            newQuery.append(" where id IN (:id)");
        } else if (id.length == 0 && gender.length > 0){
            newQuery.append(" where gender IN (:gender)");
        } else if (id.length > 0 && gender.length > 0){
            newQuery.append(" where id IN (:id)");
            newQuery.append(" and gender IN (:gender)");
        }

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", stringList);
        parameterSource.addValue("gender", stringGender);

        List result = jt.queryForList(newQuery.toString(), parameterSource);
        return result;
    }

    private StringBuilder dynamicQuery(){
        StringBuilder query = new StringBuilder();
        query.append("select * from latihan");
        return query;
    }

}

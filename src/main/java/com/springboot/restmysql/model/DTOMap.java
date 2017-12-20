package com.springboot.restmysql.model;

import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ryan on 12/11/17.
 */
public class DTOMap implements Serializable, RowMapper<DTOMap> {

    private static final long serialVersionUID = -8840406844877458198L;

    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    public HashMap<String, Object> map = new HashMap<>();

    public HashMap<String, Object> getMap() {
        return map;
    }

    @Override
    public DTOMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        DTOMap dto = new DTOMap();

        int rowCount = rs.getMetaData().getColumnCount();

        for (int i = 1; i < rowCount; i++) {
            try {
                dto.map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
            } catch (SQLException e){
                e.getErrorCode();
            }
        }

        return dto;
    }

    public void put(String name, Object o){
        logger.info(name);
        map.put(name, o);
    }

    public Object get(String name){
        return map.get(name);
    }

    public String getString(String name){
        return (String) map.get(name);
    }

    public Integer getInt(String name){
        return (Integer) map.get(name);
    }

    public Date getDate(String name){
        return (Date) map.get(name);
    }

    public BigDecimal getBigDecimal(String name){
        return (BigDecimal) map.get(name);
    }
    
    public Long getLong(String name){
    	return (Long) map.get(name);
    }

    public void coptItem(String key, DTOMap map){
        this.put(key, map.get(key));
    }

    public String showData(){
        StringBuilder build = new StringBuilder();
        build.append("\n");
        for (String data: map.keySet()) {
            build.append(data+" :" + map.get(data) + "\n");
        }
        build.append("\n");
        return build.toString();
    }
}

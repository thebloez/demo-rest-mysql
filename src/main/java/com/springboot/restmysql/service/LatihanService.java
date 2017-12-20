package com.springboot.restmysql.service;

import com.springboot.restmysql.model.*;
import com.springboot.restmysql.rest.NodeEmiten;
import com.springboot.restmysql.rest.Root;
import com.springboot.restmysql.rest.RootPartisipan;
import com.sun.deploy.util.ArrayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ryan on 12/11/17.
 */
@Service
public class LatihanService {

    @Autowired private NamedParameterJdbcTemplate jt;

    private final Logger logger = Logger.getLogger(this.getClass());

    public Object[] getDataDummy() {
        Object[] result = new Object[2];

        List<Object[]> categories = new ArrayList<>();
        Object[] tempCat = new Object[4];

        List<Object[]> dataset = new ArrayList<>();
        Object[] tempDataSet = new Object[3];

        StringBuilder str = new StringBuilder()
                .append("SELECT")
                .append("* FROM BREAKDOWN ");
        SqlParameterSource parameter = new MapSqlParameterSource();
        List<DTOMap> dtoMapList = (jt.query(str.toString(), parameter, new DTOMap()));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (int i = 0; i < dtoMapList.size(); i++) {



//            tempCat = new Object[4];
//            tempCat[0] = String.valueOf(i+1);
//            tempCat[1] = dtoMapList.get(i).getString("URL_AVATAR");
//            tempCat[2] = dtoMapList.get(i).getString("PARTISIPAN_NAME");
//            tempCat[3] = dtoMapList.get(i).getLong("NILAI_INVESTASI");
//            categories.add(tempCat);
//            tempDataSet = new Object[3];
//            tempDataSet[0] = i + 1;
//            tempDataSet[1] = dtoMapList.get(i).getLong("NILAI_INVESTASI");
//            tempDataSet[2] = dtoMapList.get(i).getString("CODE_BASE_SEC");
//            dataset.add(tempDataSet);
        }
//        result[0]=dm;
//        result[1]=dataset;

        System.out.println(result);
        return result;

//        return null;
    }

    public List<Map<String, AllData>> tes() {
        String query = "select URL_AVATAR as urlAvatar, PARTISIPAN_NAME as partisipanName, ID_MEM as idMem" +
                "NILAI_INVESTASI as nilaiInvestasi, CODE_BASE_SEC as codeBase from BREAKDOWN";
        SqlParameterSource parameter = new MapSqlParameterSource();

        List<Map<String, AllData>> data = jt.query(query, parameter, new RowMapper<Map<String, AllData>>() {
            @Override
            public Map<String, AllData> mapRow(ResultSet resultSet, int i) throws SQLException {
                AllData allData = new AllData();
                List<Map<String, AllData>> newData = new ArrayList<>();
                Map<String, Object> a = new HashMap<>();
//                a.put("A", allData.setIdMem(resultSet.getString("SS")));

                return null;
            }
        });



        return null;
    }

    public List<RootPartisipan> teslagihDistinct(String date){
        Root root = new Root();
        String tanggal;

        if (date.length() == 0){
            tanggal = dateFormat(lastTransact());
        } else {
            tanggal = dateFormat(lastTransactBySearch(date));
        }

        MapSqlParameterSource param = new MapSqlParameterSource().addValue("tanggal", tanggal);

        BigDecimal totalSummaryValue = BigDecimal.ZERO;
        BigDecimal totalEmiten = BigDecimal.ZERO;

        // listing partisipan for investor
        String sqlPartisipan = "SELECT SNAP_DATE as tanggal, SUM(NILAI_INVESTASI) AS sumNilai, ID_MEM AS idMem, PARTISIPAN_NAME AS partisipanName, URL_AVATAR AS urlAvatar " +
                "FROM BREAKDOWN where SNAP_DATE = :tanggal GROUP BY ID_MEM, ID_MEM, PARTISIPAN_NAME,URL_AVATAR";
        List<RootPartisipan> rootList = jt.query(sqlPartisipan, param, BeanPropertyRowMapper.newInstance(RootPartisipan.class));
        List<NodeEmiten> nodeEmitenList;

        for (int idx = 0; idx < rootList.size() ; idx++) {
            RootPartisipan partisipan = rootList.get(idx);
            String sqlEmiten = "SELECT SNAP_DATE AS tanggal, CODE_BASE_SEC AS codeBase, ID_MEM AS idMem, NILAI_INVESTASI AS nilaiInvestasi " +
                    "FROM BREAKDOWN WHERE SNAP_DATE = :tanggal and ID_MEM = :idMem";
            param.addValue("idMem", partisipan.getIdMem());
            nodeEmitenList = jt.query(sqlEmiten, param, BeanPropertyRowMapper.newInstance(NodeEmiten.class));

            for (int i = 0; i < nodeEmitenList.size(); i++) {
                nodeEmitenList.get(i).setId(i+1);
            }
            totalSummaryValue = totalSummaryValue.add(partisipan.getSumNilai() == null ? BigDecimal.ZERO : partisipan.getSumNilai());

            partisipan.setId(idx+1);
            partisipan.setNodeEmitenList(nodeEmitenList);
            rootList.set(idx, partisipan);

        }

        // query agains nodeEmitenList
        if (rootList.size() > 0){
            root.setTotalSemua(totalSummaryValue);
            for (int i = 0; i < rootList.size(); i++) {
                RootPartisipan part = rootList.get(i);
                BigDecimal emiten = part.getSumNilai();
                float rootTot = totalSummaryValue.longValue();
                float emitenTot = emiten.floatValue();

                float hitung = (emitenTot/rootTot);

                float percent = Math.round(hitung*100);

                part.setPercent(percent);
            }

        }

        return  rootList;

    }

    public List<NodeEmiten> getPartisipanUsingInQuery(){
        String query = "SELECT SNAP_DATE AS tanggal, CODE_BASE_SEC AS codeBase, ID_MEM AS idMem, NILAI_INVESTASI AS nilaiInvestasi " +
                "FROM BREAKDOWN where CODE_BASE_SEC IN (:tanggal)";

//        List secType = Arrays.asList(new Integer[]{1,4,5,2});
        MapSqlParameterSource tanggal = new MapSqlParameterSource().addValue("tanggal", getListTanggal());
        logger.info(tanggal);
        List<NodeEmiten> emitenList = jt.query(query, tanggal, BeanPropertyRowMapper.newInstance(NodeEmiten.class));
        logger.info(emitenList);
        return emitenList;
    }

    public List<String> getListTanggal(){
        String query = "select CODE_BASE_SEC from BREAKDOWN where ID_MEM = 'CC002'";
        MapSqlParameterSource param = new MapSqlParameterSource();

        List listTanggal = jt.queryForList(query, param, String.class);

        return listTanggal;
    }

    public String lastTransact(){
        MapSqlParameterSource param = new MapSqlParameterSource();
        Date latestTransaction = jt.queryForObject("select MAX(SNAP_DATE) from BREAKDOWN", param, Date.class);
        return new SimpleDateFormat("yyyy-MM-dd").format(latestTransaction).toString();
    }

    public String  lastTransactBySearch(String searchDate) {
        // untuk parameter baru
        String newDate = searchDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // parameter untuk query awal
        MapSqlParameterSource paramAwal = new MapSqlParameterSource();
        List<String> dateLastTransctBySearch;

        String sqlLastTransactBySearch = "SELECT SNAP_DATE FROM BREAKDOWN " +
                "WHERE SNAP_DATE = :tanggal GROUP BY SNAP_DATE";
        paramAwal.addValue("tanggal", searchDate);
        dateLastTransctBySearch = jt.queryForList(sqlLastTransactBySearch, paramAwal, String.class);
        if (dateLastTransctBySearch.size() == 0){

            do {

                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(searchDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, -1);

                newDate = sdf.format(c.getTime());

            } while (dateLastTransctBySearch.size() > 0);
        }

        MapSqlParameterSource newParam = new MapSqlParameterSource().addValue("newtanggal", newDate);

        String sqlNew = "SELECT SNAP_DATE FROM BREAKDOWN " +
                "WHERE SNAP_DATE = :newtanggal GROUP BY SNAP_DATE";

        return jt.queryForObject(sqlNew, newParam, String.class);

    }

    public String dateFormat(String toDate){
        DateFormat readFormat = new SimpleDateFormat( "yyyy-MM-dd");
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = readFormat.parse(toDate);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return writeFormat.format(date);
    }

}

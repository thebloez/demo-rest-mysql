package com.springboot.restmysql.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 12/11/17.
 */
@JsonRootName(value = "Data Latihan")
public class DataRest {

    @JsonProperty("InvID") private String invID;
    @JsonProperty("dataset") private List<Category> categories;
    @JsonProperty("node") private DataSet dataSet;

    //construtor didieu
    public DataRest(List<Object[]> categories,List<Object[]> dataset) {
        this.categories = setItemCategories(categories);
        this.dataSet = setItemDataSet(dataset);
    }

    private List<Category> setItemCategories(List<Object[]> categories){
        List<Category> result = new ArrayList<>();
        Category tempCategory = new Category();

        for (int i = 0; i < categories.size(); i++) {
            Object[] temp = categories.get(i);
            tempCategory = new Category();
            tempCategory.id = (String) temp[0];
            tempCategory.logoSrc = (String) temp[1];
            tempCategory.partisipanName = (String) temp[2];
            tempCategory.valueCat = (BigDecimal) temp[3];
            result.add(tempCategory);
        }
        return result;
    }

    private DataSet setItemDataSet(List<Object[]> dataset){
        List<DataSet.Data> tempData = new ArrayList<>();
        DataSet dataSet = new DataSet();
        DataSet.Data data = dataSet.new Data();

        for (int i = 0; i < dataset.size() ; i++) {
            Object[] temp = (Object[]) dataset.get(i);
            data = dataSet.new Data();
            data.id = (int) temp[0];
            data.value = (BigDecimal) temp[1];
            data.name = (String) temp[2];
            tempData.add(data);
        }

        return new DataSet(tempData);
    }

    // inner class dari DataRest
    private class Category {

        @JsonProperty("id") private String id;
        @JsonProperty("logosrc") private String logoSrc;
        @JsonProperty("partisipan_name") private String partisipanName;
        @JsonProperty("value") private BigDecimal valueCat;
        @JsonProperty("percent") private int percent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogoSrc() {
            return logoSrc;
        }

        public void setLogoSrc(String logoSrc) {
            this.logoSrc = logoSrc;
        }

        public String getPartisipanName() {
            return partisipanName;
        }

        public void setPartisipanName(String partisipanName) {
            this.partisipanName = partisipanName;
        }

        public BigDecimal getValueCat() {
            return valueCat;
        }

        public void setValueCat(BigDecimal valueCat) {
            this.valueCat = valueCat;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }


    // inner class dari DataRest
    static class DataSet {
        @JsonProperty("node") private List<Data> data;

        public DataSet(){}

        public DataSet(List<Data> data) {
            this.data = data;
        }

        // inner class dari DataRest->DataSet
        private class Data {

            @JsonProperty("id") int id;
            @JsonProperty("value") BigDecimal value;
            @JsonProperty("name") String name;

            public Data(){}

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public BigDecimal getValue() {
                return value;
            }

            public void setValue(BigDecimal value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

package com.springboot.restmysql.rest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ryan on 16/11/17.
 */
public class Root {
    BigDecimal totalSemua;
    List<RootPartisipan> rootPartisipans;

    public BigDecimal getTotalSemua() {
        return totalSemua;
    }

    public void setTotalSemua(BigDecimal totalSemua) {
        this.totalSemua = totalSemua;
    }

    public List<RootPartisipan> getRootPartisipans() {
        return rootPartisipans;
    }

    public void setRootPartisipans(List<RootPartisipan> rootPartisipans) {
        this.rootPartisipans = rootPartisipans;
    }
}

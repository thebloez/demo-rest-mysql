package com.springboot.restmysql.rest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ryan on 15/11/17.
 */
public class NodeEmiten {

    int id;
    Date tanggal;
    BigDecimal nilaiInvestasi;
    String codeBase, idMem;

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getIdMem() {
        return idMem;
    }

    public void setIdMem(String idMem) {
        this.idMem = idMem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getNilaiInvestasi() {
        return nilaiInvestasi;
    }

    public void setNilaiInvestasi(BigDecimal nilaiInvestasi) {
        this.nilaiInvestasi = nilaiInvestasi;
    }

    public String getCodeBase() {
        return codeBase;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }
}

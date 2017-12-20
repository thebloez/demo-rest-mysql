package com.springboot.restmysql.model;

import java.math.BigDecimal;

/**
 * Created by Ryan on 26/11/17.
 */
public class AllData {
    int id;
    String urlAvatar, partisipanName, idMem, codeBase;
    BigDecimal sumNilai, nilaiInvestasi;
    float percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getPartisipanName() {
        return partisipanName;
    }

    public void setPartisipanName(String partisipanName) {
        this.partisipanName = partisipanName;
    }

    public String getIdMem() {
        return idMem;
    }

    public void setIdMem(String idMem) {
        this.idMem = idMem;
    }

    public String getCodeBase() {
        return codeBase;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public BigDecimal getSumNilai() {
        return sumNilai;
    }

    public void setSumNilai(BigDecimal sumNilai) {
        this.sumNilai = sumNilai;
    }

    public BigDecimal getNilaiInvestasi() {
        return nilaiInvestasi;
    }

    public void setNilaiInvestasi(BigDecimal nilaiInvestasi) {
        this.nilaiInvestasi = nilaiInvestasi;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}

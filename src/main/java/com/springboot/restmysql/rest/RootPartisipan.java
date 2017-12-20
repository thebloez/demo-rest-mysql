package com.springboot.restmysql.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Ryan on 15/11/17.
 */
public class RootPartisipan {

private int id;
String urlAvatar, partisipanName, idMem;
BigDecimal sumNilai;
float percent;
List<NodeEmiten> nodeEmitenList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMem() {
        return idMem;
    }

    public void setIdMem(String idMem) {
        this.idMem = idMem;
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

    public BigDecimal getSumNilai() {
        return sumNilai;
    }

    public void setSumNilai(BigDecimal sumNilai) {
        this.sumNilai = sumNilai;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public List<NodeEmiten> getNodeEmitenList() {
        return nodeEmitenList;
    }

    public void setNodeEmitenList(List<NodeEmiten> nodeEmitenList) {
        this.nodeEmitenList = nodeEmitenList;
    }
}

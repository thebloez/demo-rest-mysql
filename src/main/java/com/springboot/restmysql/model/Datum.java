
package com.springboot.restmysql.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("urlAvatar")
    @Expose
    private String logosrc;
    @SerializedName("partisipan_name")
    @Expose
    private String partisipanName;
    @SerializedName("nilaiInvestasi")
    @Expose
    private String value;
    @SerializedName("percent")
    @Expose
    private String percent;
    @SerializedName("node")
    @Expose
    private List<Node> node = new ArrayList<Node>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param partisipanName
     * @param id
     * @param node
     * @param percent
     * @param value
     * @param logosrc
     */
    public Datum(String id, String logosrc, String partisipanName, String value, String percent, List<Node> node) {
        super();
        this.id = id;
        this.logosrc = logosrc;
        this.partisipanName = partisipanName;
        this.value = value;
        this.percent = percent;
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogosrc() {
        return logosrc;
    }

    public void setLogosrc(String logosrc) {
        this.logosrc = logosrc;
    }

    public String getPartisipanName() {
        return partisipanName;
    }

    public void setPartisipanName(String partisipanName) {
        this.partisipanName = partisipanName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public List<Node> getNode() {
        return node;
    }

    public void setNode(List<Node> node) {
        this.node = node;
    }


}

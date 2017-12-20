
package com.springboot.restmysql.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("invId")
    @Expose
    private String invId;
    @SerializedName("Data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataModel() {
    }

    /**
     * 
     * @param data
     * @param invId
     */
    public DataModel(String invId, List<Datum> data) {
        super();
        this.invId = invId;
        this.data = data;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


}

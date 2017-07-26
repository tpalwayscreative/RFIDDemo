package com.nordicid.common.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PC on 7/3/2017.
 */

public class RFIDProtocol extends RealmObject {


    @PrimaryKey
    private String epc ;
    private String value ;
    private String dateTime ;

    public String getEpc(){
        return epc ;
    }
    public void setEpc(String epc){
        this.epc = epc ;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDateTime(String dateTime){
        this.dateTime = dateTime ;
    }

    public String getDateTime(String dateTime){
        return dateTime ;
    }


}

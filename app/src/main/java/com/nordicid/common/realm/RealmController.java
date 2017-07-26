package com.nordicid.common.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by PC on 7/3/2017.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

    //clear all objects from RFIDProtocol.class
    public void clearAll() {
        realm.beginTransaction();
        realm.clear(RFIDProtocol.class);
        realm.commitTransaction();
    }

    //find all objects in the RFIDProtocol.class
    public RealmResults<RFIDProtocol> getEPCList() {
        return realm.where(RFIDProtocol.class).findAll();
    }

    //query a single item with the given id
    public RFIDProtocol getEPCSpecific(String epc) {
        return realm.where(RFIDProtocol.class).equalTo("epc", epc).findFirst();
    }
    //check if RFIDProtocol.class is empty
    public boolean hasEPCEmpty() {
        return !realm.allObjects(RFIDProtocol.class).isEmpty();
    }

    //Inserting epc to realm data
    public void mInsetEPC(RFIDProtocol protocol){
        if (realm == null){
            return;
        }
        realm.beginTransaction();
        realm.copyToRealm(protocol);
        realm.commitTransaction();
    }

    //query example
    public RealmResults<RFIDProtocol> queryEPC() {
        return realm.where(RFIDProtocol.class)
                .contains("epc", "Author 0")
                .or()
                .contains("value", "Realm")
                .findAll();
    }

}

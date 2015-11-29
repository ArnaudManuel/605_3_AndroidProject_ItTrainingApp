package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import android.content.Context;

import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
public class DataGeneralStore {
    private final static boolean onDebug=true;
    private static final DataGeneralStore me = new DataGeneralStore();





    private DataGeneralStore() {
    }

    public static DataStore getStore(Context context){
        if (onDebug)
            return DataTestStore.getMe();
        else
            return new SqlStore(context);
    }




}
package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
public class DataGeneralStore {
    private final boolean onDebug=true;
    private static final DataGeneralStore me = new DataGeneralStore();
    public static DataStore store;
    



    private DataGeneralStore(){
        if(onDebug)
            store= new DataTestStore();
        else;
            //// TODO: 19.11.2015 implentation database
    }




}
package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
public class DataGeneralStore implements DataStore{
    private final static boolean onDebug=true;

    public final static DataGeneralStore store=new DataGeneralStore();
    private final static DataStore testdata=new DataTestStore();
    private final static DataStore local=null;
    private final static DataStore cloud=null;
    private static boolean isCloudAvailabe=false;

    private DataGeneralStore(){}



    @Override
    public List<Student> getStudentsList() {
        if(onDebug)
            return testdata.getStudentsList();
        else
            if(isCloudAvailabe)
                return cloud.getStudentsList();
            else
                return local.getStudentsList();

    }

    @Override
    public List<Student> getStudentsList(Cours cours) {
        if(onDebug)
            return testdata.getStudentsList(cours);
        else
        if(isCloudAvailabe)
            return cloud.getStudentsList(cours);
        else
            return local.getStudentsList(cours);

    }

    @Override
    public List<Student> getStudentsList(Session session) {
        if(onDebug)
            return testdata.getStudentsList(session);
        else
        if(isCloudAvailabe)
            return cloud.getStudentsList(session);
        else
            return local.getStudentsList(session);

    }

    @Override
    public void save(Student student) {
        if(onDebug)
            testdata.save(student);
        else
        if(isCloudAvailabe)
            cloud.save(student);
        else
            local.save(student);
    }

    @Override
    public Student getStudentById(int id) {
        if(onDebug)
            return testdata.getStudentById(id);
        else
        if(isCloudAvailabe)
            return cloud.getStudentById(id);
        else
            return local.getStudentById(id);

    }


    @Override
    public List<Teacher> getTeachersList() {
        if(onDebug)
            return testdata.getTeachersList();
        else
        if(isCloudAvailabe)
            return cloud.getTeachersList();
        else
            return local.getTeachersList();

    }

    @Override
    public List<Teacher> getTeachersList(Cours cours) {
        if(onDebug)
            return testdata.getTeachersList(cours);
        else
        if(isCloudAvailabe)
            return cloud.getTeachersList(cours);
        else
            return local.getTeachersList(cours);

    }

    @Override
    public List<Teacher> getTeachersList(Session session) {
        if(onDebug)
            return testdata.getTeachersList(session);
        else
        if(isCloudAvailabe)
            return cloud.getTeachersList(session);
        else
            return local.getTeachersList(session);

    }

    @Override
    public void save(Teacher teacher) {
        if(onDebug)
            testdata.save(teacher);
        else
        if(isCloudAvailabe)
            cloud.save(teacher);
        else
            local.save(teacher);
    }

    @Override
    public Teacher getTeacherById(int id) {
        if(onDebug)
            return testdata.getTeacherById(id);
        else
        if(isCloudAvailabe)
            return cloud.getTeacherById(id);
        else
            return local.getTeacherById(id);

    }

}

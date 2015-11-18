package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
public interface DataStore {

    //used in the general student list show off all student in database
    List<Student> getStudentsList();
    //used to list Studends who participe to a cours
    List<Student> getStudentsList(Cours cours);
    //used to list Students who participate to a session
    List<Student> getStudentsList(Session session);
    //used to save modification or new Student
    void save (Student srudent);
    Student getStudentById(int id);



    //used in the genneral teachers list show off all teachers in database
    List<Teacher> getTeachersList();
    //used to list teachers who participe to a cours
    List<Teacher> getTeachersList(Cours cours);
    //used to list teachers who participate to a session
    List<Teacher> getTeachersList(Session session);
    //used to save modification or new Student
    void save (Teacher teacher);
    Teacher getTeacherById(int id);

}

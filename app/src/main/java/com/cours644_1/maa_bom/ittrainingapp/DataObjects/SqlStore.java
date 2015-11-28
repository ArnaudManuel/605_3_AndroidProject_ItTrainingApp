package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by arnaud on 27.11.2015.
 */
public class SqlStore extends SQLiteOpenHelper implements DataStore{
    private final static int VERSION =1;
    private final static String DATABASE_NAME ="iptaining.db";
    private final static String Ascendant =" ASC";

    public SqlStore(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(iptainingContract.PersonsTable.getCreateQuery());
        db.execSQL(iptainingContract.CoursTable.getCreateQuery());
        db.execSQL(iptainingContract.RoomTable.getCreateQuery());
        db.execSQL(iptainingContract.SessionTable.getCreateQuery());
        db.execSQL(iptainingContract.TeachersCoursTable.getCreateQuery());
        db.execSQL(iptainingContract.StudentsCoursTable.getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public List<Student> getStudentsList() {

    List<Student> respons = new ArrayList<Student>();
    String clause =iptainingContract.PersonsTable.IsStudent+"=?";
    String[] conds = {"1"};
    SQLiteDatabase db = getReadableDatabase();
    Cursor curs = db.query(
            iptainingContract.PersonsTable.Table_name,
            iptainingContract.PersonsTable.ALL,
            clause,
            conds,
            null,
            null,
            iptainingContract.PersonsTable.Name+Ascendant);
    if (curs!=null){
        for (curs.moveToFirst();curs.isAfterLast()==false;curs.moveToNext()){
            respons.add(new Student(new PersonData(
                    curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable._ID)),
                    curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Name)),
                    curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Firstname)),
                    curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Mail)),
                    curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Description)),
                    curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsStudent)) == 1,
                    curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsTeacher)) == 1
            )));
        }
        curs.close();
    }
    db.close();
    return respons;

}
    @Override
    public List<Student> getStudentsList(Cours cours) {
        return null;
    }

    @Override
    public List<Student> getStudentsList(Session session) {
        return null;
    }

    @Override
    public void save(Student student) {
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.Name, student.getName());
        values.put(iptainingContract.PersonsTable.Firstname, student.getFirstname());
        values.put(iptainingContract.PersonsTable.Mail, student.getMail());
        values.put(iptainingContract.PersonsTable.IsStudent, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        if (student.getId() < 0) {
            db.insert(iptainingContract.PersonsTable.Table_name,"" ,values);
        } else {
            String[] cond = {""+student.getId()};
            db.update(
                    iptainingContract.PersonsTable.Table_name,
                    values,
                    iptainingContract.PersonsTable._ID,
                    cond);
        }
    }

    @Override
    public Student getStudentById(int id) {
        Student respons=null;
        String clause =iptainingContract.PersonsTable._ID+"=?";
        String[] conds = {id+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.PersonsTable.Table_name,
                iptainingContract.PersonsTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.PersonsTable.Name+Ascendant);
        if (curs!=null){
            if(curs.moveToFirst()) {
                respons = new Student(new PersonData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Name)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Firstname)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Mail)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Description)),
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsStudent)) == 1,
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsTeacher)) == 1
                ));
            }
            curs.close();
        }
        db.close();
        return respons;
    }

    @Override
    public List<Teacher> getTeachersList() {
        List<Teacher> respons = new ArrayList<Teacher>();
        String clause =iptainingContract.PersonsTable.IsTeacher+"=?";
        String[] conds = {"1"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.PersonsTable.Table_name,
                iptainingContract.PersonsTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.PersonsTable.Name+Ascendant);
        if (curs!=null){
            for (curs.moveToFirst();curs.isAfterLast()==false;curs.moveToNext()){
                respons.add(new Teacher(new PersonData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Name)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Firstname)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Mail)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Description)),
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsStudent)) == 1,
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsTeacher)) == 1
                )));
            }
            curs.close();
        }
        db.close();
        return respons;
    }

    @Override
    public List<Teacher> getTeachersList(Cours cours) {
        return null;
    }

    @Override
    public List<Teacher> getTeachersList(Session session) {
        return null;
    }

    @Override
    public void save(Teacher teacher) {
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.Name, teacher.getName());
        values.put(iptainingContract.PersonsTable.Firstname, teacher.getFirstname());
        values.put(iptainingContract.PersonsTable.Mail, teacher.getMail());
        values.put(iptainingContract.PersonsTable.IsTeacher, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        if (teacher.getId() < 0) {
            db.insert(iptainingContract.PersonsTable.Table_name,"" ,values);
        } else {
            String[] cond = {""+teacher.getId()};
            db.update(
                    iptainingContract.PersonsTable.Table_name,
                    values,
                    iptainingContract.PersonsTable._ID,
                    cond);
        }
    }

    @Override
    public Teacher getTeacherById(int id) {
        Teacher respons=null;
        String clause =iptainingContract.PersonsTable._ID+"=?";
        String[] conds = {id+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.PersonsTable.Table_name,
                iptainingContract.PersonsTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.PersonsTable.Name+Ascendant);
        if (curs!=null){
            if(curs.moveToFirst()) {
                respons = new Teacher(new PersonData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Name)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Firstname)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Mail)),
                        curs.getString(curs.getColumnIndex(iptainingContract.PersonsTable.Description)),
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsStudent)) == 1,
                        curs.getInt(curs.getColumnIndex(iptainingContract.PersonsTable.IsTeacher)) == 1
                ));
            }
            curs.close();
        }
        db.close();
        return respons;
    }

    @Override
    public List<Cours> getCoursList() {
        return new ArrayList<Cours>();
    }

    @Override
    public Cours getCoursById(int id) {
        return null;
    }

    @Override
    public List<Cours> getCoursFor(Student student) {
        return new ArrayList<Cours>();
    }

    @Override
    public List<Cours> getCoursFor(Teacher teacher) {
        return new ArrayList<Cours>();
    }

    @Override
    public void save(Cours cours) {

    }

    @Override
    public Session getSessionById(int id) {
        return null;
    }

    @Override
    public List<Session> getSessionFor(Cours cours) {
        return new ArrayList<Session>();
    }

    @Override
    public List<Session> getSessionFor(Teacher teacher) {
        return new ArrayList<Session>();
    }

    @Override
    public List<Session> getSessionFor(Student student) {
        return new ArrayList<Session>();
    }

    @Override
    public void save(Session session) {

    }

    @Override
    public Room getRoomById(int id) {
        return null;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end) {
        return null;
    }

    private static abstract class iptainingContract{

        private abstract class SqlType{
            final static String Text = " TEXT";
            final static String Boolean =" INTEGER";//TODO voir possibilité boolean actueleemnt int 1= true, reste =false
            final static String Date =" DATE";
            final static String Integer =" INTEGER";
        }
        private static abstract class SqlContraint{
            final static String PrimaryKey =" PRIMARY KEY AUTOINCREMENT";
            final static String Reference = " REFERENCES ";
            final static String ForeygnKey(String recever){return "FOREIGN KEY("+recever+") ";}
        }
        private static abstract class SqlCommand{
            final static String CreateTable="CREATE TABLE ";
        }
        private static abstract class PersonsTable implements BaseColumns {
            final static String Table_name="Person";

            final static String Reference=SqlContraint.Reference+ Table_name+"("+_ID+")";

            final static String Name ="name";
            final static String Firstname="Firstname";
            final static String Mail="Mail";
            final static String Description="Descr";
            final static String IsStudent="isStudent";
            final static String IsTeacher="isTeacher";

            final static String getCreateQuery() {
                return SqlCommand.CreateTable + Table_name
                        + " ("
                        + _ID + SqlType.Integer + SqlContraint.PrimaryKey + ", "
                        + Name + SqlType.Text + ", "
                        + Firstname + SqlType.Text + ", "
                        + Mail + SqlType.Text + ", "
                        + Description + SqlType.Text + ", "
                        + IsStudent + SqlType.Boolean + ", "
                        + IsTeacher + SqlType.Boolean + ")";
            }
            final static String[] ALL={
                    _ID,
                    Name,
                    Firstname,
                    Mail,
                    Description,
                    IsStudent,
                    IsTeacher};

        }
        private static abstract class CoursTable implements BaseColumns{
            final static String Table_name="Cours";

            final static String Reference=SqlContraint.Reference+ Table_name+"("+_ID+")";

            final static String Name ="name";
            final static String Description="Descr";

            final static String getCreateQuery() {
                return SqlCommand.CreateTable + Table_name
                        + " ("
                        + _ID + SqlType.Integer + SqlContraint.PrimaryKey + ", "
                        + Name + SqlType.Text + ", "
                        + Description + SqlType.Text + ")";
            }
            final static String[] ALL={
                    _ID,
                    Name,
                    Description};
        }
        private static abstract class RoomTable implements  BaseColumns{
            final static String Table_name="Room";

            final static String Reference=SqlContraint.Reference+ Table_name+"("+_ID+")";

            final static String Name ="name";

            final static String getCreateQuery() {
                return SqlCommand.CreateTable + Table_name
                        + " ("
                        + _ID + SqlType.Integer + SqlContraint.PrimaryKey + ", "
                        + Name + SqlType.Text + ")";
            }
            final static String[] ALL={
                    _ID,
                    Name};
        }
        private static abstract class SessionTable implements  BaseColumns{
            final static String Table_name="Session";

            final static String RoomId ="roomId";
            final static String CoursId = "coursId";
            final static String Start = "start";
            final static String End="end";


            final static String getCreateQuery() {
                return SqlCommand.CreateTable + Table_name
                        + " ("
                        + _ID + SqlType.Integer + SqlContraint.PrimaryKey + ", "
                        + RoomId + SqlType.Text + ", "
                        + CoursId + SqlType.Text + ", "
                        + Start + SqlType.Date + ","
                        + End + SqlType.Date + ","
                        + SqlContraint.ForeygnKey(CoursId) + CoursTable.Reference
                        + ")";
            }

            final static String[] ALL={
                    _ID,
                    RoomId,
                    CoursId,
                    Start,
                    End};
        }
        private static abstract class TeachersCoursTable{
            final static String Table_name="TeachersCours";

            final static String CoursId = "coursId";
            final static String TeacherId = "teacherId";

            final static String getCreateQuery(){
                return SqlCommand.CreateTable+Table_name
                        +" ("
                        +CoursId+SqlType.Integer+", "
                        +TeacherId+ SqlType.Integer +", "
                        +SqlContraint.ForeygnKey(CoursId)+CoursTable.Reference+", "
                        +SqlContraint.ForeygnKey(TeacherId)+PersonsTable.Reference
                        +")";
            }
            final static String[] ALL={CoursId, TeacherId};
        }
        private static abstract class StudentsCoursTable{
            final static String Table_name="StudentsCours";
            final static String CoursId = "coursId";
            final static String StudentId = "studentId";

            final static String getCreateQuery() {
                return SqlCommand.CreateTable + Table_name
                        + " ("
                        + CoursId + SqlType.Integer + ", "
                        + StudentId + SqlType.Integer + ", "
                        + SqlContraint.ForeygnKey(CoursId) + CoursTable.Reference + ", "
                        + SqlContraint.ForeygnKey(StudentId) + PersonsTable.Reference
                        + ")";
            }
            final static String[] ALL={
                    CoursId,
                    StudentId};

        }
    }




}

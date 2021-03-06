package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.cours644_1.maa_bom.ittrainingapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arnaud on 27.11.2015.
 */
public class SqlStore extends SQLiteOpenHelper implements DataStore{
    private final static int VERSION =1;
    private final static String DATABASE_NAME ="iptaining.db";
    private final static String Ascendant =" ASC";
    private Context context;

    public SqlStore(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(iptainingContract.PersonsTable.getCreateQuery());
        db.execSQL(iptainingContract.CoursTable.getCreateQuery());
        db.execSQL(iptainingContract.RoomTable.getCreateQuery());
        db.execSQL(iptainingContract.SessionTable.getCreateQuery());
        db.execSQL(iptainingContract.TeachersCoursTable.getCreateQuery());
        db.execSQL(iptainingContract.StudentsCoursTable.getCreateQuery());

        ContentValues[] rooms = iptainingContract.RoomTable.getSomeRooms(15);
        for (ContentValues room : rooms)
            db.insert(iptainingContract.RoomTable.Table_name,"" ,room);
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
    public int save(Student student) {
        if (student.getName().equals(""))
            return -1;
        int respons;
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.Name, student.getName());
        values.put(iptainingContract.PersonsTable.Firstname, student.getFirstname());
        values.put(iptainingContract.PersonsTable.Mail, student.getMail());
        values.put(iptainingContract.PersonsTable.IsStudent, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        if (student.getId() < 0) {
            respons= (int) db.insert(iptainingContract.PersonsTable.Table_name,"" ,values);
        } else {
            respons=student.getId();
            String[] cond = {""+student.getId()};
            String clause =iptainingContract.PersonsTable._ID+"=?";
            db.update(
                    iptainingContract.PersonsTable.Table_name,
                    values,
                    clause,
                    cond);
        }
        db.close();
        return respons;
    }

    @Override
    public void delete(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.IsStudent, 0);
        String[] cond = {""+student.getId()};
        String clause =iptainingContract.PersonsTable._ID+"=?";
        db.update(
                iptainingContract.PersonsTable.Table_name,
                values,
                clause,
                cond);
        db.close();
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
        List<Integer> teacherIds = new ArrayList<Integer>();

        String clause = iptainingContract.TeachersCoursTable.CoursId+"=?";
        String[] conds = {cours.getId()+""};
        String[] selection={iptainingContract.TeachersCoursTable.TeacherId};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.TeachersCoursTable.Table_name,
                selection,
                clause,
                conds,
                null,
                null,
                null);
        if (curs!=null){
            if(curs.moveToFirst())
                teacherIds.add(new Integer(curs.getInt(curs.getColumnIndex(iptainingContract.TeachersCoursTable.TeacherId))));
            curs.close();
        }
        db.close();
        List<Teacher> respons = new ArrayList<Teacher>();
        for (Integer intObj:teacherIds){
            respons.add(getTeacherById(intObj.intValue()));
        }
        return respons;

    }

    @Override
    public List<Teacher> getTeachersList(Session session) {
        List<Integer> teacherIds = new ArrayList<Integer>();

        String clause = iptainingContract.TeachersCoursTable.CoursId+"=?";
        String[] conds = {session.getCoursId()+""};
        String[] selection={iptainingContract.TeachersCoursTable.TeacherId};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.TeachersCoursTable.Table_name,
                selection,
                clause,
                conds,
                null,
                null,
                null);
        if (curs!=null){
            if(curs.moveToFirst())
                teacherIds.add(new Integer(curs.getInt(curs.getColumnIndex(iptainingContract.TeachersCoursTable.TeacherId))));
            curs.close();
        }
        db.close();
        List<Teacher> respons = new ArrayList<Teacher>();
        for (Integer intObj:teacherIds){
            respons.add(getTeacherById(intObj.intValue()));
        }
        return respons;
    }

    @Override
    public int save(Teacher teacher) {
        if(teacher.getName().equals(""))
            return -1;
        int respons;
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.Name, teacher.getName());
        values.put(iptainingContract.PersonsTable.Firstname, teacher.getFirstname());
        values.put(iptainingContract.PersonsTable.Mail, teacher.getMail());
        values.put(iptainingContract.PersonsTable.Description, teacher.getDescription());
        values.put(iptainingContract.PersonsTable.IsTeacher, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        if (teacher.getId() < 0) {
            respons = (int)db.insert(iptainingContract.PersonsTable.Table_name,"" ,values);
        } else {
            String[] cond = {""+teacher.getId()};
            String clause = iptainingContract.PersonsTable._ID+"=?";
            db.update(
                    iptainingContract.PersonsTable.Table_name,
                    values,
                    clause,
                    cond);
            respons= teacher.getId();
        }
        db.close();
        return respons;
    }

    @Override
    public void delete(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(iptainingContract.PersonsTable.IsTeacher, 0);
        String[] cond = {""+teacher.getId()};
        String clause = iptainingContract.PersonsTable._ID+"=?";
        db.update(
                iptainingContract.PersonsTable.Table_name,
                values,
                clause,
                cond);
        db.close();
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
        List<Cours> respons = new ArrayList<Cours>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.CoursTable.Table_name,
                iptainingContract.CoursTable.ALL,
                null,
                null,
                null,
                null,
                iptainingContract.CoursTable.Name+Ascendant);
        if (curs!=null){
            for (curs.moveToFirst();curs.isAfterLast()==false;curs.moveToNext()){
                respons.add(new Cours(new CoursData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.CoursTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.CoursTable.Name)),
                        curs.getString(curs.getColumnIndex(iptainingContract.CoursTable.Description))
                )));
            }
            curs.close();
        }
        db.close();


        return respons;
    }

    @Override
    public Cours getCoursById(int id) {

        Cours respons = null;

        String clause = iptainingContract.CoursTable._ID+"=?";
        String[] conds = {id+""};


        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.CoursTable.Table_name,
                iptainingContract.CoursTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.CoursTable.Name+Ascendant);
        if (curs!=null){
            if(curs.moveToFirst())
                respons=new Cours(new CoursData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.CoursTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.CoursTable.Name)),
                        curs.getString(curs.getColumnIndex(iptainingContract.CoursTable.Description))
                ));

            curs.close();
        }
        db.close();
        return respons;
    }

    @Override
    public List<Cours> getCoursFor(Student student) {
        List<Integer> coursIds = new ArrayList<Integer>();

        String clause = iptainingContract.StudentsCoursTable.StudentId+"=?";
        String[] conds = {student.getId()+""};
        String[] selection={iptainingContract.StudentsCoursTable.CoursId};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.StudentsCoursTable.Table_name,
                selection,
                clause,
                conds,
                null,
                null,
                iptainingContract.StudentsCoursTable.CoursId+Ascendant
        );
        if (curs!=null){
            if(curs.moveToFirst())
                coursIds.add(new Integer(curs.getInt(curs.getColumnIndex(iptainingContract.StudentsCoursTable.CoursId))));
            curs.close();
        }
        db.close();
        List<Cours> respons = new ArrayList<Cours>();
        for (Integer intObj:coursIds){
            respons.add(getCoursById(intObj.intValue()));
        }
        return respons;
    }

    @Override
    public List<Cours> getCoursFor(Teacher teacher) {
        List<Integer> coursIds = new ArrayList<Integer>();

        String clause = iptainingContract.TeachersCoursTable.TeacherId+"=?";
        String[] conds = {teacher.getId()+""};
        String[] selection={iptainingContract.TeachersCoursTable.CoursId};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.TeachersCoursTable.Table_name,
                selection,
                clause,
                conds,
                null,
                null,
                iptainingContract.TeachersCoursTable.CoursId+Ascendant
        );
        if (curs!=null){
            if(curs.moveToFirst())
               coursIds.add(new Integer(curs.getInt(curs.getColumnIndex(iptainingContract.TeachersCoursTable.CoursId))));
            curs.close();
        }
        db.close();
        List<Cours> respons = new ArrayList<Cours>();
        for (Integer intObj:coursIds){
            respons.add(getCoursById(intObj.intValue()));
        }
       return respons;
    }

    @Override
    public int save(Cours cours) {
        if (cours.getName().equals(""))
            return -1;
        int respons;
        ContentValues values = new ContentValues();
        values.put(iptainingContract.CoursTable.Name, cours.getName());
        values.put(iptainingContract.CoursTable.Description, cours.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        if (cours.getId() < 0) {
            respons = (int)db.insert(iptainingContract.CoursTable.Table_name,"" ,values);
        } else {
            String[] cond = {""+cours.getId()};
            String clause =iptainingContract.CoursTable._ID+"=?";
            db.update(
                    iptainingContract.CoursTable.Table_name,
                    values,
                    clause,
                    cond);
            respons= cours.getId();
        }
        db.close();
        return respons;
    }

    @Override
    public void delete(Cours cours) {
        String clause_teacher = iptainingContract.TeachersCoursTable.CoursId+"=?";
        String clause_student = iptainingContract.StudentsCoursTable.CoursId+"=?";
        String clause_session = iptainingContract.SessionTable.CoursId+"=?";
        String clause_cours = iptainingContract.CoursTable._ID+"=?";

        String[] conds = {cours.getId()+""};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(iptainingContract.TeachersCoursTable.Table_name,
                clause_teacher,
                conds);
        db.delete(iptainingContract.StudentsCoursTable.Table_name,
                clause_student,
                conds);
        db.delete(iptainingContract.SessionTable.Table_name,
                clause_session,
                conds);
        db.delete(iptainingContract.CoursTable.Table_name,
                clause_cours,
                conds);
        db.close();

    }

    @Override
    public Session getSessionById(int id) {
       SessionData temp =null;

        String clause = iptainingContract.SessionTable._ID+"=?";
        String[] conds = {id+""};

        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.SessionTable.Table_name,
                iptainingContract.SessionTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.SessionTable.RoomId+Ascendant);
        if (curs!=null){
            if(curs.moveToFirst()){
                temp = (new SessionData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable._ID)),
                        curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable.CoursId)),
                        curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable.RoomId)),
                        new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.Start))),
                        new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.End)))
                        ));
            }
            curs.close();
        }
        db.close();
        return new Session(temp, getCoursById(temp.coursId),getRoomById(temp.roomId));
    }

    @Override
    public List<Session> getSessionFor(Cours cours) {
        List<SessionModificator> temp = new ArrayList<SessionModificator>();

        String clause = iptainingContract.SessionTable.CoursId+"=?";
        String[] conds = {cours.getId()+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.SessionTable.Table_name,
                iptainingContract.SessionTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.SessionTable.Start+Ascendant);
        if (curs!=null){
            Date now = new Date();
            for (curs.moveToFirst();curs.isAfterLast()==false;curs.moveToNext()){
                long endSesseion =curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.End));
                if(endSesseion>=now.getTime())
                temp.add(new SessionModificator(
                        new SessionData(
                                curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable._ID)),
                                cours.getId(),
                                curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable.RoomId)),
                                new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.Start))),
                                new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.End)))
                        )
                        , cours,
                        null));
            }
            curs.close();
        }
        db.close();

        for (SessionModificator session:temp){
            session.setRoom(getRoomById(session.data.roomId));
        }
        List<Session> respons = new ArrayList<Session>();
        for (SessionModificator session:temp)
            respons.add(session);
        return respons;
        }

    @Override
    public List<Session> getSessionFor(Teacher teacher) {
        List<Session> respons = new ArrayList<Session>();
        for (Cours cours : getCoursFor(teacher))
            respons.addAll(getSessionFor(cours));
        return respons;
    }

    @Override
    public List<Session> getSessionFor(Student student) {
        List<Session> respons = new ArrayList<Session>();
        for (Cours cours : getCoursFor(student))
            respons.addAll(getSessionFor(cours));
        return respons;
    }

    @Override
    public List<Session> getAllSession(Cours cours) {
        List<SessionModificator> temp = new ArrayList<SessionModificator>();

        String clause = iptainingContract.SessionTable.CoursId+"=?";
        String[] conds = {cours.getId()+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.query(
                iptainingContract.SessionTable.Table_name,
                iptainingContract.SessionTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.SessionTable.Start+Ascendant);
        if (curs!=null){
            for (curs.moveToFirst();curs.isAfterLast()==false;curs.moveToNext()){
                temp.add(new SessionModificator(
                        new SessionData(
                                curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable._ID)),
                                cours.getId(),
                                curs.getInt(curs.getColumnIndex(iptainingContract.SessionTable.RoomId)),
                                new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.Start))),
                                new Date(curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.End)))
                        )
                        , cours,
                        null));
            }
            curs.close();
        }
        db.close();

        for (SessionModificator session:temp){
            session.setRoom(getRoomById(session.data.roomId));
        }
        List<Session> respons = new ArrayList<Session>();
        for (SessionModificator session:temp)
            respons.add(session);
        return respons;
    }

    @Override
    public int save(Session session) {
        int respons;
        ContentValues values = new ContentValues();

        values.put(iptainingContract.SessionTable.RoomId, session.data.roomId);
        values.put(iptainingContract.SessionTable.CoursId, session.data.coursId);
        values.put(iptainingContract.SessionTable.Start, session.data.start.getTime());
        values.put(iptainingContract.SessionTable.End, session.data.end.getTime());

        SQLiteDatabase db = this.getWritableDatabase();
        if (session.getId() < 0) {
            respons=(int)db.insert(iptainingContract.SessionTable.Table_name,"" ,values);
        } else {
            String[] cond = {""+session.getId()};
            String clause= iptainingContract.SessionTable._ID+"=?";
            db.update(
                    iptainingContract.SessionTable.Table_name,
                    values,
                    clause,
                    cond);
            respons=session.getId();
        }
        db.close();
        return respons;
    }

    @Override
    public void delete(Session session) {
        String clause = iptainingContract.SessionTable._ID+"=?";

        String[] conds = {session.getId()+""};
        SQLiteDatabase db = getWritableDatabase();

        db.delete(iptainingContract.SessionTable.Table_name,
                clause,
                conds);
        db.close();
    }

    @Override
    public Room getRoomById(int id) {
        Room respons = null;
        String clause = iptainingContract.RoomTable._ID+"=?";

        String[] conds = {id+""};
        SQLiteDatabase db = getReadableDatabase();

        Cursor curs = db.query(
                iptainingContract.RoomTable.Table_name,
                iptainingContract.RoomTable.ALL,
                clause,
                conds,
                null,
                null,
                iptainingContract.RoomTable.Name+Ascendant);
        if (curs!=null){
            if(curs.moveToFirst()){
                respons = (new Room(new RoomData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.RoomTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.RoomTable.Name))
                )));
            }
            curs.close();
        }
        db.close();
        return respons;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end, Session session) {
        String tempTableName = "tempTable";
        List<Long> usedRoomIds = new ArrayList<>();

        //to know whitch room is currently available, we select all sessions performing in the periode, then choose rooms who ar not used.

        /*
        query:
        create a new table (temporary) as all sessions who end after the wanted start time
         */

        String query = iptainingContract.SqlCommand.CreateTable+
                tempTableName
                +" AS SELECT *"
                +" FROM "+ iptainingContract.SessionTable.Table_name
                +" WHERE "
                + iptainingContract.SessionTable.End+ " > "
                + start.getTime();


        String clause;
        String[] conds;
        SQLiteDatabase db = this.getWritableDatabase();


        /*
        try - catch  block is hier to performe dstruction of the temp table even if an exception occure.
        if we not use this artefact, if an exception occure, we do not dropt the temp table so we can not recreate it.
        the we have exception on cascade, and we are not able to perform the opération of finding available rooms.
        */
        Cursor curs;


        boolean isclearlyExecuted=false;
        try {
            db.execSQL(query);
            /*
        query:
        remove from the temp table all sessions who start after the wanted end
         */

            clause = iptainingContract.SessionTable.Start+">?";
            conds =new String[] {end.getTime()+""};
            db.delete(tempTableName, clause, conds);
        /*
        query:
        remove from the temp table the current session (the room is available for it)
         */

            clause = iptainingContract.SessionTable._ID + "=?";
            conds = new String[]{session.getId() + ""};
            db.delete(tempTableName, clause, conds);//// TODO: 30.11.2015 on plante là


            //select all used room ids
            String[] selected = {iptainingContract.SessionTable.RoomId};
            usedRoomIds = new ArrayList<>();
            curs = db.query(
                    tempTableName,
                    selected,
                    null,
                    null,
                    null,
                    null,
                    null);

            if (curs != null) {
                for (curs.moveToFirst(); curs.isAfterLast() == false; curs.moveToNext()) {
                    usedRoomIds.add(new Long(
                            curs.getLong(curs.getColumnIndex(iptainingContract.SessionTable.RoomId))
                    ));
                }
                curs.close();

            }

            isclearlyExecuted=true;
        }catch (Exception e){}

        try {
            //drop the temp table
            db.execSQL(iptainingContract.SqlCommand.DestroyTable+tempTableName);
        }catch (Exception e){}



        if(isclearlyExecuted==false){
            Toast.makeText(
                    context,
                    R.string.error_message_room,
                    Toast.LENGTH_SHORT
            ).show();
            //forcing return of the operation by user, juged preferable as giving all rooms as available
            return new ArrayList<Room>();
        }
        List<Room> respons =getRooms();
        for (Long id : usedRoomIds){
            Room usedRoom= getRoomById(id.intValue());
            respons.remove(usedRoom);
        }


/*
        if(usedRoomIds.size()==0) {
            db.close();
            return getRooms();
        }
        else
        {
            for (Long id : usedRoomIds) {
                conds = new String[]{id.longValue() + ""};
                clause = iptainingContract.RoomTable._ID + "<>?";
                curs = db.query(
                        iptainingContract.RoomTable.Table_name,
                        iptainingContract.RoomTable.ALL,
                        clause,
                        conds,
                        null,
                        null,
                        null
                );
                if (curs != null) {
                    for (curs.moveToFirst(); curs.isAfterLast() == false; curs.moveToNext())
                        respons.add(new Room(new RoomData(
                                curs.getInt(curs.getColumnIndex(iptainingContract.RoomTable._ID)),
                                curs.getString(curs.getColumnIndex(iptainingContract.RoomTable.Name))
                        )));
                    curs.close();
                }
            }
        }
        db.close();
        */
        return respons;
    }

    @Override
    public List<Room> getRooms() {
        List<Room> respons = new ArrayList<Room>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor curs = db.rawQuery(iptainingContract.SqlCommand.SelectAll+ iptainingContract.RoomTable.Table_name, null);
        if(curs!=null ) {
            for (curs.moveToFirst(); curs.isAfterLast()==false; curs.moveToNext())
                respons.add(new Room(new RoomData(
                        curs.getInt(curs.getColumnIndex(iptainingContract.RoomTable._ID)),
                        curs.getString(curs.getColumnIndex(iptainingContract.RoomTable.Name))
                )));
            curs.close();
        }

        db.close();
        return respons;
    }

    @Override
    public void setCours(Student student, List<Cours> selected) {

        String clause = iptainingContract.StudentsCoursTable.StudentId+"=?";
        String[] conds = {student.getId()+""};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(iptainingContract.StudentsCoursTable.Table_name,
                clause,
                conds);

        for (Cours cours : selected) {
            ContentValues values = new ContentValues();
            values.put(iptainingContract.StudentsCoursTable.CoursId,cours.getId());
            values.put(iptainingContract.StudentsCoursTable.StudentId, student.getId());
            db.insert(iptainingContract.StudentsCoursTable.Table_name,"", values);
        }
    }

    @Override
    public void setTeacher(Cours cours, List<Teacher> selected) {
        String clause = iptainingContract.TeachersCoursTable.CoursId+"=?";
        String[] conds = {cours.getId()+""};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(iptainingContract.TeachersCoursTable.Table_name,
                clause,
                conds);
        for (Teacher teacher : selected){
            ContentValues values = new ContentValues();
            values.put(iptainingContract.TeachersCoursTable.CoursId, cours.getId());
            values.put(iptainingContract.TeachersCoursTable.TeacherId,teacher.getId());
            db.insert(iptainingContract.TeachersCoursTable.Table_name,"",values);
        }
    }

    private static abstract class iptainingContract{

        private abstract class SqlType{
            final static String Text = " TEXT";
            final static String Boolean =" INTEGER";//TODO voir possibilité boolean actueleemnt int 1= true, reste =false
            final static String Date =" INTEGER";
            final static String Integer =" INTEGER";
        }
        private static abstract class SqlContraint{
            final static String PrimaryKey =" PRIMARY KEY AUTOINCREMENT";
            final static String Reference = " REFERENCES ";
            final static String ForeygnKey(String recever){return "FOREIGN KEY("+recever+") ";}
        }
        private static abstract class SqlCommand{
            final static String DestroyTable = "DROP TABLE ";
            final static String CreateTable="CREATE TABLE ";
            final static String SelectAll = "SELECT  * FROM ";
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


            static private ContentValues[] getSomeRooms(int nb) {
                ContentValues[] rooms;
                int nbrooms=nb%9000;
                rooms= new ContentValues[nbrooms];
                for (int cpt= 0; cpt<rooms.length;++cpt){
                    ContentValues values = new ContentValues();
                    int temp=101+cpt;
                    values.put(Name,temp+"");
                    rooms[cpt]=values;
                }
                return rooms;
            }
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
                        + Start + SqlType.Date + ", "
                        + End + SqlType.Date + ", "
                        + SqlContraint.ForeygnKey(CoursId) + CoursTable.Reference +", "
                        +SqlContraint.ForeygnKey(RoomId)+ RoomTable.Reference
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

package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
//// TODO: 20.11.2015 implemntation 
public class DataTestStore implements DataStore {
    private List<PersonData> PersonsTable;
    private List<CoursData> CoursesTable;
    private List<SessionData> SessionsTable;
    private List<RoomData> RoomsTable;
    private List<PersonCoursLink> StudentsCoursTable;
    private List<PersonCoursLink> TeachersCoursTable;
    private static DataTestStore me;

    private class PersonCoursLink {
        private int personId;
        private int coursId;

        private PersonCoursLink(int personId, int coursId) {
            this.personId = personId;
            this.coursId = coursId;
        }
    }
    private void initialise(){
        PersonsTable = new ArrayList<PersonData>();
        CoursesTable = new ArrayList<CoursData>();
        SessionsTable = new ArrayList<SessionData>();
        RoomsTable = new ArrayList<RoomData>();
        StudentsCoursTable = new ArrayList<PersonCoursLink>();
        TeachersCoursTable = new ArrayList<PersonCoursLink>();
        populateData();
    }

    public static DataTestStore getMe(){
        if (me==null){
            me= new DataTestStore();
            me.initialise();
        }
        return me;

    }




    private void populateData() {

        {//add persons
            PersonsTable.add(new PersonData(1, "Manuel", "Arnaud", "arnaud@manuel", "", true, false));
            PersonsTable.add(new PersonData(2, "Borgeat", "maximilien", "boa@bax.hevs", "", true, false));
            PersonsTable.add(new PersonData(3, "Shumarer", "", "", "Proffesor of patern", false, true));
            PersonsTable.add(new PersonData(4, "LeCalvé", "Anne", "lecalvé@Anne.hevs", "proffesor of data", false, true));
            PersonsTable.add(new PersonData(5, "Pascal", "Giordano", "", "", true, false));
            PersonsTable.add(new PersonData(6, "Maurice", "Polo", "Maurice@Polo", "", true, false));
            PersonsTable.add(new PersonData(7, "Franc", "Philippe", "Philippe@Franc", "", true, false));
            PersonsTable.add(new PersonData(8, "Piot", "Vincent", "Piot@Vincent", "", true, false));
            PersonsTable.add(new PersonData(9, "Barrere", "Frédéric", "Frédéric@manuel", "", true, false));
            PersonsTable.add(new PersonData(10, "Mery", "Marc", "Marc@Mery", "", true, false));
            PersonsTable.add(new PersonData(11, "Bouteille", "Thierry", "Thierry@Bouteille", "", true, false));
            PersonsTable.add(new PersonData(12, "Kohler", "Henri", "Henri @Kohler", "", true, false));
            PersonsTable.add(new PersonData(13, "Brault", "Mathieu", "Mathieu@Brault", "", true, false));
            PersonsTable.add(new PersonData(14, "Baudoin", "Brault", "vBrault@Baudoin", "", true, false));
            PersonsTable.add(new PersonData(15, "Carton", "Joseph", "Joseph@Carton", "", true, false));
        }

        { //add rooms
            RoomsTable.add(new RoomData(1, "101"));
            RoomsTable.add(new RoomData(2, "102"));
            RoomsTable.add(new RoomData(3, "103"));
        }


        { //add cours
            CoursesTable.add(new CoursData(1, "android", "cours de programation adnroide"));
            CoursesTable.add(new CoursData(2, "sql", "cours of database"));
        }

        { //add sessions
            Calendar calendarStart = Calendar.getInstance();
            Calendar calendarEnd = Calendar.getInstance();
            calendarStart.set(2016, 1, 1, 12, 0);
            calendarEnd.set(2016, 1, 1, 13, 15);
            SessionsTable.add(new SessionData(1, 1, 1, calendarStart.getTime(), calendarEnd.getTime()));
            SessionsTable.add(new SessionData(2, 2, 2, calendarStart.getTime(), calendarEnd.getTime()));
            calendarStart.set(2016, 1, 2, 12, 0);
            calendarEnd.set(2016, 1, 2, 13, 15);
            SessionsTable.add(new SessionData(3, 1, 1, calendarStart.getTime(), calendarEnd.getTime()));
            SessionsTable.add(new SessionData(4, 2, 2, calendarStart.getTime(), calendarEnd.getTime()));
        }

        { //add Teacher in cours
            TeachersCoursTable.add(new PersonCoursLink(3, 1));
            TeachersCoursTable.add(new PersonCoursLink(4, 2));
        }

        { //add students in cours
            StudentsCoursTable.add(new PersonCoursLink(1, 1));
            StudentsCoursTable.add(new PersonCoursLink(2, 1));
            StudentsCoursTable.add(new PersonCoursLink(5, 1));
            StudentsCoursTable.add(new PersonCoursLink(6, 1));
            StudentsCoursTable.add(new PersonCoursLink(7, 1));
            StudentsCoursTable.add(new PersonCoursLink(8, 1));
            StudentsCoursTable.add(new PersonCoursLink(9, 1));
            StudentsCoursTable.add(new PersonCoursLink(10, 1));

            StudentsCoursTable.add(new PersonCoursLink(8, 2));
            StudentsCoursTable.add(new PersonCoursLink(9, 2));
            StudentsCoursTable.add(new PersonCoursLink(10, 2));
            StudentsCoursTable.add(new PersonCoursLink(11, 2));
            StudentsCoursTable.add(new PersonCoursLink(12, 2));
            StudentsCoursTable.add(new PersonCoursLink(13, 2));
            StudentsCoursTable.add(new PersonCoursLink(14, 2));
            StudentsCoursTable.add(new PersonCoursLink(15, 2));
        }


    }

    @Override
    public List<Student> getStudentsList() {
        List<Student> respons = new ArrayList<Student>();

        for (PersonData persData : PersonsTable) {
            if (persData.isStudent)
                respons.add(new Student(persData));
        }
        return respons;
    }

    @Override
    public List<Student> getStudentsList(Cours cours) {
        List<Student> respons = new ArrayList<Student>();

        for (PersonCoursLink studentLink : StudentsCoursTable) {
            if (studentLink.coursId == cours.getId()) {
                for (PersonData persData : PersonsTable) {
                    if (persData.id == studentLink.personId) {
                        respons.add(new Student(persData));
                        break;
                    }
                }
            }
        }
        return respons;
    }

    @Override
    public List<Student> getStudentsList(Session session) {
        List<Student> respons = new ArrayList<Student>();

        for (PersonCoursLink studentLink : StudentsCoursTable) {
            if (studentLink.coursId == session.getCoursId()) {
                for (PersonData persData : PersonsTable) {
                    if (persData.id == studentLink.personId) {
                        respons.add(new Student(persData));
                        break;
                    }
                }
            }
        }
        return respons;
    }

    @Override
    public int save(Student student) {
        if (student.getId() < 0) {
            student.data.id = PersonsTable.size();
            PersonsTable.add(student.data);
        } else
            for (PersonData personData : PersonsTable)
                if (personData.id == student.getId()) {
                    personData.name = student.getName();
                    personData.firstname = student.getFirstname();
                    personData.mail = student.getMail();
                    personData.isStudent = true;
                    break;
                }
return student.data.id;
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public Student getStudentById(int id) {
        Student respons = null;
        for (PersonData persData : PersonsTable) {
            if (persData.id == id) {
                respons = new Student(persData);
                break;
            }
        }
        return respons;
    }

    @Override
    public List<Teacher> getTeachersList() {
        List<Teacher> respons = new ArrayList<Teacher>();

        for (PersonData persData : PersonsTable) {
            if (persData.isTeacher)
                respons.add(new Teacher(persData));
        }
        return respons;
    }

    @Override
    public List<Teacher> getTeachersList(Cours cours) {
        List<Teacher> respons = new ArrayList<Teacher>();

        for (PersonCoursLink studentLink : StudentsCoursTable) {
            if (studentLink.coursId == cours.getId()) {
                for (PersonData persData : PersonsTable) {
                    if (persData.id == studentLink.personId) {
                        respons.add(new Teacher(persData));
                        break;
                    }
                }
            }
        }
        return respons;


    }

    @Override
    public List<Teacher> getTeachersList(Session session) {
        List<Teacher> respons = new ArrayList<Teacher>();

        for (PersonCoursLink studentLink : StudentsCoursTable) {
            if (studentLink.coursId == session.getCoursId()) {
                for (PersonData persData : PersonsTable) {
                    if (persData.id == studentLink.personId) {
                        respons.add(new Teacher(persData));
                        break;
                    }
                }
            }
        }
        return respons;
    }

    @Override
    public int save(Teacher teacher) {

        if (teacher.getId() < 0) {
            teacher.data.id = PersonsTable.size();
            PersonsTable.add(teacher.data);
        } else
            for (PersonData personData : PersonsTable)
                if (personData.id == teacher.getId()) {
                    personData.name = teacher.getName();
                    personData.firstname = teacher.getFirstname();
                    personData.mail = teacher.getMail();
                    personData.description = teacher.getDescription();
                    personData.isTeacher = true;
                    break;
                }
        return teacher.data.id;
    }

    @Override
    public void delete(Teacher teacher) {

    }

    @Override
    public Teacher getTeacherById(int id) {
        Teacher respons = null;
        for (PersonData persData : PersonsTable) {
            if (persData.id == id) {
                respons = new Teacher(persData);
                break;
            }
        }
        return respons;
    }

    @Override
    public List<Cours> getCoursList() {
        List<Cours> respons = new ArrayList<Cours>();

        for (CoursData coursData : CoursesTable) {
            respons.add(new Cours(coursData));
        }
        return respons;
    }

    @Override
    public Cours getCoursById(int id) {
        Cours respons = null;

        for (CoursData coursData : CoursesTable) {
            if (coursData.id == id) {
                respons = new Cours(coursData);
                break;
            }
        }
        return respons;
    }

    @Override
    public List<Cours> getCoursFor(Student student) {
        if (student == null)
            return null;
        List<Cours> respons = new ArrayList<Cours>();
        for (PersonCoursLink actualCoursLink : StudentsCoursTable) {
            if (actualCoursLink.personId == student.getId())
                respons.add(getCoursById(actualCoursLink.coursId));
        }
        return respons;
    }

    @Override
    public List<Cours> getCoursFor(Teacher teacher) {
        if (teacher == null)
            return null;
        List<Cours> respons = new ArrayList<Cours>();
        for (PersonCoursLink actualCoursLink : TeachersCoursTable) {
            if (actualCoursLink.personId == teacher.getId())
                respons.add(getCoursById(actualCoursLink.coursId));
        }
        return respons;
    }

    @Override
    public int save(Cours cours) {
        //TODO save cours
return 0;
    }

    @Override
    public void delete(Cours cours) {

    }

    @Override
    public Session getSessionById(int id) {
        SessionData sesData = null;
        for (SessionData sd : SessionsTable) {
            if (sd.id == id) {
                sesData = sd;
                break;
            }
        }
        if (sesData == null)
            return null;
        Cours cours = getCoursById(sesData.coursId);
        Room room = getRoomById(sesData.roomId);
        if (room == null || cours == null)
            return null;

        return new Session(sesData, cours, room);
    }

    @Override
    public List<Session> getSessionFor(Cours cours) {
        if (cours == null)
            return null;
        List<Session> respons = new ArrayList<Session>();
        for (SessionData sessionData : SessionsTable) {
            if (sessionData.coursId == cours.getId()) {
                Cours ncours = getCoursById(sessionData.coursId);
                Room room = getRoomById(sessionData.roomId);
                if (room != null && ncours != null)
                    respons.add(new Session(sessionData, ncours, room));
            }
        }
        return respons;
    }

    @Override
    public List<Session> getSessionFor(Teacher teacher) {
        if (teacher == null)
            return null;

        List<Session> result = new ArrayList<Session>();
        for (Cours cours : getCoursFor(teacher)) {
            for (Session session : getSessionFor(cours))
                result.add(session);
        }
        return result;
    }

    @Override
    public List<Session> getSessionFor(Student student) {
        if (student == null)
            return null;

        List<Session> result = new ArrayList<Session>();
        for (Cours cours : getCoursFor(student)) {
            for (Session session : getSessionFor(cours))
                result.add(session);
        }
        return result;
    }

    @Override
    public List<Session> getAllSession(Cours cours) {
        return null;
    }


    @Override
    public int save(Session session) {
// TODO: 21.11.2015 implementation
        return 0;
    }

    @Override
    public void delete(Session session) {

    }

    public Room getRoomById(int id) {
        Room room = null;
        for (RoomData roomData : RoomsTable) {
            if (roomData.id == id) {
                room = new Room(roomData);
                break;
            }
        }
        return room;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end) {
        List<Room> respons= new ArrayList<Room>();
        for (RoomData roomData : RoomsTable)
            respons.add(new Room(roomData));
        return respons;
    }

    @Override
    public List<Room> getRooms() {
        List<Room> respons= new ArrayList<Room>();
        for (RoomData roomData : RoomsTable)
            respons.add(new Room(roomData));
        return respons;
    }

    @Override
    public void setCours(Student student, List<Cours> selected) {
        //// TODO: 28.11.2015  du vide
    }

    @Override
    public void setTeacher(Cours cours, List<Teacher> selected) {

    }


}

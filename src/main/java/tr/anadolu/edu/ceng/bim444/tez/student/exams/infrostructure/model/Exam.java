package tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model;

import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:57 AM
 */
public class Exam {
    private String examId;
    private Person teacher;
    private String name;
    private String date;
    private String duration;
    private String location;
    private String info;
    private String isStarted;
    private String grade;

    public Exam(String examId, Person teacher, String name,
                String date, String duration, String location, String info, String started,
                String grade) {
        this.examId = examId;
        this.teacher = teacher;
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.location = location;
        this.info = info;
        isStarted = started;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Person getTeacher() {
        return teacher;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStarted() {
        return isStarted;
    }

    public void setStarted(String started) {
        isStarted = started;
    }
}

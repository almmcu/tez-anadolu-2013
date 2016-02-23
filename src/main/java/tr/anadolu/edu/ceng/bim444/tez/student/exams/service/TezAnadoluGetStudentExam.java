package tr.anadolu.edu.ceng.bim444.tez.student.exams.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.dao.ExamDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Exam;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;


/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */

@Component
@Path("/student/exam")
public class TezAnadoluGetStudentExam {


    @GET
    @Path("/get")
    @Produces("application/json")
    public List<Exam> searchPerson(@QueryParam("id") String id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        List<Exam> result;
        result = examDAO.searchExam(id);

        return result;
    }

    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public Status searchStatus(@QueryParam("id") String id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        Status result;
        result = examDAO.findStatusByExamId(id);

        return result;
    }
}

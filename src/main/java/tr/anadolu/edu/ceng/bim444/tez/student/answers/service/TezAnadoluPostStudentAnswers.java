package tr.anadolu.edu.ceng.bim444.tez.student.answers.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.dao.AnswerDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.AnswerPosted;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.Answers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */

@Component
@Path("/students/answer")
public class TezAnadoluPostStudentAnswers {


    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response postAnswers(Answers answers) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        AnswerDAO answerDAO = (AnswerDAO) context.getBean("answerDAO");
        int size = answers.getAnswers().size();
        size = size - 1;

        String result = "Answers saved : \n\n\n\n\n\n";
        String fileName = new Date().getTime() + ".txt";
        fileName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss'.txt'").format(new Date());
        try {
            FileWriter writer = new FileWriter("measure/10cm/" + fileName, true);
            for (int i = 0; i < answers.getAnswers().size(); i++) {
                System.out.print("accelerometer " + i + " = " + answers.getAnswers().get(i).getAccelerometer());
                System.out.println("Time Interval  " + answers.getAnswers().get(i).getTimeInterval());
                writer.write(answers.getAnswers().get(i).getAccelerometer() +
                        " "
                        + answers.getAnswers().get(i).getTimeInterval());
                writer.write("\r\n");   // write new line
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(201).entity(result).build();

    }




}

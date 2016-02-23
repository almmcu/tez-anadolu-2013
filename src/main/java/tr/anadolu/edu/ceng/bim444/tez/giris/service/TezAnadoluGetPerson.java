package tr.anadolu.edu.ceng.bim444.tez.giris.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao.PersonDAO;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.PersonInfo;

import javax.ws.rs.*;


/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */

@Component
@Path("/login/get")
public class TezAnadoluGetPerson {

    @GET
    @Path("/{name}")
    @Produces("application/json")
    public PersonInfo searchPerson(@PathParam("name") String name, @QueryParam("id") String id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        PersonDAO customerDAO = (PersonDAO) context.getBean("customerDAO");
        Boolean result;
        result = customerDAO.searchPerson(id, name);

        return customerDAO.findByPersonId(id, result);


    }
}

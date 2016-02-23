package tr.anadolu.edu.ceng.bim444.tez.giris.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao.PersonDAO;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.PersonInfo;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");

        PersonDAO customerDAO = (PersonDAO) context.getBean("customerDAO");
        Person person = new Person("21527663776", "ali", "", "mumcu", "@", "12345");
        customerDAO.insert(person, "teacher");


        PersonInfo person1 = customerDAO.findByPersonId("23", true);
        System.out.println(person1);

    }

}

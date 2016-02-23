package tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao;


import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.PersonInfo;

public interface PersonDAO {
    public void insert(Person person, String name);

    public PersonInfo findByPersonId(String personId, Boolean name);

    public boolean searchPerson(String personInfo, String name);

    public String getStatus();
}





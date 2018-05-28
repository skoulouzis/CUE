package nl.uva.sne.vre4eic.cue.service;

import java.util.List;
import nl.uva.sne.vre4eic.cue.dao.PersonDAO;
import nl.uva.sne.vre4eic.cue.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDao;

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = personDao.getAllPersons();
        return persons;
    }

    @Override
    public void insertPerson(Person person) {
        personDao.insertPerson(person);
    }

}

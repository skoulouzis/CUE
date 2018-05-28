package nl.uva.sne.vre4eic.cue.service;

import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Person;

public interface PersonService {

    public List<Person> getAllPersons();

    public void insertPerson(Person person);
}

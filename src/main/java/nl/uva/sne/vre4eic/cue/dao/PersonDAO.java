package nl.uva.sne.vre4eic.cue.dao;

import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Person;

public interface PersonDAO {

    public List<Person> getAllPersons();

    public void insertPerson(Person person);
}

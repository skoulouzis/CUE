package nl.uva.sne.vre4eic.cue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import nl.uva.sne.vre4eic.cue.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDAO {

    @Autowired
    private DataSource dataSource;

    @Transactional(readOnly = true)
    @Override
    public List<Person> getAllPersons() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = null;
            PreparedStatement ps = conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void insertPerson(Person employee) {
    }

}

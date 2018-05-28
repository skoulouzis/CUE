/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import nl.uva.sne.vre4eic.cue.dao.PersonDAO;
import nl.uva.sne.vre4eic.cue.model.Person;
import nl.uva.sne.vre4eic.cue.service.PersonService;
import nl.uva.sne.vre4eic.cue.util.WriteCsvToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author S. Koulouzis
 */
@RestController
public class CsvController {

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/cities", produces = "text/csv", method = RequestMethod.GET)
    public void findCity(@RequestParam Map<String, String> requestParams, HttpServletResponse response) {
        try {
            System.err.println(requestParams);

            List<Person> persons = service.getAllPersons();

            WriteCsvToResponse.writePersons(response.getWriter(), persons);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            Logger.getLogger(CsvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

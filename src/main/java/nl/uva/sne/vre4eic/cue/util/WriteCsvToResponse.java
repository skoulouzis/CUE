/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.PrintWriter;
import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Person;

/**
 *
 * @author S. Koulouzis
 */
public class WriteCsvToResponse {

    public static void writePersons(PrintWriter writer, List<Person> cities) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        ColumnPositionMappingStrategy mapStrategy
                = new ColumnPositionMappingStrategy();

        mapStrategy.setType(Object.class);
        mapStrategy.generateHeader();

        String[] columns = new String[]{"id", "name", "population"};
        mapStrategy.setColumnMapping(columns);

        StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(mapStrategy)
                .withSeparator(',')
                .build();

        btcsv.write(cities);

    }

    public static void writePerson(PrintWriter writer, Object city) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        ColumnPositionMappingStrategy mapStrategy
                = new ColumnPositionMappingStrategy();

        mapStrategy.setType(Object.class);

        String[] columns = new String[]{"id", "name", "population"};
        mapStrategy.setColumnMapping(columns);

        StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(mapStrategy)
                .withSeparator(',')
                .build();

        btcsv.write(city);

    }
}

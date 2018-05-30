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
import java.io.Reader;
import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Argo;
import static nl.uva.sne.vre4eic.cue.util.Consts.COLUMS;

/**
 *
 * @author S. Koulouzis
 */
public class CsvUtils {

    public static void writeArgoToWriter(PrintWriter writer, List<Argo> argo) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        ColumnPositionMappingStrategy mapStrategy
                = new ColumnPositionMappingStrategy();

        mapStrategy.setType(Argo.class);
        mapStrategy.generateHeader();

        mapStrategy.setColumnMapping(COLUMS);
        
        StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(mapStrategy)
                .withSeparator(',')
                .build();
        String[] header = mapStrategy.generateHeader();
        if (header.length > 0) {
            btcsv.write(header);
        }
        btcsv.write(argo);

    }

    public static void readArgo(Reader reader, List<Argo> argo) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
//        CsvToBean<Argo> csvToBean = new CsvToBeanBuilder(reader)
//                .withType(Argo.class)
//                .withIgnoreLeadingWhiteSpace(true)
//                .build();
    }
}

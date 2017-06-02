package br.ufc.deti.ecgweb.utils.ecgformat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.cvrgrid.hl7aecg.Hl7EcgLeadData.PagedEcgXYDataset;
import org.cvrgrid.hl7aecg.HL7PreprocessReturn;
import org.cvrgrid.hl7aecg.Hl7Ecg;
import org.cvrgrid.hl7aecg.Hl7EcgLeadData;

public class ReaderHl7 {

    public static List<Signal> read(File file) throws IOException, JAXBException {
        InputStream inputStream = new FileInputStream(file);
        HL7PreprocessReturn ret = Hl7Ecg.preprocess(inputStream);

        Hl7EcgLeadData data = ret.getLeadData();
        int leadCount = ret.getLeadCount();
        int numberPoints = data.getNumberOfPoints();

        List<Signal> signals = new ArrayList<Signal>();

        for (int l = 0; l < leadCount; l++) {
            PagedEcgXYDataset peg = (PagedEcgXYDataset) data.getOneXYDataset(l);

            Signal signal = new Signal(data.getLeadName()[l]);

            for (int i = 0; i < numberPoints; i++) {
                signal.add(peg.getYValue(0, i));
            }

            signal.setTimeIncrement(data.getTimeIncrement());
            signal.setTimeUnit(data.getTimeUnit());

            signal.setScale(data.getLeadScaleValue(l));
            signal.setScaleUnit(data.getLeadScaleUnit(l));

            signal.setOrigin(data.getLeadOriginValue(l));
            signal.setOriginUnit(data.getLeadOriginUnit(l));

            signals.add(signal);
        }
        return signals;
    }
}

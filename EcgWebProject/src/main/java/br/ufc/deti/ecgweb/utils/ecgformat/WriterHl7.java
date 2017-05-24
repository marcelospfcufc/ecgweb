package br.ufc.deti.ecgweb.utils.ecgformat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class WriterHl7 {
	private Namespace defaultNS;
	private Namespace xsi;
	private Document doc;

	private void annotatedECG(Element previous, String uid, String timeLow, String timeHigh) {
		Element id = new Element("id", defaultNS);
		id.setAttribute("root", uid);
		previous.addContent(id);

		Element code = new Element("code", defaultNS);
		code.setAttribute("code", "93000");
		code.setAttribute("codeSystem", "2.16.840.1.113883.6.12");
		previous.addContent(code);

		Element effectiveTime = new Element("effectiveTime", defaultNS);
		previous.addContent(effectiveTime);

		Element low = new Element("low", defaultNS);
		low.setAttribute("value", timeLow);
		effectiveTime.addContent(low);

		Element high = new Element("high", defaultNS);
		high.setAttribute("value", timeHigh);
		effectiveTime.addContent(high);
	}

	private void clinicalTrial(Element previous, String uid) {
		Element clinicalTrial = new Element("clinicalTrial", defaultNS);
		previous.addContent(clinicalTrial);

		Element id = new Element("id", defaultNS);
		id.setAttribute("root", uid);
		clinicalTrial.addContent(id);
	}

	private void subject(Element previous, String uid) {
		Element subject = new Element("subject", defaultNS);
		previous.addContent(subject);

		Element trialSubject = new Element("trialSubject", defaultNS);
		subject.addContent(trialSubject);

		Element id = new Element("id", defaultNS);
		id.setAttribute("root", uid);
		trialSubject.addContent(id);
	}

	private void subjectAssignment(Element previous, String name) {
		Element subjectAssignment = new Element("subjectAssignment", defaultNS);
		previous.addContent(subjectAssignment);

		Element subject = new Element("subject", defaultNS);
		subject.addContent(name);
		subjectAssignment.addContent(subject);
	}

	private void timepointEvent(Element previous) {
		Element timepointEvent = new Element("timepointEvent", defaultNS);
		previous.addContent(timepointEvent);
	}

	private void manufacturedSeriesDevice(Element previous) {
		Element author = new Element("author", defaultNS);
		previous.addContent(author);

		Element seriesAuthor = new Element("seriesAuthor", defaultNS);
		author.addContent(seriesAuthor);

		Element manufacturedSeriesDevice = new Element("manufacturedSeriesDevice", defaultNS);
		seriesAuthor.addContent(manufacturedSeriesDevice);
	}

	private void secondaryPerformer(Element previous) {
		Element secondaryPerformer = new Element("secondaryPerformer", defaultNS);
		previous.addContent(secondaryPerformer);

		Element seriesPerformer = new Element("seriesPerformer", defaultNS);
		secondaryPerformer.addContent(seriesPerformer);
	}

	private void annotationSet(Element previous) {
		Element subjectOf = new Element("subjectOf", defaultNS);
		previous.addContent(subjectOf);

		Element annotationSet = new Element("annotationSet", defaultNS);
		subjectOf.addContent(annotationSet);
	}

	private Element sequenceSet(Element previous, String timeLow, String timeHigh) {
		Element component1 = new Element("component", defaultNS);
		previous.addContent(component1);

		Element series = new Element("series", defaultNS);
		component1.addContent(series);

		Element code = new Element("code", defaultNS);
		code.setAttribute("code", "RHYTHM");
		code.setAttribute("codeSystem", "2.16.840.1.113883.5.4");
		code.setAttribute("codeSystemName", "ActCode");
		series.addContent(code);

		Element effectiveTime = new Element("effectiveTime", defaultNS);
		series.addContent(effectiveTime);

		Element low = new Element("low", defaultNS);
		low.setAttribute("value", timeLow);
		effectiveTime.addContent(low);

		Element high = new Element("high", defaultNS);
		high.setAttribute("value", timeHigh);
		effectiveTime.addContent(high);

		Element component = new Element("component", defaultNS);
		series.addContent(component);

		Element sequenceSet = new Element("sequenceSet", defaultNS);
		component.addContent(sequenceSet);

		return sequenceSet;
	}

	private void component0(Element previousComp, double timeIncrement, String timeUnit) {
		Element component = new Element("component", defaultNS);
		previousComp.addContent(component);

		Element sequence = new Element("sequence", defaultNS);
		component.addContent(sequence);

		Element code = new Element("code", defaultNS);
		code.setAttribute("code", "TIME_RELATIVE");
		code.setAttribute("codeSystem", "2.16.840.1.113883.5.4");
		code.setAttribute("codeSystemName", "ActCode");
		code.setAttribute("displayName", "Relative Time");
		sequence.addContent(code);

		Element value = new Element("value", defaultNS);
		value.setAttribute("type", "GLIST_PQ", xsi);
		sequence.addContent(value);

		Element head = new Element("head", defaultNS);
		head.setAttribute("value", "0.000");
		head.setAttribute("unit", "s");
		value.addContent(head);

		Element increment = new Element("increment", defaultNS);
		increment.setAttribute("value", String.valueOf(timeIncrement));
		increment.setAttribute("unit", timeUnit);
		value.addContent(increment);

	}

	private void componentData(Element previousComp, String code, String data, double origin, String originUnit,
			double scale, String scaleUnit) {
		Element component = new Element("component", defaultNS);
		previousComp.addContent(component);

		Element sequence = new Element("sequence", defaultNS);
		component.addContent(sequence);

		Element eCode = new Element("code", defaultNS);
		eCode.setAttribute("code", code);
		eCode.setAttribute("codeSystem", "2.16.840.1.113883.6.24");
		eCode.setAttribute("codeSystemName", "MDC");
		sequence.addContent(eCode);

		Element value = new Element("value", defaultNS);
		value.setAttribute("type", "SLIST_PQ", xsi);
		sequence.addContent(value);

		Element originE = new Element("origin", defaultNS);
		originE.setAttribute("value", String.valueOf(origin));
		originE.setAttribute("unit", originUnit);
		value.addContent(originE);

		Element scaleE = new Element("scale", defaultNS);
		scaleE.setAttribute("value", String.valueOf(scale));
		scaleE.setAttribute("unit", scaleUnit);
		value.addContent(scaleE);

		Element digits = new Element("digits", defaultNS);
		digits.addContent(data);
		value.addContent(digits);

	}

	private Element header() {
		Element header = new Element("AnnotatedECG");

		header.setNamespace(Namespace.getNamespace("urn:hl7-org:v3"));
		header.addNamespaceDeclaration(Namespace.getNamespace("voc", "urn:hl7-org:v3/voc"));

		xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		header.addNamespaceDeclaration(xsi);

		header.setAttribute("schemaLocation", "urn:hl7-org:v3 /HL7/aECG/2003-12/schema/PORT_MT020001.xsd", xsi);
		header.setAttribute("type", "Observation");
		header.setAttribute("classCode", "OBS");

		doc = new Document(header);

		defaultNS = header.getNamespace();
		return header;
	}

	private String convertList(List<Double> list) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			str.append((int) list.get(i).doubleValue() + " ");
		}
		return str.toString();
	}

	public void write(String path, List<Signal> signals) throws IOException {
		Element header = header();

		annotatedECG(header, UUID.randomUUID().toString(), "20020510092700", "20020510092900");
		clinicalTrial(header, UUID.randomUUID().toString());
		subject(header, UUID.randomUUID().toString());
		subjectAssignment(header, "Name");
		timepointEvent(header);
		manufacturedSeriesDevice(header);
		secondaryPerformer(header);

		Element sequenceSet = sequenceSet(header, "20020510092700", "20020510092900");

		double timeIncrement = signals.get(0).getTimeIncrement();
		String timeUnit = signals.get(0).getTimeUnit();
		component0(sequenceSet, timeIncrement, timeUnit);

		String name = null;
		String data = null;
		for (int i = 0; i < signals.size(); i++) {
			name = signals.get(i).getName();
			data = convertList(signals.get(i).getSignal());
			componentData(sequenceSet, name, data, signals.get(i).getOrigin(), signals.get(i).getOriginUnit(),
					signals.get(i).getScale(), signals.get(i).getScaleUnit());
		}

		annotationSet(header);

		XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());

		FileWriter file = new FileWriter(path);
		xout.output(doc, file);
	}

	public static void main(String[] args) {
		WriterHl7 writer = new WriterHl7();

		List<Signal> signals = new ArrayList<Signal>();
		Signal s1 = new Signal("MDC_ECG_LEAD_I");
		s1.setTimeUnit("s");
		s1.setTimeIncrement(1.0 / 360); // (1/freq)
		s1.setOriginUnit("uV");
		s1.setOrigin(0);
		s1.setScaleUnit("uV");
		s1.setScale(1);
		
		for(int i =0 ; i < 1000; i++){
			s1.add(i%100.0);			
		}

		Signal s2 = new Signal("MDC_ECG_LEAD_II");
		s2.setTimeUnit("s");
		s2.setTimeIncrement(1.0 / 360); // (1/freq)
		s2.setOriginUnit("uV");
		s2.setOrigin(100);
		s2.setScaleUnit("uV");
		s2.setScale(1);
		
		for(int i =0 ; i < 1000; i++){
			s2.add( i % 100.0);			
		}
		
		signals.add(s1);
		signals.add(s2);
		signals.add(s1);
		signals.add(s2);

		signals.add(s1);
		signals.add(s2);
		signals.add(s1);
		signals.add(s2);

		signals.add(s1);
		signals.add(s2);
		signals.add(s1);
		signals.add(s2);

		try {
			writer.write(".//src//arquivoXML.xml", signals);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("End");
	}
}

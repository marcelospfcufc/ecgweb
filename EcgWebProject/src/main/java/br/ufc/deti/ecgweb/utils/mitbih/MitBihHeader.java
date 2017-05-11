/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.mitbih;

import br.ufc.deti.ecgweb.domain.client.GenderType;
import br.ufc.deti.ecgweb.domain.exam.EcgLeadType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class MitBihHeader {

    private String recordName;
    private int nChannel;
    private long sampleRate;
    private long nSamples;
    private long baseLine;
    private long gain;
    private int age;
    private GenderType gender;
    private EcgLeadType typeChannel1;
    private EcgLeadType typeChannel2;
    
    private EcgLeadType getLeadTypeFromString(String value) {
        
        if(value.compareTo("MLI") == 0) {
            return EcgLeadType.I;
        }else if(value.compareTo("MLII") == 0) {
            return EcgLeadType.II;
        }else if(value.compareTo("MLIII") == 0) {
            return EcgLeadType.III;
        }else if(value.compareTo("V1") == 0) {
            return EcgLeadType.V1;            
        }else if(value.compareTo("V2") == 0) {
            return EcgLeadType.V2;
        }else if(value.compareTo("V3") == 0) {
            return EcgLeadType.V3;
        }else if(value.compareTo("V4") == 0) {
            return EcgLeadType.V4;
        }else if(value.compareTo("V5") == 0) {
            return EcgLeadType.V5;
        }else if(value.compareTo("V6") == 0) {
            return EcgLeadType.V6;
        }
        
        return EcgLeadType.II;
        
    }
    
    private void fillFirstLine(String firstLine) {        
        String split[] = firstLine.split(" ");
        recordName = split[0];
        nChannel = Integer.parseInt(split[1]);
        sampleRate = Integer.parseInt(split[2]);
        nSamples = Integer.parseInt(split[3]);
    }
    
    private void fillSecondLine(String line) {
        String split[] = line.split(" ");
        gain = Integer.parseInt(split[2]);
        baseLine = Integer.parseInt(split[4]);        
        typeChannel1 = getLeadTypeFromString(split[8]);        
    }
    
    private void fillThirdLine(String line) {
        String split[] = line.split(" ");
        gain = Integer.parseInt(split[2]);
        baseLine = Integer.parseInt(split[4]);        
        typeChannel2 = getLeadTypeFromString(split[8]);        
    }
    
    private void fillFourthLine(String line) {
        String split[] = line.split(" ");
        age = Integer.parseInt(split[1]);
        gender = split[2].compareTo("M") == 0 ? GenderType.Male : GenderType.Female;                
    }
    
    

    public MitBihHeader(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);        
        fillFirstLine(lines.get(0));
        fillSecondLine(lines.get(1));
        fillThirdLine(lines.get(2));        
        fillFourthLine(lines.get(2));        
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getnChannel() {
        return nChannel;
    }

    public void setnChannel(int nChannel) {
        this.nChannel = nChannel;
    }

    public long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public long getnSamples() {
        return nSamples;
    }

    public void setnSamples(long nSamples) {
        this.nSamples = nSamples;
    }

    public long getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(int baseLine) {
        this.baseLine = baseLine;
    }

    public long getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public EcgLeadType getTypeChannel1() {
        return typeChannel1;
    }

    public void setTypeChannel1(EcgLeadType typeChannel1) {
        this.typeChannel1 = typeChannel1;
    }

    public EcgLeadType getTypeChannel2() {
        return typeChannel2;
    }

    public void setTypeChannel2(EcgLeadType typeChannel2) {
        this.typeChannel2 = typeChannel2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
    
    

    @Override
    public String toString() {
        return "MitBihHeader{" + "recordName=" + recordName + ", nChannel=" + nChannel + ", sampleRate=" + sampleRate + ", nSamples=" + nSamples + ", baseLine=" + baseLine + ", gain=" + gain + ", typeChannel1=" + typeChannel1 + ", typeChannel2=" + typeChannel2 + '}';
    }
}

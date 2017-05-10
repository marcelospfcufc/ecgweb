/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.mitbih;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class MitBihData {
    private final List<Double> channel1 = new ArrayList<Double>();
    private final List<Double> channel2 = new ArrayList<Double>();    

    public MitBihData(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);        
        
        for (String line : lines) {
            Scanner scan = new Scanner(line);
            
            
            scan.nextDouble();
            channel1.add(scan.nextDouble());
            channel2.add(scan.nextDouble());    
            
        }
    }
    public void addSignalToChannel1(Double signal) {
        channel1.add(signal);
        
    }
    
    public void addSignalToChannel2(Double signal) {
        channel2.add(signal);
    }

    public List<Double> getChannel1() {
        return channel1;
    }

    public List<Double> getChannel2() {
        return channel2;
    }
    
    @Override
    public String toString() {
        return "MitBihData{" + "channel1=" + channel1 + ", channel2=" + channel2 + '}';
    }
}

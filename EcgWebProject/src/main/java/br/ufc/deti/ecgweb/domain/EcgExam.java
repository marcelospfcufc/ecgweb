/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javadev
 */
public class EcgExam extends AbstractExam{
    private List<EcgPoint> points = new ArrayList();    
    private List<EcgAnnotation> annotations = new ArrayList();

    public EcgExam() {
    }
    
    public void addPoint(int x, int y) throws Exception {                
        
        if(points.size() > 0) {
            EcgPoint p = points.get(points.size() - 1);
            if (p.getX() > x) {
                throw new InvalidOrderPointException();
            }
        }
        
        points.add(new EcgPoint(x,y));
    }    

    public EcgPoint getPoint(int idx) {
        return points.get(idx);
    }    
    
    public void remPoint(int idx) throws UnsupportedOperationException, IndexOutOfBoundsException{
        points.remove(idx);
    }    

    public List<EcgPoint> getPoints() {
        return points;
    }

    public void setPoints(List<EcgPoint> points) {
        this.points = points;
    }

    public List<EcgAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<EcgAnnotation> annotations) {
        this.annotations = annotations;
    }
    
    public void addAnnotation(Doctor doctor, double msTime, String comment) {
        EcgAnnotation annotation = new EcgAnnotation(doctor, msTime, comment);        
        annotations.add(annotation);
    }
    
    public void remAnnotation(int idx) {
        annotations.remove(idx);
    }
    
    public EcgAnnotation getAnnotation(int idx) {
        return annotations.get(idx);
    }
}

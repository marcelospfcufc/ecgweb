/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetEcgInformationResponseDTO{
    private Double heartRate;
    private HeartRateUnityTypeDTO heartRateUnity;
    
    
    private Double qrsDuration;
    private TimeUnityTypeDTO qrsDurationUnity;
    
    private Double tWaveDuration;
    private TimeUnityTypeDTO tWaveDurationUnity;
    
    private Double pWaveDuration;
    private TimeUnityTypeDTO pWaveDurationUnity;

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Double getQrsDuration() {
        return qrsDuration;
    }

    public void setQrsDuration(Double qrsDuration) {
        this.qrsDuration = qrsDuration;
    }

    public Double gettWaveDuration() {
        return tWaveDuration;
    }

    public void settWaveDuration(Double tWaveDuration) {
        this.tWaveDuration = tWaveDuration;
    }

    public Double getpWaveDuration() {
        return pWaveDuration;
    }

    public void setpWaveDuration(Double pWaveDuration) {
        this.pWaveDuration = pWaveDuration;
    }

    public HeartRateUnityTypeDTO getHeartRateUnity() {
        return heartRateUnity;
    }

    public void setHeartRateUnity(HeartRateUnityTypeDTO heartRateUnity) {
        this.heartRateUnity = heartRateUnity;
    }

    public TimeUnityTypeDTO getQrsDurationUnity() {
        return qrsDurationUnity;
    }

    public void setQrsDurationUnity(TimeUnityTypeDTO qrsDurationUnity) {
        this.qrsDurationUnity = qrsDurationUnity;
    }

    public TimeUnityTypeDTO gettWaveDurationUnity() {
        return tWaveDurationUnity;
    }

    public void settWaveDurationUnity(TimeUnityTypeDTO tWaveDurationUnity) {
        this.tWaveDurationUnity = tWaveDurationUnity;
    }

    public TimeUnityTypeDTO getpWaveDurationUnity() {
        return pWaveDurationUnity;
    }

    public void setpWaveDurationUnity(TimeUnityTypeDTO pWaveDurationUnity) {
        this.pWaveDurationUnity = pWaveDurationUnity;
    }
    
    
}

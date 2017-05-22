package br.ufc.deti.ecgweb.application.dto;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetPlotRRResponseDTO{

    private List<RRIntervalDTO> plotRR;
    private TimeUnityTypeDTO yUnity;
    private TimeUnityTypeDTO xUnity;

    public GetPlotRRResponseDTO() {
    }

    public List<RRIntervalDTO> getPlotRR() {
        return plotRR;
    }

    public void setPlotRR(List<RRIntervalDTO> plotRR) {
        this.plotRR = plotRR;
    }

    public TimeUnityTypeDTO getyUnity() {
        return yUnity;
    }

    public void setyUnity(TimeUnityTypeDTO yUnity) {
        this.yUnity = yUnity;
    }

    public TimeUnityTypeDTO getxUnity() {
        return xUnity;
    }

    public void setxUnity(TimeUnityTypeDTO xUnity) {
        this.xUnity = xUnity;
    }

    
    

}

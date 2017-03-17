/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

/**
 *
 * @author Marcelo Araujo Lima
 */
public enum EcgLeadType {
    ECG_LEAD_I,
    ECG_LEAD_II,
    ECG_LEAD_III,
    ECG_LEAD_AVR,
    ECG_LEAD_AVL,
    ECG_LEAD_AVF,
    ECG_LEAD_V1,
    ECG_LEAD_V2,
    ECG_LEAD_V3,
    ECG_LEAD_V4,
    ECG_LEAD_V5,
    ECG_LEAD_V6;

    public static String getStringValue(EcgLeadType type) {
        String value = null;
        switch (type) {
            case ECG_LEAD_I:
                value = "I";
                break;
            case ECG_LEAD_II:
                value = "II";
                break;
            case ECG_LEAD_III:
                value = "III";
                break;
            case ECG_LEAD_AVR:
                value = "AVR";
                break;

            case ECG_LEAD_AVL:
                value = "AVL";
                break;

            case ECG_LEAD_AVF:
                value = "AVF";
                break;

            case ECG_LEAD_V1:
                value = "V1";
                break;
            case ECG_LEAD_V2:
                value = "V2";
                break;

            case ECG_LEAD_V3:
                value = "V3";
                break;

            case ECG_LEAD_V4:
                value = "V4";
                break;

            case ECG_LEAD_V5:
                value = "V5";
                break;

            case ECG_LEAD_V6:
                value = "V1";
                break;

            default:
                break;
        }

        return value;
    }
    
    public static EcgLeadType getEcgLeadTypefromStr(String strType) {
        if(strType.equalsIgnoreCase("I")) {
            return ECG_LEAD_I;
        }else if(strType.equalsIgnoreCase("II")) {
            return ECG_LEAD_II;
        }else if(strType.equalsIgnoreCase("III")) {
            return ECG_LEAD_III;
        }else if(strType.equalsIgnoreCase("AVR")) {
            return ECG_LEAD_AVR;
        }else if(strType.equalsIgnoreCase("AVL")) {
            return ECG_LEAD_AVL;
        }else if(strType.equalsIgnoreCase("AVF")) {
            return ECG_LEAD_AVF;
        }else if(strType.equalsIgnoreCase("V1")) {
            return ECG_LEAD_V1;
        }else if(strType.equalsIgnoreCase("V2")) {
            return ECG_LEAD_V2;
        }else if(strType.equalsIgnoreCase("V3")) {
            return ECG_LEAD_V3;
        }else if(strType.equalsIgnoreCase("V4")) {
            return ECG_LEAD_V4;
        }else if(strType.equalsIgnoreCase("V5")) {
            return ECG_LEAD_V5;
        }else if(strType.equalsIgnoreCase("V6")) {
            return ECG_LEAD_V6;
        }else {
            return null;
        }
    }
}

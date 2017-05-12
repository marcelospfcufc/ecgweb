package br.ufc.deti.ecgweb.utils.algorithms;

import java.util.ArrayList;

public class SegmentaWaveletTrousQRS {

    private double[] Ebeats;
    private double[] Onset;
    private double[] Offset;

    public double[] getEbeats() {
        return Ebeats;
    }

    public void setEbeats(double[] ebeats) {
        Ebeats = ebeats;
    }

    public double[] getOnset() {
        return Onset;
    }

    public void setOnset(double[] onset) {
        Onset = onset;
    }

    public double[] getOffset() {
        return Offset;
    }

    public void setOffset(double[] offset) {
        Offset = offset;
    }

    // Onset,Offset,Ebeats
    public void process(double[] sinal, double[] vetorR, double fam) {
        int[] Escalas = {1, 2}; // Escalas a serem aplicadas no processo de
        // segmenta��o
        double sfreq = fam; // Frequencia de amostragem
        double offset1 = Math.round(0.3 * fam); // % Inicio da janela de busca
        // para QRS onset
        double offset2 = Math.round(0.19 * fam); // % Fim da janela de busca
        // para QRS offset
        double[] vetor_on = new double[Escalas.length];
        double[] vetor_off = new double[Escalas.length];

        Ebeats = new double[vetorR.length];
        Onset = new double[vetorR.length];
        Offset = new double[vetorR.length];

        for (int beat = 0; beat < vetorR.length; beat++) {
            double[] gn = {2, -2};
            double[] hn = {1.0 / 8.0, 3.0 / 8.0, 3.0 / 8.0, 1.0 / 8.0};

            int BegSearch = (int) (Math.max(1, vetorR[beat] - offset1) + 1); // %
            // In�cio
            // da
            // janela
            // de
            // busca
            // (onset)
            int EndSearch = (int) (Math.min(vetorR[beat] + offset2,
                    sinal.length)); // % Fim da janela de busca (offset)

            // Janela de busca propriamente dita
            double Win[] = UtilArray.getSub(sinal, BegSearch, EndSearch);
            UtilArray.subtract(Win, UtilArray.mean(Win));

            // % Localiza��o do pico do QRS na janela Win
            int PicoR = (int) (vetorR[beat] - BegSearch);

            double[] Input = Win;

            double[] metrica1 = new double[Escalas.length];
            double[] metrica2 = new double[Escalas.length];
            double[] metrica3 = new double[Escalas.length];
            double[] metrica4 = new double[Escalas.length];
            double[] metrica5 = new double[Escalas.length];
            double[] metrica51 = new double[Escalas.length];
            double[] metrica52 = new double[Escalas.length];
            boolean[] metrica6 = new boolean[Escalas.length];
            boolean[] metrica7 = new boolean[Escalas.length];

            for (int idEscala = 0; idEscala < Escalas.length; idEscala++) {
                double[] Fwin = Util.convoluc(Input, gn);
                Input = Util.convoluc(Input, hn);
                double[] dFwin = Fwin;

                int On = 0;
                int Off = 0;

                int PtIni = PicoR;
                double ultref = PicoR;
                int idpcr1 = 0;
                ArrayList<Integer> vet_pcr = new ArrayList<Integer>();
                double pmax = 0;
                int On_tmp = 0;

                while ((PtIni > Math.max(PicoR - 0.080 * sfreq, 1) && vet_pcr
                        .size() < 3)
                        || (PtIni > Math.max(PicoR - 0.080 * sfreq, 1) && ultref
                        - PtIni <= 0.030 * sfreq)) {

                    if (Math.abs(dFwin[PtIni]) > Math.abs(dFwin[PtIni - 1])
                            && Math.abs(dFwin[PtIni]) > Math
                            .abs(dFwin[PtIni + 1])) {
                        vet_pcr.add(PtIni);
                        ultref = UtilArray.getLast(vet_pcr);

                        if (Math.abs(dFwin[PtIni]) > pmax) {
                            pmax = Math.abs(dFwin[PtIni]);
                        }
                    }

                    PtIni--;
                }

                for (int ii = 0; ii < vet_pcr.size(); ii++) {
                    if (Math.abs(dFwin[vet_pcr.get(ii)]) >= 0.10 * pmax) {
                        On_tmp = vet_pcr.get(ii);
                    }
                }

                idpcr1 = On_tmp;
                int ok = 0;

                if (On_tmp == 0) {
                    On = (int) (PicoR - Math.round(0.045 * sfreq));
                } else {
                    double limOn = 0;
                    if (dFwin[On_tmp] > 0) {
                        limOn = 0.05 * Math.abs(dFwin[On_tmp]);
                    } else {
                        limOn = 0.07 * Math.abs(dFwin[On_tmp]);
                    }

                    while (ok == 0 && idpcr1 > 0) {
                        if ((Math.abs(dFwin[idpcr1]) < Math.abs(dFwin[idpcr1 - 1]) && Math.abs(dFwin[idpcr1]) < Math.abs(dFwin[idpcr1 + 1]))
                                || Math.abs(dFwin[idpcr1]) <= limOn) {
                            ok = 1;
                        } else {
                            idpcr1--;
                        }
                    }

                    if (ok == 0) {
                        idpcr1 = (int) Math.max(
                                PicoR - Math.round(0.080 * sfreq), 1);
                    }

                    On = idpcr1;
                }

                vetor_on[idEscala] = On; // %% Armazenamento do resultado de
                // Onset

                PtIni = PicoR;
                ultref = PicoR;
                idpcr1 = 0;
                vet_pcr = new ArrayList<Integer>();
                pmax = 0;
                int Off_tmp = 0;
                while ((PtIni < Math.min(PicoR + 0.080 * sfreq, dFwin.length) && vet_pcr
                        .size() < 3)
                        || (PtIni < Math.min(PicoR + 0.080 * sfreq,
                                dFwin.length) && PtIni - ultref <= 0.030 * sfreq)) {

                    if (Math.abs(dFwin[PtIni]) > Math.abs(dFwin[PtIni - 1])
                            && Math.abs(dFwin[PtIni]) > Math
                            .abs(dFwin[PtIni + 1])) {
                        if (vet_pcr.size() == 0) {
                            vet_pcr.add(PtIni);
                            if (Math.abs(dFwin[PtIni]) > pmax) {
                                pmax = Math.abs(dFwin[PtIni]);
                            }
                        } else {
                            vet_pcr.add(PtIni);

                            if (Math.abs(dFwin[PtIni]) > pmax) {
                                pmax = Math.abs(dFwin[PtIni]);
                            }
                            ultref = UtilArray.getLast(vet_pcr);
                        }
                    }

                    PtIni++;
                }

                for (int ii = 0; ii < vet_pcr.size(); ii++) {
                    if (Math.abs(dFwin[vet_pcr.get(ii)]) >= 0.10 * pmax) {
                        Off_tmp = vet_pcr.get(ii);
                    }
                }

                idpcr1 = Off_tmp;
                ok = 0;

                if (Off_tmp == 0) {
                    Off = (int) (PicoR + Math.round(0.045 * sfreq));
                } else {
                    double limOff = 0;
                    limOff = 0.07 * Math.abs(dFwin[Off_tmp]);

                    while (ok == 0 && idpcr1 < dFwin.length - 1) {
                        if ((Math.abs(dFwin[idpcr1]) < Math
                                .abs(dFwin[idpcr1 - 1]) && Math
                                .abs(dFwin[idpcr1]) < Math
                                .abs(dFwin[idpcr1 + 1]))
                                || Math.abs(dFwin[idpcr1]) <= limOff) {
                            ok = 1;
                        } else {
                            idpcr1++;
                        }
                    }

                    if (ok == 0) {
                        idpcr1 = (int) Math
                                .min(PicoR + Math.round(0.080 * sfreq),
                                        dFwin.length);
                    }

                    Off = idpcr1;
                }

                vetor_off[idEscala] = Off;

                // %% C�lculo de m�tricas que subsidiar�o processo de sele��o da
                // %% escala de Wavelet e dos correspondentes resultados de
                // %% segmenta��o mais precisos
                // Dura��o do QRS segmentado para a presente escala
                metrica1[idEscala] = (Off - On) / sfreq;
                metrica2[idEscala] = Math.abs(Win[On] - Win[PicoR]);
                metrica3[idEscala] = Math.abs(Win[Off] - Win[PicoR]);
                metrica4[idEscala] = (PicoR - On) / sfreq;
                metrica5[idEscala] = (Off - PicoR) / sfreq;

                double[] sub = UtilArray.getSub(Win, On, PicoR + 1);
                UtilArray.multiply(sub, sub);
                metrica51[idEscala] = UtilArray.sum(sub);

                sub = UtilArray.getSub(Win, PicoR, Off + 1);
                UtilArray.multiply(sub, sub);

                metrica52[idEscala] = UtilArray.sum(sub);

                metrica6[idEscala] = On >= PicoR - 0.010 * sfreq
                        || On <= PicoR - 0.120 * sfreq
                        || metrica1[idEscala] > 0.200;

                metrica7[idEscala] = Off <= PicoR + 0.010 * sfreq
                        || Off >= PicoR + 0.120 * sfreq
                        || metrica1[idEscala] > 0.200;

                double[] novo_gn = new double[gn.length * 2];
                double[] novo_hn = new double[hn.length * 2];

                int pos = 0;
                for (int ii = 0; ii < gn.length; ii++) {
                    novo_gn[pos++] = gn[ii];
                    novo_gn[pos++] = 0;
                }

                pos = 0;
                for (int ii = 0; ii < hn.length; ii++) {
                    novo_hn[pos++] = hn[ii];
                    novo_hn[pos++] = 0;
                }

                gn = novo_gn;
                hn = novo_hn;
            }

            double[] Aval_3 = UtilArray.subDiv(
                    UtilArray.getSub(metrica2, 1, metrica2.length),
                    UtilArray.getSub(metrica2, 0, metrica2.length - 1));

            double[] Aval_4 = UtilArray.subDiv(
                    UtilArray.getSub(metrica3, 1, metrica3.length),
                    UtilArray.getSub(metrica3, 0, metrica3.length - 1));

            double[] Aval_7 = UtilArray.subDiv(
                    UtilArray.getSub(metrica51, 1, metrica51.length),
                    UtilArray.getSub(metrica51, 0, metrica51.length - 1));

            double[] Aval_8 = UtilArray.subDiv(
                    UtilArray.getSub(metrica52, 1, metrica52.length),
                    UtilArray.getSub(metrica52, 0, metrica52.length - 1));

            Aval_3 = UtilArray.abs(Aval_3);
            ArrayList<Integer> Aval_32 = UtilArray.findGreater(Aval_3, 0.1);
            ArrayList<Integer> Aval_33 = UtilArray.findGreater(Aval_7, 0.05);

            ArrayList<Integer> index = new ArrayList<Integer>();
            if (Aval_33.isEmpty() && Aval_32.isEmpty()) {
                index = UtilArray.findEqual(metrica4, UtilArray.min(metrica4));
                Onset[beat] = vetor_on[index.get(0)];

            } else {
                if (metrica4[1] < 0.110) {
                    index.clear();
                    index.add(1);
                } else {
                    index = UtilArray.findEqual(metrica4,
                            UtilArray.min(metrica4));
                }
                Onset[beat] = vetor_on[index.get(0)];
            }

            Aval_4 = UtilArray.abs(Aval_4);
            ArrayList<Integer> Aval_42 = UtilArray.findGreater(Aval_4, 0.1);
            ArrayList<Integer> Aval_43 = UtilArray.findGreater(Aval_8, 0.05);

            if (Aval_43.isEmpty() && Aval_42.isEmpty()) {
                index = UtilArray.findEqual(metrica5, UtilArray.min(metrica5));

                Offset[beat] = vetor_off[index.get(0)];
            } else {
                if (metrica5[1] < 0.110) {
                    index.clear();
                    index.add(1);
                } else {
                    index = UtilArray.findEqual(metrica5,
                            UtilArray.min(metrica5));
                }

                Offset[beat] = vetor_off[index.get(0)];
            }

            double subEbeats[] = UtilArray.getSub(Win, (int) Onset[beat], (int) Offset[beat] + 1);
            double[] powEbeats = UtilArray.pow(subEbeats, 2);
            Ebeats[beat] = UtilArray.sum(powEbeats);
            Onset[beat] = Onset[beat] + BegSearch;
            Offset[beat] = Offset[beat] + BegSearch;

        }
    }
}

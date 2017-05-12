package br.ufc.deti.ecgweb.utils.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import org.jtransforms.fft.DoubleFFT_1D;

public class WavHilbFiltDante3 {

	private double[][] MIntmm;
	private double[][] MPicosR;
	private double[] rr1;
	private double[] rr2;
	private double[] rr3;
	private double[] mOn;
	private double[] mOff;
	private double[][] mmOn;
	private double[][] mmOff;

	public double[][] getMIntmm() {
		return MIntmm;
	}

	public void setMIntmm(double[][] mIntmm) {
		MIntmm = mIntmm;
	}

	public double[][] getMPicosR() {
		return MPicosR;
	}

	public void setMPicosR(double[][] mPicosR) {
		MPicosR = mPicosR;
	}

	public double[] getRR1() {
		return rr1;
	}

	public void setRR1(double[] rR1) {
		rr1 = rR1;
	}

	public double[] getRR2() {
		return rr2;
	}

	public void setRR2(double[] rR2) {
		rr2 = rR2;
	}

	public double[] getRR3() {
		return rr3;
	}

	public void setRR3(double[] rR3) {
		rr3 = rR3;
	}

	public double[] getMOn() {
		return mOn;
	}

	public void setMOn(double[] mOn) {
		this.mOn = mOn;
	}

	public double[] getMOff() {
		return mOff;
	}

	public void setMOff(double[] mOff) {
		this.mOff = mOff;
	}

	public double[][] getMMOn() {
		return mmOn;
	}

	public void setMMOn(double[][] mMOn) {
		mmOn = mMOn;
	}

	public double[][] getMMOff() {
		return mmOff;
	}

	public void setMMOff(double[][] mMOff) {
		mmOff = mMOff;
	}

	private ArrayList<Integer> find(double picos_R1[], double Lgap1,
			double Lwin, double sfreq, double batimento_anterior) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < picos_R1.length; i++) {
			if (picos_R1[i] > Lgap1
					& picos_R1[i] <= Lwin + Lgap1 + Math.round(0.120 * sfreq)
					& picos_R1[i] > Math.round(0.120 * sfreq
							+ batimento_anterior)) {
				ids.add(i);
			}
		}
		return ids;
	}

	public void wavHilbFilt_Dante3(double[][] matin, double amostraIni,
			double sfreq, double lwin, double lgap1, double lgap2,
			double batimentoAnterior, double batimentoAnterior2,
			double batimentoAnterior3, double ultBloco, double idIter) {

		int nder = matin.length;
		
		ArrayList<Double[]> mout = new ArrayList<Double[]>();
		ArrayList<Double[]> minmm = new ArrayList<Double[]>();

		double[] mediasOn =  null ;
		double[] mediasOff = null;

		double nBatmin = 0;
		double nBatmax = 0;
		
		for (int ider = 0; ider < nder; ider++) {
			double[] exame = matin[ider];
			double[] sinalf = FiltroECGWanderingWavelet3(exame, sfreq);
			exame = sinalf;
			
			int t0 = exame.length;
			double[] int0 = exame;

			int[] setEscala;
			if (sfreq <= 500) {
				setEscala = new int[2];
				setEscala[0] = 2;
				setEscala[1] = 4;
			} else {
				setEscala = new int[2];
				setEscala[0] = 4;
				setEscala[1] = 8;
				setEscala[2] = 16;
				setEscala[3] = 32;
			}

			double[] desvioPontosFid = new double[setEscala.length];
			double[] desvioPicosR1 = new double[setEscala.length];
			double[] vetFPLimiar = new double[setEscala.length];

			nBatmin = (0.7 * t0 / sfreq);
			nBatmax = (3.5 * t0 / sfreq);

			for (int valorEscala = 0; valorEscala < setEscala.length; valorEscala++) {
				double[] waveletFilha = chapeuMexicano(setEscala[valorEscala]);
				double[] fInt0 = filtFiltJP(int0, waveletFilha);

				double[] dFInt0 = UtilArray.subtract(
						UtilArray.getSub(fInt0, 1, fInt0.length),
						UtilArray.getSub(fInt0, 0, fInt0.length - 1));

				double[] zdFInt0 = transfHilbert2(dFInt0);

				double[] envInt0 = UtilArray.fftAbs(zdFInt0);

				DetPrBat detprBat = new DetPrBat();
				detprBat.process(envInt0, int0, (double) ider,nBatmin,nBatmax,(int)sfreq);

				double[] picosR1 = detprBat.getVetorPFid();

				
				double[] ibb = UtilArray.subtract(
						UtilArray.getSub(picosR1, 1, picosR1.length),
						UtilArray.getSub(picosR1, 0, picosR1.length - 1));

				double desvioPfid = detprBat.getDesvio_ampl_pfid();

				desvioPicosR1[valorEscala] = UtilArray.std(ibb);
				desvioPontosFid[valorEscala] = UtilArray.std(ibb)
						* desvioPfid;
				vetFPLimiar[valorEscala] = detprBat.getFplim();
			}

			double valorRef = desvioPontosFid[0];
			int escalaSel = 0;
			for (int valorEscala = 0; valorEscala < setEscala.length; valorEscala++) {
				if (desvioPontosFid[valorEscala] < valorRef) {
					valorRef = desvioPontosFid[valorEscala];
					escalaSel = valorEscala;
				}
			}

			escalaSel = setEscala[escalaSel];

			double[] waveletFilha = chapeuMexicano(escalaSel);

			double[] fInt0 = filtFiltJP(int0, waveletFilha);

			double[] dFInt0 = UtilArray.subtract(
					UtilArray.getSub(fInt0, 1, fInt0.length),
					UtilArray.getSub(fInt0, 0, fInt0.length - 1));

			double[] zdFInt0 = transfHilbert2(dFInt0);

			double[] envInt0 = UtilArray.fftAbs(zdFInt0);

			mout.add(Util.convert(envInt0));
			minmm.add(Util.convert(exame));
		}

		DetPrBat detprBat = new DetPrBat();
		detprBat.process(Util.convert(mout.get(0)), Util.convert(minmm.get(0)),
				0,nBatmin,nBatmax,(int)sfreq);

		double[] picosR1 = detprBat.getVetorPFid();
		ArrayList<Integer> picos_uteis = find(picosR1, lgap1, lwin, sfreq,
				batimentoAnterior);
		double[] picosR1u = UtilArray.getElements(picosR1, picos_uteis);

		detprBat.process(Util.convert(mout.get(1)), Util.convert(minmm.get(1)),
				0,nBatmin,nBatmax,(int)sfreq);
		double[] picos_R2 = detprBat.getVetorPFid();
		picos_uteis = find(picos_R2, lgap1, lwin, sfreq, batimentoAnterior);
		double[] picosR2u = UtilArray.getElements(picos_R2, picos_uteis);

		detprBat.process(Util.convert(mout.get(2)), Util.convert(minmm.get(2)),
				0,nBatmin,nBatmax,(int)sfreq);
		double[] picosR3 = detprBat.getVetorPFid();
		picos_uteis = find(picosR3, lgap1, lwin, sfreq, batimentoAnterior);
		double[] picosR3u = UtilArray.getElements(picosR3, picos_uteis);

		if (picosR2u.length > picosR1u.length
				|| picosR2u.length > picosR3u.length) {
			if (picosR2u.length > picosR1u.length) {
				if (Math.abs((picosR2u[picosR2u.length - 1] - picosR1u[picosR1u.length - 1])
						/ sfreq) > 0.3) {
					ArrayList<Integer> futR1 = UtilArray.findGreater(picosR1,
							picosR1u[picosR1u.length - 1]);
					if (futR1.size() > 0) {
						if (((picosR1[futR1.get(0)] - picosR2u[picosR2u.length - 1]) / sfreq) < 0.090) {
							picosR1u = UtilArray.concat(picosR1u,
									picosR2u[picosR2u.length - 1]);
						}
					}
				}
			}

			if (picosR2u.length > picosR3u.length) {
				if (Math.abs((picosR2u[picosR2u.length - 1] - picosR3u[picosR3u.length - 1])
						/ sfreq) > 0.3) {
					ArrayList<Integer> futR3 = UtilArray.findGreater(picosR3,
							picosR3u[picosR3u.length - 1]);
					if (futR3.size() > 0) {
						if (((picosR3[futR3.get(0)] - picosR2u[picosR2u.length - 1]) / sfreq) < 0.090) {
							picosR3u = UtilArray.concat(picosR3u,
									picosR2u[picosR2u.length - 1]);
						}
					}
				}
			}
		}

		if (picosR3u.length > picosR1u.length
				|| picosR3u.length > picosR2u.length) {

			if (picosR3u.length > picosR1u.length) {
				if (Math.abs((picosR3u[picosR3u.length - 1] - picosR1u[picosR1u.length - 1])
						/ sfreq) > 0.3) {
					ArrayList<Integer> futR1 = UtilArray.findGreater(picosR1,
							picosR1u[picosR1u.length - 1]);
					if (futR1.size() > 0) {
						if (((picosR1[futR1.get(0)] - picosR3u[picosR3u.length - 1]) / sfreq) < 0.090) {
							picosR1u = UtilArray.concat(picosR1u,
									picosR3u[picosR3u.length - 1]);
						}
					}
				}
			}

			if (picosR3u.length > picosR2u.length) {
				if (Math.abs((picosR2u[picosR2u.length - 1] - picosR3u[picosR3u.length - 1])
						/ sfreq) > 0.3) {
					ArrayList<Integer> futR2 = UtilArray.findGreater(picos_R2,
							picosR2u[picosR2u.length - 1]);
					if (futR2.size() > 0) {
						if (((picos_R2[futR2.get(0)] - picosR3u[picosR3u.length - 1]) / sfreq) < 0.090) {
							picosR2u = UtilArray.concat(picosR2u,
									picosR3u[picosR3u.length - 1]);
						}
					}
				}
			}

		}

		double[] ibb1 = UtilArray.subtract(
				UtilArray.getSub(picosR1u, 1, picosR1u.length),
				UtilArray.getSub(picosR1u, 0, picosR1u.length - 1));

		double[] sub = UtilArray.getElements(Util.convert(minmm.get(0)),
				picosR1u);
		double Maxs1 = UtilArray.max(UtilArray.abs(sub));

		UtilArray.divide(sub, Maxs1);
		double dpRR1 = UtilArray.std(UtilArray.abs(sub)) * UtilArray.std(ibb1);

		double[] ibb2 = UtilArray.subtract(
				UtilArray.getSub(picosR2u, 1, picosR2u.length),
				UtilArray.getSub(picosR2u, 0, picosR2u.length - 1));

		double[] sub2 = UtilArray.getElements(Util.convert(minmm.get(1)),
				picosR2u);
		double Maxs2 = UtilArray.max(UtilArray.abs(sub2));

		UtilArray.divide(sub2, Maxs2);
		double dpRR2 = UtilArray.std(UtilArray.abs(sub2)) * UtilArray.std(ibb2);

		double[] ibb3 = UtilArray.subtract(
				UtilArray.getSub(picosR3u, 1, picosR3u.length),
				UtilArray.getSub(picosR3u, 0, picosR3u.length - 1));

		double[] sub3 = UtilArray.getElements(Util.convert(minmm.get(2)),
				picosR3u);
		double Maxs3 = UtilArray.max(UtilArray.abs(sub3));

		UtilArray.divide(sub3, Maxs3);
		double dpRR3 = UtilArray.std(UtilArray.abs(sub3)) * UtilArray.std(ibb3);

		boolean vbiguais = true;
		if (picosR1u.length == picosR2u.length
				&& picosR2u.length == picosR3u.length) {
			for (int iR = 0; iR < picosR1u.length; iR++) {
				if (Math.abs(picosR1u[iR] - picosR2u[iR]) > 0.090 * sfreq
						|| Math.abs(picosR1u[iR] - picosR3u[iR]) > 0.090 * sfreq
						|| Math.abs(picosR3u[iR] - picosR2u[iR]) > 0.090 * sfreq) {
					vbiguais = false;
				}
			}
		}

		int vencCH1 = 0;
		int vencCH2 = 0;
		int vencCH3 = 0;

		if (picosR1u.length != picosR2u.length
				|| picosR1u.length != picosR3u.length
				|| picosR3u.length != picosR2u.length || vbiguais == false) {
			if ((dpRR1 < dpRR2 && dpRR1 <= dpRR3)
					|| (dpRR1 <= dpRR2 && dpRR1 < dpRR3)) {
				vencCH2 = 1;
				vencCH3 = 1;

				picosR2u = picosR1u.clone();
				picosR3u = picosR1u.clone();
				//System.out.println("Canal 1 vencedor!");
			} else if ((dpRR2 < dpRR1 && dpRR2 <= dpRR3)
					|| (dpRR2 <= dpRR1 && dpRR2 < dpRR3)) {
				vencCH1 = 2;
				vencCH3 = 2;

				picosR1u = picosR2u.clone();
				picosR3u = picosR2u.clone();
				//System.out.println("Canal 2 vencedor!");
			} else if ((dpRR3 < dpRR1 & dpRR3 <= dpRR2)
					| (dpRR3 <= dpRR1 & dpRR3 < dpRR2)) {
				vencCH1 = 3;
				vencCH2 = 3;

				picosR1u = picosR3u.clone();
				picosR2u = picosR3u.clone();
				//System.out.println("Canal 3 vencedor!");
			}
		}
		double[][] picosR = new double[3][];
		picosR[0] = picosR1u;
		picosR[1] = picosR2u;
		picosR[2] = picosR3u;

		double[][] matrizOn = new double[nder][];
		double[][] matrizOff = new double[nder][];
		double[][] pesosOnOff = new double[nder][];

		for (int ider = 0; ider < nder; ider++) {
			double[] exame = Util.convert(minmm.get(ider));
			double[] picos_R = picosR[ider];

			SegmentaWaveletTrousQRS seg = new SegmentaWaveletTrousQRS();
			seg.process(exame, picos_R, sfreq);

			matrizOn[ider] = seg.getOnset();
			matrizOff[ider] = seg.getOffset();
			pesosOnOff[ider] = seg.getEbeats();
		}

		double toldesv = 2 / sfreq;
		
		mediasOn  = new double[picosR[0].length];
		mediasOff  = new double[picosR[0].length];
		for (int ii = 0; ii < picosR[0].length ; ii++) {
			if (matrizOn[0][ii] >= picosR[1][ii]
					|| matrizOn[0][ii] >= picosR[2][ii]) {

				if (matrizOn[0][ii] >= picosR[1][ii]) {

					if ((picosR[0][ii] - matrizOn[0][ii]) / sfreq <= (picosR[1][ii] - matrizOn[1][ii])
							/ sfreq
							&& (picosR[0][ii] - matrizOn[0][ii]) / sfreq > 0.010) {
						picosR[1][ii] = picosR[0][ii];
						matrizOn[1][ii] = matrizOn[0][ii];
					} else {
						picosR[0][ii] = picosR[1][ii];
						matrizOn[0][ii] = matrizOn[1][ii];
					}
				}

				if (matrizOn[0][ii] >= picosR[2][ii]) {
	
					if ((picosR[0][ii] - matrizOn[0][ii]) / sfreq <= (picosR[2][ii] - matrizOn[2][ii])
							/ sfreq
							&& (picosR[0][ii] - matrizOn[0][ii]) / sfreq > 0.010) {

						picosR[2][ii] = picosR[0][ii];
						matrizOn[2][ii] = matrizOn[0][ii];
					} else {

						picosR[0][ii] = picosR[2][ii];
						matrizOn[0][ii] = matrizOn[2][ii];
					}
				}
			}

			if (matrizOn[1][ii] >= picosR[0][ii]
					|| matrizOn[1][ii] >= picosR[2][ii]) {

				if (matrizOn[1][ii] >= picosR[0][ii]) {

					if ((picosR[1][ii] - matrizOn[1][ii]) / sfreq <= (picosR[0][ii] - matrizOn[0][ii])
							/ sfreq
							&& (picosR[1][ii] - matrizOn[1][ii]) / sfreq > 0.010) {

						picosR[0][ii] = picosR[1][ii];
						matrizOn[0][ii] = matrizOn[1][ii];
					} else {

						picosR[1][ii] = picosR[0][ii];
						matrizOn[1][ii] = matrizOn[0][ii];
					}

				}


				if (matrizOn[1][ii] >= picosR[2][ii]) {

					if ((picosR[1][ii] - matrizOn[1][ii]) / sfreq <= (picosR[2][ii] - matrizOn[2][ii])
							/ sfreq
							&& (picosR[1][ii] - matrizOn[1][ii]) / sfreq > 0.010) {

						picosR[2][ii] = picosR[1][ii];
						matrizOn[2][ii] = matrizOn[1][ii];
					} else {

						picosR[1][ii] = picosR[2][ii];
						matrizOn[1][ii] = matrizOn[2][ii];
					}
				}

			}


			if (matrizOn[2][ii] >= picosR[0][ii]
					|| matrizOn[2][ii] >= picosR[1][ii]) {

				if (matrizOn[2][ii] >= picosR[0][ii]) {
	
					if ((picosR[2][ii] - matrizOn[2][ii]) / sfreq <= (picosR[0][ii] - matrizOn[0][ii])
							/ sfreq
							&& (picosR[2][ii] - matrizOn[2][ii]) / sfreq > 0.010) {

						picosR[0][ii] = picosR[2][ii];
						matrizOn[0][ii] = matrizOn[2][ii];
					} else {

						picosR[2][ii] = picosR[0][ii];
						matrizOn[2][ii] = matrizOn[0][ii];
					}
				}
				
				if (matrizOn[2][ii] >= picosR[1][ii]) {

					if ((picosR[2][ii] - matrizOn[2][ii]) / sfreq <= (picosR[1][ii] - matrizOn[1][ii])
							/ sfreq
							&& (picosR[2][ii] - matrizOn[2][ii]) / sfreq > 0.010) {

						picosR[1][ii] = picosR[2][ii];
						matrizOn[1][ii] = matrizOn[2][ii];
					} else {

						picosR[2][ii] = picosR[1][ii];
						matrizOn[2][ii] = matrizOn[1][ii];
					}

				}
			}


			if (matrizOff[0][ii] <= picosR[1][ii]
					|| matrizOff[0][ii] <= picosR[2][ii]) {

				if (matrizOff[0][ii] <= picosR[1][ii]) {

					if ((matrizOff[0][ii] - picosR[0][ii]) / sfreq <= (matrizOff[1][ii] - picosR[1][ii])
							/ sfreq
							& (matrizOff[0][ii] - picosR[0][ii]) / sfreq > 0.010) {

						picosR[1][ii] = picosR[0][ii];
						matrizOff[1][ii] = matrizOff[0][ii];
					} else {

						picosR[0][ii] = picosR[1][ii];
						matrizOff[0][ii] = matrizOff[1][ii];
					}

				}

				if (matrizOff[0][ii] <= picosR[2][ii]) {

					if ((matrizOff[0][ii] - picosR[0][ii]) / sfreq <= (matrizOff[2][ii] - picosR[2][ii])
							/ sfreq
							& (matrizOff[0][ii] - picosR[0][ii]) / sfreq > 0.010) {

						picosR[2][ii] = picosR[0][ii];
						matrizOff[2][ii] = matrizOff[0][ii];
					} else {

						picosR[0][ii] = picosR[2][ii];
						matrizOff[0][ii] = matrizOff[2][ii];
					}

				}
			}


			if (matrizOff[1][ii] <= picosR[0][ii]
					|| matrizOff[1][ii] <= picosR[2][ii]) {

				if (matrizOff[1][ii] <= picosR[0][ii]) {

					if ((matrizOff[1][ii] - picosR[1][ii]) / sfreq <= (matrizOff[0][ii] - picosR[0][ii])
							/ sfreq
							& (matrizOff[1][ii] - picosR[1][ii]) / sfreq > 0.010) {

						picosR[0][ii] = picosR[1][ii];
						matrizOff[0][ii] = matrizOff[1][ii];
					} else {

						picosR[1][ii] = picosR[0][ii];
						matrizOff[1][ii] = matrizOff[0][ii];
					}

				}

				if (matrizOff[1][ii] <= picosR[2][ii]) {

					if ((matrizOff[1][ii] - picosR[1][ii]) / sfreq <= (matrizOff[2][ii] - picosR[2][ii])
							/ sfreq
							& (matrizOff[1][ii] - picosR[1][ii]) / sfreq > 0.010) {

						picosR[2][ii] = picosR[1][ii];
						matrizOff[2][ii] = matrizOff[1][ii];
					} else {

						picosR[1][ii] = picosR[2][ii];
						matrizOff[1][ii] = matrizOff[2][ii];
					}

				}
			}


			if (matrizOff[2][ii] <= picosR[0][ii]
					|| matrizOff[2][ii] <= picosR[1][ii]) {

				if (matrizOff[2][ii] <= picosR[0][ii]) {

					if ((matrizOff[2][ii] - picosR[2][ii]) / sfreq <= (matrizOff[0][ii] - picosR[0][ii])
							/ sfreq
							& (matrizOff[2][ii] - picosR[2][ii]) / sfreq > 0.010) {

						picosR[0][ii] = picosR[2][ii];
						matrizOff[0][ii] = matrizOff[2][ii];
					} else {

						picosR[2][ii] = picosR[0][ii];
						matrizOff[2][ii] = matrizOff[0][ii];
					}

				}

				if (matrizOff[2][ii] <= picosR[1][ii]) {

					if ((matrizOff[2][ii] - picosR[2][ii]) / sfreq <= (matrizOff[1][ii] - picosR[1][ii])
							/ sfreq
							& (matrizOff[2][ii] - picosR[2][ii]) / sfreq > 0.010) {

						picosR[1][ii] = picosR[2][ii];
						matrizOff[1][ii] = matrizOff[2][ii];
					} else {

						picosR[2][ii] = picosR[1][ii];
						matrizOff[2][ii] = matrizOff[1][ii];
					}
				}
			}

			double Aval_On = UtilArray.meanRow(matrizOn, ii);
			double Aval_Off = UtilArray.meanRow(matrizOff, ii);
			
			if ((Math.abs(matrizOn[0][ii] - Aval_On) / sfreq <= toldesv
					&& Math.abs(matrizOn[1][ii] - Aval_On) / sfreq <= toldesv && Math
					.abs(matrizOn[2][ii] - Aval_On) / sfreq <= toldesv)) {
				mediasOn[ii]=(UtilArray.minRow(matrizOn, ii));
			} else {
				if (Math.abs(UtilArray.minRow(matrizOn, ii)
						- UtilArray.maxRow(matrizOff, ii))
						/ sfreq < 0.120) {
					mediasOn[ii]=(UtilArray.minRow(matrizOn, ii));
				} else if (UtilArray.minRow(
						UtilArray.subtractRows(matrizOff, matrizOn, ii), 0)
						/ sfreq < 0.120) {
					double refdRon = 1 * sfreq;
					double refpqrs = 0;

					if (vencCH1 == 0 && vencCH2 == 0 && vencCH3 == 0) {
						for (int cc = 0; cc < nder; cc++) {
							if (pesosOnOff[cc][ii] > refpqrs
									& (UtilArray.maxRow(matrizOff, ii) - matrizOn[cc][ii])
											/ sfreq >= 0.080
									& (UtilArray.maxRow(matrizOff, ii) - matrizOn[cc][ii])
											/ sfreq < 0.120) {
								mediasOn[ii]=(matrizOn[cc][ii]);
								refpqrs = pesosOnOff[cc][ii];
							}
						}

						if (refpqrs == 0) {
							ArrayList<Integer> loc = UtilArray.findEqualRow(
									pesosOnOff,
									UtilArray.maxRow(pesosOnOff, ii), ii);
							mediasOn[ii]=(matrizOn[loc.get(0)][ii]);
						}

					} else {
						for (int cc = 0; cc < nder; cc++) {
							if ((matrizOff[cc][ii] - matrizOn[cc][ii])
									/ sfreq < 0.120
									&& (matrizOff[cc][ii] - matrizOn[cc][ii])
											/ sfreq >= 0.080
									&& (picosR[cc][ii] - matrizOn[cc][ii])
											/ sfreq < refdRon) {
								mediasOn[ii]=(matrizOn[cc][ii]);
								refdRon = (picosR[cc][ii] - matrizOn[cc][ii])
										/ sfreq;
							}
						}
						if (refdRon == 1 * sfreq) {

							if (vencCH2 == 1 && vencCH3 == 1) {
								mediasOn[ii]=(matrizOn[0][ii]);
							}

							if (vencCH1 == 2 && vencCH3 == 2) {
								mediasOn[ii]=(matrizOn[1][ii]);
							}

							if (vencCH1 == 3 && vencCH2 == 3) {
								mediasOn[ii]=(matrizOn[2][ii]);
							}

						}
					}
				} else {
					if (vencCH1 == 0 & vencCH2 == 0 && vencCH3 == 0) {
						mediasOn[ii]=(UtilArray.minRow(matrizOn, ii));
					} else {
						if (vencCH2 == 1 && vencCH3 == 1) {
							mediasOn[ii]=(matrizOn[0][ii]);
						}

						if (vencCH1 == 2 && vencCH3 == 2) {
							mediasOn[ii]=(matrizOn[1][ii]);
						}

						if (vencCH1 == 3 && vencCH2 == 3) {
							mediasOn[ii]=(matrizOn[2][ii]);
						}
					}
				}
			}

			if ((Math.abs(matrizOff[0][ii] - Aval_Off) / sfreq <= toldesv
					&& Math.abs(matrizOff[1][ii] - Aval_Off) / sfreq <= toldesv && Math
					.abs(matrizOff[2][ii] - Aval_Off) / sfreq <= toldesv)
					|| UtilArray.maxRow(
							UtilArray.subtractRows(matrizOff,
									mediasOn[ii], ii), 0)
							/ sfreq < 0.120) {
				mediasOff[ii]=(UtilArray.maxRow(matrizOff, ii));
			} else {
				if ((UtilArray.minRow(UtilArray.subtractRows(matrizOff,
						mediasOn[ii], ii), 0))
						/ sfreq < 0.120) {
					double refpqrs = 0;
					double refdRoff = 1 * sfreq;

					if (vencCH1 == 0 && vencCH2 == 0 && vencCH3 == 0) {
						for (int cc = 0; cc < nder; cc++) {
							if (pesosOnOff[cc][ii] > refpqrs
									& (matrizOff[cc][ii] - mediasOn[ii])
											/ sfreq >= 0.080
									& (matrizOff[cc][ii] - mediasOn[ii])
											/ sfreq < 0.120) {
								mediasOff[ii]=(matrizOff[cc][ii]);
								refpqrs = pesosOnOff[cc][ii];
							}
						}

						if (refpqrs == 0) {
							ArrayList<Integer> loc = UtilArray.findEqualRow(
									pesosOnOff,
									UtilArray.maxRow(pesosOnOff, ii), ii);
							mediasOff[ii]=(matrizOff[loc.get(0)][ii]);

						}
					} else {
						for (int cc = 0; cc < nder; cc++) {
							if ((matrizOff[cc][ii] - mediasOn[ii])
									/ sfreq < 0.120
									& (matrizOff[cc][ii] - mediasOn[ii])
											/ sfreq >= 0.080
									& (matrizOff[cc][ii] - picosR[cc][ii])
											/ sfreq < refdRoff) {
								mediasOff[ii]=(matrizOff[cc][ii]);
								refdRoff = (matrizOff[cc][ii] - picosR[cc][ii])
										/ sfreq;
							}
						}

						if (refdRoff == 1 * sfreq) {
							if (vencCH2 == 1 && vencCH3 == 1) {
								mediasOff[ii]=(matrizOff[0][ii]);
							}

							if (vencCH1 == 2 && vencCH3 == 2) {
								mediasOff[ii]=(matrizOff[1][ii]);
							}

							if (vencCH1 == 3 && vencCH2 == 3) {
								mediasOff[ii]=(matrizOff[2][ii]);
							}
						}
					}
				} else {
					if (vencCH1 == 0 && vencCH2 == 0 && vencCH3 == 0) {
						mediasOff[ii]=(UtilArray.minRow(matrizOff, ii));
					} else {
						if (vencCH2 == 1 && vencCH3 == 1) {
							mediasOff[ii]=(matrizOff[0][ii]);
						}

						if (vencCH1 == 2 && vencCH3 == 2) {
							mediasOff[ii]=(matrizOff[1][ii]);
						}

						if (vencCH1 == 3 && vencCH2 == 3) {
							mediasOff[ii]=(matrizOff[2][ii]);
						}
					}

				}
			}

		}// for

		// FIM DOS ALGORITMOS DE DETECÇÃO E SEGMENTAÇÃO DAS ONDAS P E T.
		picosR1u = picosR[0];
		picosR2u = picosR[1];
		picosR3u = picosR[2];

		this.mmOn = UtilArray.subtract(matrizOn, lgap1);
		this.mOn = UtilArray.sum(mediasOn, -lgap1);

		this.mmOff = UtilArray.subtract(matrizOff, lgap1);
		this.mOff= UtilArray.sum(mediasOff, -lgap1);

		if (idIter > 0) {
			double[] sub1 = UtilArray.subtract(
					UtilArray.getSub(picosR1u, 1, picosR1u.length - 1),
					UtilArray.getSub(picosR1u, 0, picosR1u.length - 2));

			rr1 = new double[picosR1u.length];
			rr1[0] = picosR1u[0] - batimentoAnterior;
			for (int i = 0; i < sub1.length; i++) {
				rr1[i + 1] = sub1[i];
			}
		} else {

			rr1 = UtilArray.subtract(
					UtilArray.getSub(picosR1u, 1, picosR1u.length ),
					UtilArray.getSub(picosR1u, 0, picosR1u.length - 1));
		}

		if (idIter > 0) {
			double[] sub1 = UtilArray.subtract(
					UtilArray.getSub(picosR1u, 1, picosR1u.length ),
					UtilArray.getSub(picosR1u, 0, picosR1u.length - 1));

			rr2 = new double[picosR2u.length];
			rr2[0] = picosR2u[0] - batimentoAnterior2;
			for (int i = 0; i < sub1.length; i++) {
				rr2[i + 1] = sub1[i];
			}
		} else {

			rr2 = UtilArray.subtract(
					UtilArray.getSub(picosR2u, 1, picosR2u.length ),
					UtilArray.getSub(picosR2u, 0, picosR2u.length - 1));

		}

		if (idIter > 0) {
			double[] sub1 = UtilArray.subtract(
					UtilArray.getSub(picosR3u, 1, picosR3u.length ),
					UtilArray.getSub(picosR3u, 0, picosR3u.length - 1));

			rr3 = new double[picosR3u.length];
			rr3[0] = picosR3u[0] - batimentoAnterior3;
			for (int i = 0; i < sub1.length; i++) {
				rr3[i + 1] = sub1[i];
			}
		} else {
			rr3 = UtilArray.subtract(
					UtilArray.getSub(picosR3u, 1, picosR3u.length ),
					UtilArray.getSub(picosR3u, 0, picosR3u.length - 1));
		}

		UtilArray.subtract(picosR1u, lgap1);
		UtilArray.subtract(picosR2u, lgap1);
		UtilArray.subtract(picosR3u, lgap1);

		this.MPicosR = new double[3][];
		this.MPicosR[0] = picosR1u;
		this.MPicosR[1] = picosR2u;
		this.MPicosR[2] = picosR3u;

		if (ultBloco == 0) {
			this.MIntmm = UtilArray.getSub(minmm, (int) lgap1, (int) (lwin
					+ lgap1 ));
		} else {
			this.MIntmm = UtilArray.getSub(minmm, (int) lgap1,
					(int) (minmm.get(0).length));
		}

	}

	public double[] filtFiltJP(double[] fn1, double[] fn2) {
		int lengthFn2 = 2 * fn2.length;
		double[] fn1Ones = new double[lengthFn2 * 2 + fn1.length];

		int pos = 0;
		for (int i = 0; i < lengthFn2; i++) {
			fn1Ones[pos] = fn1[0];
			pos++;
		}

		for (int i = 0; i < fn1.length; i++) {
			fn1Ones[pos] = fn1[i];
			pos++;
		}

		for (int i = 0; i < lengthFn2; i++) {
			fn1Ones[pos] = fn1[fn1.length - 1];
			pos++;
		}

		double[] res = Util.convoluc(fn1Ones, fn2);

		ArrayList<Double> y = Util.doubleToArrayList(res);
		y = Util.reverse(y);

		double[] res1 = Util.convoluc(Util.ArrayListToDouble(y), fn2);

		y = Util.reverse(Util.doubleToArrayList(res1));

		ArrayList<Double> resp = new ArrayList<Double>();
		for (int i = lengthFn2; i < y.size() - lengthFn2; i++) {
			resp.add(y.get(i));
		}

		return Util.ArrayListToDouble(resp);
	}

	public double[] chapeuMexicano(int escala) {
		double passo = 1.0 / escala;

		int tamanhoX = 0;
		for (double i = -5; i <= 5; i += passo) {
			tamanhoX++;
		}
		double[] sinal = new double[tamanhoX];
		double x = 0;

		int pos = 0;
		for (double i = -5; i <= 5; i += passo) {
			x = i;
			sinal[pos] = 2.1741 * (1.0 / Math.sqrt(2 * Math.PI)
					* (1.0 - Math.pow(x, 2)) * Math.exp(-Math.pow(x, 2) / (2)));
			pos++;
		}
		return sinal;
	}

	public double[] transfHilbert2(double[] fn) {
		DoubleFFT_1D fft = new DoubleFFT_1D(fn.length);
		double[] fftFn = new double[fn.length * 2];
		System.arraycopy(fn, 0, fftFn, 0, fn.length);

		// FFT format : fft[0] = re, fft[1] = im , fft[2] = re, fft[3] = im, ...
		fft.realForwardFull(fftFn);

		int n = fn.length * 2;
		double[] h = new double[n];
		Arrays.fill(h, 0);

		if ((fn.length % 2) == 0) {
			h[0] = 1;
			h[1] = 1;

			h[n / 2] = 1;
			h[n / 2 + 1] = 1;

			for (int i = 1; i < n / 2; i++) {
				h[i] = 2;
			}
		} else {
			h[0] = 1;
			h[1] = 1;

			for (int i = 1; i < (n + 1) / 2; i++) {
				h[i] = 2;
			}
		}

		UtilArray.multiply(fftFn, h);

		fft.complexInverse(fftFn, true);
		return fftFn;
	}

	private void decomposicao(ArrayList<Double> A, ArrayList<Double> D,
			double[] fn1, double[] fn2A, double[] fn2D, double Lfs) {
		double[] A1 = filtFiltJP(fn1, fn2A);
		UtilArray.multiply(A1, 0.5);

		double[] D1 = filtFiltJP(fn1, fn2D);
		UtilArray.multiply(D1, 0.5);

		for (int i = 0; i < Lfs; i++) {
			A.add(A1[0]);
			D.add(D1[0]);
		}

		for (int i = 0; i < A1.length; i++) {
			A.add(A1[i]);
		}

		for (int i = 0; i < D1.length; i++) {
			D.add(D1[i]);
		}

		for (int i = 0; i < Lfs; i++) {
			A.add(A1[A1.length - 1]);
			D.add(D1[D1.length - 1]);
		}
	}

	private double[] subamostragem(ArrayList<Double> amostra) {
		ArrayList<Double> sub = new ArrayList<Double>();

		for (int i = 0; i < amostra.size(); i++) {
			if (i % 2 == 0) {
				sub.add(amostra.get(i));
			}
		}

		return Util.ArrayListToDouble(sub);
	}

	private double[] reconstrucao(double[] sub, double[] Lo_R, int Lfs, int size) {
		int length = sub.length * 2;

		if (length > size) {
			length--;
		}

		double[] recons = new double[length];

		int pos = 0;
		for (int i = 0; i < sub.length; i++) {
			recons[pos] = sub[i];
			pos++;

			if (pos < length) {
				recons[pos] = 0;
				pos++;
			}
		}

		double[] r1 = filtFiltJP(recons, Lo_R);

		return UtilArray.getSub(r1, Lfs, r1.length - Lfs);
	}

	public double[] FiltroECGWanderingWavelet3(double[] sinal, double sfreq) {

		// [Lo_D,Hi_D,Lo_R,Hi_R]=wfilters('db10');

		// valores retidados do matlab
		double[] Lo_D = { -1.326420e-05, 9.358867e-05, -1.164669e-04,
				-6.858567e-04, 1.992405e-03, 1.395352e-03, -1.073318e-02,
				3.606554e-03, 3.321267e-02, -2.945754e-02, -7.139415e-02,
				9.305736e-02, 1.273693e-01, -1.959463e-01, -2.498464e-01,
				2.811723e-01, 6.884590e-01, 5.272012e-01, 1.881768e-01,
				2.667006e-02 };
		double[] Hi_D = { -2.667006e-02, 1.881768e-01, -5.272012e-01,
				6.884590e-01, -2.811723e-01, -2.498464e-01, 1.959463e-01,
				1.273693e-01, -9.305736e-02, -7.139415e-02, 2.945754e-02,
				3.321267e-02, -3.606554e-03, -1.073318e-02, -1.395352e-03,
				1.992405e-03, 6.858567e-04, -1.164669e-04, -9.358867e-05,
				-1.326420e-05 };
		double[] Lo_R = { -1.326420e-05, 9.358867e-05, -1.164669e-04,
				-6.858567e-04, 1.992405e-03, 1.395352e-03, -1.073318e-02,
				3.606554e-03, 3.321267e-02, -2.945754e-02, -7.139415e-02,
				9.305736e-02, 1.273693e-01, -1.959463e-01, -2.498464e-01,
				2.811723e-01, 6.884590e-01, 5.272012e-01, 1.881768e-01,
				2.667006e-02 };

		double[] trecho = sinal;

		int Lfs = Lo_D.length;

		// decomposição 1
		ArrayList<Double> A1 = new ArrayList<Double>();
		ArrayList<Double> D1 = new ArrayList<Double>();
		decomposicao(A1, D1, trecho, Lo_D, Hi_D, Lfs);

		double[] sA1 = subamostragem(A1);

		// decomposição 2
		ArrayList<Double> A2 = new ArrayList<Double>();
		ArrayList<Double> D2 = new ArrayList<Double>();
		decomposicao(A2, D2, sA1, Lo_D, Hi_D, Lfs);

		double[] sA2 = subamostragem(A2);

		// decomposição 3
		ArrayList<Double> A3 = new ArrayList<Double>();
		ArrayList<Double> D3 = new ArrayList<Double>();
		decomposicao(A3, D3, sA2, Lo_D, Hi_D, Lfs);

		double[] sA3 = subamostragem(A3);

		// decomposição 4
		ArrayList<Double> A4 = new ArrayList<Double>();
		ArrayList<Double> D4 = new ArrayList<Double>();
		decomposicao(A4, D4, sA3, Lo_D, Hi_D, Lfs);

		double[] sA4 = subamostragem(A4);

		// decomposição 5
		ArrayList<Double> A5 = new ArrayList<Double>();
		ArrayList<Double> D5 = new ArrayList<Double>();
		decomposicao(A5, D5, sA4, Lo_D, Hi_D, Lfs);

		double[] sA5 = subamostragem(A5);

		// decomposição 6
		ArrayList<Double> A6 = new ArrayList<Double>();
		ArrayList<Double> D6 = new ArrayList<Double>();
		decomposicao(A6, D6, sA5, Lo_D, Hi_D, Lfs);

		double[] sA6 = subamostragem(A6);

		// decomposição 7
		ArrayList<Double> A7 = new ArrayList<Double>();
		ArrayList<Double> D7 = new ArrayList<Double>();
		decomposicao(A7, D7, sA6, Lo_D, Hi_D, Lfs);

		double[] sA7 = subamostragem(A7);

		// decomposição 8
		ArrayList<Double> A8 = new ArrayList<Double>();
		ArrayList<Double> D8 = new ArrayList<Double>();
		decomposicao(A8, D8, sA7, Lo_D, Hi_D, Lfs);

		double[] sA8 = subamostragem(A8);

		// reconstrução
		double[] A8r = reconstrucao(sA8, Lo_R, Lfs, A8.size());

		// reconstrução
		double[] A7r = reconstrucao(A8r, Lo_R, Lfs, A7.size());

		// reconstrução
		double[] A6r = reconstrucao(A7r, Lo_R, Lfs, A6.size());

		// reconstrução
		double[] A5r = reconstrucao(A6r, Lo_R, Lfs, A5.size());

		// reconstrução
		double[] A4r = reconstrucao(A5r, Lo_R, Lfs, A4.size());

		// reconstrução
		double[] A3r = reconstrucao(A4r, Lo_R, Lfs, A3.size());

		// reconstrução
		double[] A2r = reconstrucao(A3r, Lo_R, Lfs, A2.size());

		// reconstrução
		double[] A1r = reconstrucao(A2r, Lo_R, Lfs, A1.size());

		return UtilArray.subtract(trecho, A1r);
	}
}

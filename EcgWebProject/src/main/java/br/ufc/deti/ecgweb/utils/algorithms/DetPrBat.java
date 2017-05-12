package br.ufc.deti.ecgweb.utils.algorithms;


import java.util.ArrayList;

public class DetPrBat {
	private double[] vetorPFid;
	private double desvioAmpPfid;
	private double fplim;

	public double[] getVetorPFid() {
		return vetorPFid;
	}

	public void setVetorPFid(double[] vetorPFid) {
		this.vetorPFid = vetorPFid;
	}

	public double getDesvio_ampl_pfid() {
		return desvioAmpPfid;
	}

	public void setDesvio_ampl_pfid(double desvio_ampl_pfid) {
		desvioAmpPfid = desvio_ampl_pfid;
	}

	public double getFplim() {
		return fplim;
	}

	public void setFplim(double fplim) {
		this.fplim = fplim;
	}

	public int picoIntervalo(int inicio, int fim, double[] sinal) {
		int amostra = inicio;

		for (int i = inicio; i <= fim; i++) {
			if (Math.abs(sinal[i]) > Math.abs(sinal[amostra])) {
				amostra = i;
			}
		}
		return amostra;
	}

	public int ultrapassaLimiar2(int start, double[] sinal, int end,
			double limiar) {
		int i = start;
		boolean bool = false;

		int pos = 0;
		while (bool == false & i < end) {
			if (sinal[i] < limiar) {
				bool = true;
			} else {
				i++;
			}
		}

		if (bool) {
			pos = i;
		}

		return pos;
	}

	public int ultrapassaLimiar1(int start, double[] sinal, int end,
			double limiar, double[] vtpic, double dmin) {
		int i = start;
		boolean bool = false;
		int pos = 0;
		if (vtpic == null || vtpic.length == 0) {
			while (bool == false & i < end) {
				if (sinal[i] > limiar) {
					bool = true;
				} else {
					i++;
				}
			}

			if (bool) {
				pos = i;
			}
		} else {
			while (bool == false & i < end) {
				if ((sinal[i] > limiar && (i - vtpic[vtpic.length - 1]) > dmin)
						|| (sinal[i] > limiar && sinal[i] > sinal[(int) vtpic[vtpic.length - 1]])) {
					bool = true;
				} else {
					i++;
				}
				if (bool) {
					pos = i;
				}
			}
		}

		return pos;
	}

	public int mapeiaPico(int ref, double sinal[], int  freq) {
		double tamJanela = Math.round(0.090 * freq);

		int start = (int) Math.abs(Math.max(ref - tamJanela, 1));
		int end = (int) (Math.min(ref + tamJanela, sinal.length));
		double max = UtilArray.max(start, end, sinal);

		if (Math.abs(ref) == max) {
			return (int) ref;
		} else {
			start = (int) Math.max(1, ref - 0.2 * freq);
			end = (int) Math.min(ref + 0.2 * freq, sinal.length);

			double refmean = UtilArray.mean(start, end, sinal);

			int index = (int) ref;
			int ref1 = (int) ref;
			while (index >= Math.max(1, ref1 - tamJanela)) {
				if (Math.abs(sinal[index] - refmean) > Math.abs(sinal[ref]
						- refmean)) {
					ref = index;
				}
				index = index - 1;
			}

			index = ref1;
			ref1 = ref;
			int ref2 = index;
			ref = index;

			while (index < Math.min(sinal.length, ref2 + tamJanela)) {
				if (Math.abs(sinal[index] - refmean) > Math.abs(sinal[ref]
						- refmean)) {
					ref = index;
				}
				index = index + 1;
			}

			ref2 = ref;

			if (Math.abs(sinal[ref1] - refmean) > Math.abs(sinal[ref2]
					- refmean)) {
				return ref1;
			} else {
				return ref2;
			}
		}
	}

	public void process(double[] sinal, double[] sinal_ref, double ider , double nBatmin , double nBatmax, int freq) {

		int pinic = (int) Math.round(0.04 * freq);
		int pfin = sinal.length - pinic;

		double maxSinal = UtilArray.max(pinic, pfin, sinal);
		UtilArray.divide(sinal, maxSinal); // Normaliza��es

		boolean vbolParou = false;
		double fplim = 0.3;
		double fplimI = 0.3;

		maxSinal = UtilArray.max(pinic, pfin, sinal);
		double limiarAmp = fplim * maxSinal;
		double dmin = 0.250 * freq; // Dist�ncia m�nima entre dois
												// pontos fiduciais
		int Niter = 0;
		ArrayList<Integer> vetorPFid = null;
		ArrayList<Integer> vetorPFid_Sfilt = null;
		

		while (vbolParou == false && Niter <= 50) {
			
			int i = pinic;
			vetorPFid = new ArrayList<Integer>();
			vetorPFid_Sfilt = new ArrayList<Integer>();
			maxSinal = UtilArray.max(pinic, pfin, sinal);
			limiarAmp = fplim * maxSinal;
						
			if (sinal[i] > limiarAmp) {
				i = ultrapassaLimiar2(i, sinal, (int) pfin, limiarAmp) + 1;
			}

			while (i < pfin) {
				int PosAcima = ultrapassaLimiar1(i, sinal, pfin, limiarAmp,
						UtilArray.toDouble(vetorPFid_Sfilt), dmin);

				if (PosAcima == 0) {
					break;
				}

				
				int PosAbaixo = ultrapassaLimiar2(PosAcima, sinal, pfin,limiarAmp);

				if (PosAbaixo == 0) {
					break;
				}

				int PontoFiducial = picoIntervalo(PosAcima, PosAbaixo, sinal);

				if (vetorPFid_Sfilt.size() > 0) {
					int end = vetorPFid_Sfilt.size() - 1;

					if (Math.abs(PontoFiducial - vetorPFid_Sfilt.get(end)) > dmin) {
						vetorPFid_Sfilt.add(PontoFiducial);
					} else {

						if (sinal[PontoFiducial] > sinal[vetorPFid_Sfilt
								.get(end)]) {
							vetorPFid_Sfilt.set(end, PontoFiducial);
						}
					}
				} else {
					vetorPFid_Sfilt.add(PontoFiducial);
				}
				i = PosAbaixo + 1;
			} //end while
			

			for (int jj = 0; jj < vetorPFid_Sfilt.size(); jj++) {
				int PontoFiducial = vetorPFid_Sfilt.get(jj);
				int PicoMapeado = mapeiaPico(PontoFiducial, sinal_ref,freq);
				PontoFiducial = PicoMapeado;

				if (jj == 0) {
					vetorPFid.add(PontoFiducial);
				} else {
					int end = vetorPFid.size() - 1;
					if (Math.abs(PontoFiducial - vetorPFid.get(end)) > dmin) {
						vetorPFid.add(PontoFiducial);
					} else {
						if (Math.abs(sinal_ref[PontoFiducial]) > Math
								.abs(sinal_ref[vetorPFid.get(end)])) {
							vetorPFid.add(PontoFiducial);
						}
					}
				}
			}

			double[] pos = UtilArray.getPositions(sinal, vetorPFid_Sfilt);
			this.desvioAmpPfid = UtilArray.std(pos);

			if (vetorPFid.size() > 2) {
				if ((vetorPFid.size() >= (int) nBatmin && vetorPFid
						.size() < (int) nBatmax)
						&& Math.abs(vetorPFid.get(0) - 1) <= 2 * freq
						&& Math.abs(UtilArray.getLast(vetorPFid) - sinal.length) <= 2 * freq) {
					vbolParou = true;
				} else {
					if ((vetorPFid.size() < nBatmin && fplim >= 0.5 * fplimI)
							|| Math.abs(vetorPFid.get(0) - 1) > 2 * freq
							|| Math.abs(UtilArray.getLast(vetorPFid)
									- sinal.length) > 2 * freq) {
						fplim = fplim * 0.8;
					} else {
						fplim = fplim * 1.2;
					}

					Niter = Niter + 1;
				}

			} else {
				fplim = fplim * 0.7;
				Niter = Niter + 1;
			}

		}

		//convert to double[]
		this.vetorPFid = new double[vetorPFid.size()];
		for(int i = 0 ; i < vetorPFid.size(); i++){
			this.vetorPFid[i] =  (double)vetorPFid.get(i);	
		}
		

		this.fplim = fplim;
	}

}

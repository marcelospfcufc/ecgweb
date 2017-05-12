package br.ufc.deti.ecgweb.utils.algorithms;
import java.util.ArrayList;
import java.util.List;

public class UtilArray {
	//aloca e copia todos os elementos 
	public static void add(List<Integer> dst, double[] src ){
		for(int i = 0 ; i < src.length;i++){
			dst.add((int)src[i]);
		}
	}
	
	public static <T> T getLast(List<T> list) {
		return list != null && !list.isEmpty() ? list.get(list.size() - 1)
				: null;
	}

	public static double[] getPositions(double[] vect, ArrayList<Integer> pos) {
		double[] res = new double[pos.size()];
		int i = 0;
		for (Integer p : pos) {
			res[i++] = vect[p];
		}
		return res;
	}

	public static int[] toInteger(ArrayList<Integer> array) {
		int[] resp = new int[array.size()];

		for (int i = 0; i < array.size(); i++) {
			resp[i] = array.get(i);
		}
		return resp;
	}

	public static double[] toDouble(ArrayList<Integer> array) {
		double[] resp = new double[array.size()];

		for (int i = 0; i < array.size(); i++) {
			resp[i] = (double) array.get(i);
		}
		return resp;
	}

	public static double[] getSub(double[] array, int start, int end) {
		double res[] = new double[end - start];
		int pos = 0;
		for (int i = start; i < end; i++) {
			res[pos++] = array[i];
		}
		return res;
	}

	/** All rows , sub columns */
	public static double[][] getSub(ArrayList<Double[]> array, int startCol,
			int endCol) {
		double res[][] = new double[array.size()][endCol - startCol];

		int posCol = 0 ;
		//all rows
		for (int i = 0; i < array.size(); i++) {
			posCol = 0;
			for(int j = startCol ; j < endCol ;j++){
				res[i][posCol++] = array.get(i)[j];
			}
		}

		return res;
	}

	// get all row, and a sub cols (start- end)
	public static double[][] getSubCols2D(double[][] array, int start, int end) {
		double res[][] = new double[array.length][];
		int pos = 0;
		for (int j = 0; j < array.length; j++) {
			res[j] = new double[end-start];
			pos = 0 ;
			for (int i = start; i < end; i++) {
				res[j][pos] = array[j][i];
				pos++;
			}
			
		}
		return res;
	}

	public static double[][] sum(double[][] array, double value) {
		double res[][] = new double[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				res[i][j] = array[i][j] + value;
			}
		}
		return res;
	}

	public static double[] sum(double[] array, double value) {
		double res[] = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			res[i] = array[i] + value;
		}
		return res;
	}

	// concatena as colunas de dois vetores com a mesma quantidade de linhas
	public static double[][] concatCols2D(double[][] array, double[][] array2) {
		int cols = 0;
		int rows = 0 ;
		
		if(array!= null){
			rows = array.length;
		}else{
			rows = array2.length;
		}
		
		if (array != null && array[0].length > 0) {
			cols += array[0].length;
		}
		if (array2 != null && array2[0].length > 0) {
			cols += array2[0].length;
		}

		double res[][] = new double[rows][cols];
		int posA = 0;
		if( array != null ){
			for (int i = 0; i < array.length; i++) {
				posA = 0 ;
				for (int j = 0; j < array[0].length; j++) {
					res[i][posA++] = array[i][j];
				}
			}
		}
		
		int posB  = 0 ;
		if( array2 != null ){
			for (int i = 0; i < array2.length; i++) {
				posB = posA;
				for (int j = 0; j < array2[0].length; j++) {
					res[i][posB++] = array2[i][j];
				}
			}
		}

		return res;
	}

	public static double[] concat(double[] array, double[] array2) {
		int cols = 0;

		if (array != null && array.length > 0) {
			cols += array.length;
		}
		if (array2 != null && array2.length > 0) {
			cols += array2.length;
		}

		double res[] = new double[cols];
		int pos = 0;

		if(array != null){
			for (int i = 0; i < array.length; i++) {
				res[pos] = array[i];
				pos++;
			}
		}
		
		if(array2 != null){
			for (int i = 0; i < array2.length; i++) {
				res[pos] = array2[i];
				pos++;
			}
		}
		return res;
	}

	public static double[] concat(double[] array, double value) {
		int cols = 0;

		if (array != null && array.length > 0) {
			cols += array.length;
		}
		cols++;

		double res[] = new double[cols];
		int pos = 0;
		for (int i = 0; i < array.length; i++) {
			res[pos] = array[i];
			pos++;
		}

		res[pos] = value;

		return res;
	}

	/**
	 * change array parameter
	 */
	public static void subtract(double[] array1, double value) {
		for (int i = 0; i < array1.length; i++) {
			array1[i] -= value;
		}
	}

	public static double[] subtract(ArrayList<Double> array, double value) {
		double[] res = new double[array.size()];
		for (int i = 0; i < array.size(); i++) {
			res[i] = array.get(i) - value;
		}
		return res;
	}

	public static double[][] subtract(double[][] array, double value) {
		double[][] res = new double[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				res[i][j] = array[i][j] - value;
			}
		}
		return res;
	}

	public static double[] subtract(double[] array1, double[] array2) {
		double[] res = new double[array1.length];
		for (int i = 0; i < array1.length; i++) {
			res[i] = array1[i] - array2[i];
		}
		return res;
	}

	/** subtract rows :Matriz_Off(:,ii) - Matriz_On(:,ii) */
	public static double[][] subtractRows(double[][] array1, double[][] array2,
			int col) {
		double[][] res = new double[array1.length][1];
		for (int i = 0; i < array1.length; i++) {
			res[i][0] = array1[i][col] - array2[i][col];
		}
		return res;
	}

	/** subtract rows :Matriz_Off(:,ii) - value */
	public static double[][] subtractRows(double[][] array1, double value,
			int col) {
		double[][] res = new double[array1.length][1];
		for (int i = 0; i < array1.length; i++) {
			res[i][0] = array1[i][col] - value;
		}
		return res;
	}

	public static ArrayList<Integer> findGreater(double[] array, double value) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] > value) {
				res.add(i);
			}
		}
		return res;
	}

	public static ArrayList<Integer> findEqual(double[] array, double value) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				res.add(i);
			}
		}
		return res;
	}

	public static ArrayList<Integer> findEqualRow(double[][] array,
			double value, int col) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i][col] == value) {
				res.add(i);
			}
		}
		return res;
	}

	public static double std(double[] a) {
		return Math.sqrt(var(a));
	}

	public static double var(double[] a) {

		if (a.length == 0)
			return Double.NaN;
		double avg = mean(a);
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i] - avg) * (a[i] - avg);
		}
		return sum / (a.length - 1);
	}

	public static double sum(double[] a) {
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

	public static double min(double sinal[]) {
		double min = sinal[0];

		for (int i = 0; i < sinal.length; i++) {
			if (min > sinal[i]) {
				min = sinal[i];
			}
		}
		return min;
	}

	public static double max(double sinal[]) {
		double max = sinal[0];

		for (int i = 0; i < sinal.length; i++) {
			if (max < sinal[i]) {
				max = sinal[i];
			}
		}
		return max;
	}

	public static double[] abs(double sinal[]) {
		double[] res = new double[sinal.length];

		for (int i = 0; i < sinal.length; i++) {
			res[i] = Math.abs(sinal[i]);
		}

		return res;
	}

	public static double mean(double[] a) {
		double mean = 0.0;
		for (int i = 0; i < a.length; i++) {
			mean += a[i] / a.length;
		}
		return mean;
	}

	public static double[] pow(double[] v1, int n) {
		double[] res = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			res[i] = Math.pow(v1[i], n);
		}
		return res;
	}

	public static void multiply(double[] array1, double value) {
		for (int i = 0; i < array1.length; i++) {
			array1[i] *= value;
		}
	}

	public static void multiply(double[] array1, double[] array2) {
		for (int i = 0; i < array1.length; i++) {
			array1[i] *= array2[i];
		}
	}

	// (v1 - v2)/v2
	public static double[] subDiv(double[] v1, double[] v2) {
		// double Aval_2 = (metrica1(2:end) -
		// metrica1(1:end-1))./metrica1(1:end-1);
		double[] res = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			res[i] = (v1[i] - v2[i]) / v2[i];
		}
		return res;
	}

	public static double max(int start, int end, double sinal[]) {
		double max = sinal[start];

		for (int i = start; i < end; i++) {
			if (max < sinal[i]) {
				max = sinal[i];
			}
		}
		return max;
	}

	public static double mean(int start, int end, double sinal[]) {
		double mean = 0;
		double length = end - start;
		for (int i = start; i < end; i++) {
			mean += sinal[i] / length;
		}
		return mean;
	}

	public static double meanRow(double sinal[][], int col) {
		double mean = 0;

		for (int i = 0; i < sinal.length; i++) {
			mean += sinal[i][col] / sinal.length;
		}
		return mean;
	}

	public static double minRow(double sinal[][], int col) {
		double min = sinal[0][col];

		for (int i = 0; i < sinal.length; i++) {
			if (min > sinal[i][col]) {
				min = sinal[i][col];
			}
		}
		return min;
	}

	public static double maxRow(double sinal[][], int col) {
		double max = sinal[0][col];

		for (int i = 0; i < sinal.length; i++) {
			if (max < sinal[i][col]) {
				max = sinal[i][col];
			}
		}
		return max;
	}

	public static void divide(double[] array1, double value) {
		for (int i = 0; i < array1.length; i++) {
			array1[i] /= value;
		}
	}

	// get elements with ids in array
	public static double[] getElements(double[] array, ArrayList<Integer> ids) {
		double[] res = new double[ids.size()];
		int i = 0;
		for (int id : ids) {
			res[i++] = array[id];
		}
		return res;
	}

	// get elements with ids in array
	public static double[] getElements(double[] array, double[] ids) {
		double[] res = new double[ids.length];
		int i = 0;
		for (double id : ids) {
			res[i++] = array[(int) id];
		}
		return res;
	}


	// array format: re,im,re,im...
	public static double[] fftAbs(double[] array) {
		double[] res = new double[array.length/2];
		int pos = 0 ;
		for (int i = 0; i < array.length; i += 2) {
			res[pos ++]  = Math.sqrt(Math.pow(array[i], 2) +   Math.pow(array[i+1], 2) );
		}
		return res;
	}
	
}

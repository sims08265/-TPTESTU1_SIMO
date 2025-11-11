package com.stjean.operation;

import java.util.Arrays;

public class OperationMathematique {

public static boolean  estPositif(int nombre) {
	 return nombre>0;
 }
 
 public static long factoriel(long a) throws IllegalParamISIException {
		if(a<0)
			throw new IllegalParamISIException("il faut un nombre positif");
		if(a==0 || a==1) 
			return 1;
		return a*factoriel(a-1);
	}
 
 public static int[] trier(int[] listes) {
	 Arrays.sort(listes);
	 return listes;
 }
 
 public static double maxNumba(double[] valeurs) {
	 return Arrays.stream(valeurs).max().getAsDouble();
 }
}

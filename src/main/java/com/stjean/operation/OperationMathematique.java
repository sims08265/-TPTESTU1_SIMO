package com.stjean.operation;

public class OperationMathematique {

public static boolean  estPositif(int nombre) {
	 return nombre>0;
 }
 
 public static long factoriel(long a) throws FactorielInvalidException {
		if(a<0)
			throw new FactorielInvalidException("il faut un nombre positif");
		if(a==0 || a==1) 
			return 1;
		return a*factoriel(a-1);
	}
}

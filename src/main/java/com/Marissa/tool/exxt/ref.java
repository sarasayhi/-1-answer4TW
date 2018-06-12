package com.Marissa.tool.exxt;

public class ref{
	public static void main(String[] args) {
		int len = 5;
		Object[] o = new Object[len];
		int i = 0;
		o[i++] = 2;
		o[i++] = 3;
		for(Object oo : o) {
			System.out.println(oo);
		}
	}
	
}
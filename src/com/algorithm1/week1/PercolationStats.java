package com.algorithm1.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
	private int noOfTrials;
	private Percolation per;
	private double[] probabilites;
	
	public PercolationStats(int n, int trials) {
		// TODO Auto-generated constructor stub
		noOfTrials = n;
		probabilites = new double[trials];
		for (int i = 0; i < probabilites.length; i++) {
			per = new Percolation(n);
			int count = 0;
			while(!per.percolates()) {
				int row = StdRandom.uniform(1, n+1);
				int col = StdRandom.uniform(1, n+1);
				//System.out.println(row + " " + col);
				per.open(row, col);
				count ++;
			}
			int nos = per.numberOfOpenSites();
//			System.out.println("no of times random input takes " + count);
//			System.out.println("no of open sites = " + nos + " out of total " + n*n + " sites.");
//			System.out.println(nos/(n*n*1.0));
			probabilites[i] = nos/(n*n*1.0);
		}
		for (int i = 0; i < probabilites.length; i++) {
			System.out.print(probabilites[i] + "     ");
		}
		System.out.println();
		
		 
	}
	
	public static void main(String[] args) {
		int n = StdIn.readInt();
		int trials =  StdIn.readInt();
		PercolationStats perStat = new PercolationStats(n, trials);
		
		
	}

}

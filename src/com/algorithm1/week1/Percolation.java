package com.algorithm1.week1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {
	private boolean[][] sites;
	private int virtualTopSite;
	private int virtualBottomSite;
	private int size;
	private int numberOfOpenSites;
	private WeightedQuickUnionUF wquf;
	
	
	public Percolation(int n) {
		// TODO Auto-generated constructor stub
		size = n;
		virtualTopSite = 0;
		numberOfOpenSites = 0;
		virtualBottomSite = n*n + 1;
		sites = new boolean[n][n];
		wquf = new WeightedQuickUnionUF(n*n+2);
	}
	
	private int getFlattenedArrayIndex(int row, int col) {
		return size*(row-1) + col;
	}
	
	public boolean isOpen(int row, int col) {
		return sites[row-1][col-1];
	}
	
	public void open(int row, int col) {
		//if (!sites[row-1][row-1])   return ;
		sites[row-1][col-1] = true;
		if( row==1 ) {
			wquf.union(virtualTopSite, this.getFlattenedArrayIndex(row, col));
		} else if (row == size) {
			wquf.union(virtualBottomSite, this.getFlattenedArrayIndex(row, col));
		}
		if ( row > 1 && this.isOpen(row-1, col)) {
			wquf.union(this.getFlattenedArrayIndex(row, col), this.getFlattenedArrayIndex(row-1, col));
		}
		if ( row < size  && this.isOpen(row+1, col)) {
			wquf.union(this.getFlattenedArrayIndex(row, col), this.getFlattenedArrayIndex(row+1, col));
		}
		
		if ( col > 1 && this.isOpen(row, col-1)) {
			wquf.union(this.getFlattenedArrayIndex(row, col), this.getFlattenedArrayIndex(row, col-1));
		}
		
		if (col < this.size && this.isOpen(row, col+1)) {
			wquf.union(this.getFlattenedArrayIndex(row, col), this.getFlattenedArrayIndex(row, col+1));
		}
		
		
	}
	
	public boolean isFull(int row, int col) {
		return wquf.connected(virtualTopSite, this.getFlattenedArrayIndex(row, col));
	}
	public int numberOfOpenSites() {
		for (int i = 0; i < sites.length; i++) {
			for (int j = 0; j < sites.length; j++) {
				if(sites[i][j]) numberOfOpenSites++;
			}
		}
		return numberOfOpenSites;
	}
	public boolean percolates() {
		return wquf.connected(virtualTopSite, virtualBottomSite);
	}
	public static void main(String[] args) {
		int n = StdIn.readInt();
		//99System.out.println(n);
		Percolation per = new Percolation(n);
		int count = 0;
		while(!per.percolates()) {
			int row = StdRandom.uniform(1, n+1);
			int col = StdRandom.uniform(1, n+1);
			//System.out.println(row + " " + col);
			per.open(row, col);
			count ++;
		}
		int nos = per.numberOfOpenSites();
		System.out.println("no of times random input takes " + count);
		System.out.println("no of open sites = " + nos + " out of total " + n*n + " sites.");
		System.out.println(nos/(n*n*1.0));
		
		
	}
	
}


/*public class Percolation {
	private int[][] A;
	private int numberOfOpenSites;

	public Percolation(int n) throws java.lang.IllegalArgumentException {
		// TODO Auto-generated constructor stub
		A = new int [n][n];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = 1;
			}
		}

	}
	
	public void open(int row,int col) throws java.lang.IllegalArgumentException {
		if(!isOpen( row, col)) {
			A[row][col] = 0;
		}
	}
	
	public boolean isOpen(int row, int col) throws java.lang.IllegalArgumentException {
		return (A[row][col]==0);
	}
	
	public boolean isFull(int row, int col) throws java.lang.IllegalArgumentException {
		return (A[row][col]==1);
	}
	
	public int numberOfOpenSites() {
		numberOfOpenSites = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if(A[i][j]==1) {
					numberOfOpenSites++;
				}
			}
		}
		return numberOfOpenSites;
	}
	
	public boolean percolates() {
		WeightedQuickUnionUF wquf = new WeightedQuickUnionUF(n*n);
	}
}
*/

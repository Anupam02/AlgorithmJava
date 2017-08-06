package com.algorithm1.week1;
import  java.util.Scanner;
import  edu.princeton.cs.algs4.*;
// eager approach
class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N)  {
		// TODO Auto-generated constructor stub
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	public boolean isConnected(int p, int q) {
		return id[p] == id[q];
	}
	
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == qid) id[i] = pid; 
		}
	}
	
}
// betterapproach
class QuickUnionUF {
	private int[] id;
	
	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	private int root(int i) {
		while ( i != id[i]) i = id[i];
		return i;
	}
	
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}


class QuickUnionWeightedUF {
	private int[] id;
	private int[] size;
	
	public QuickUnionWeightedUF(int N) {
		// TODO Auto-generated constructor stub
		id = new int[N];
		size = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int root(int i) {
		while ( i != id[i] ) i = id[i];
		return i;
	}
	
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j ) return ;
		if (size[i] < size[j]) {
			id[i] = j; size[j] += size[i];
		} else {
			id[j] = i; size[i] += size[j];
		}
	}
	
}

class QuickUnionPathComression {
	private int[] id;
	
	public QuickUnionPathComression(int N) {
		// TODO Auto-generated constructor stub
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	public int root(int i) {
		while ( i != id[i]) {
			id [i] = id[id[i]]; //one extra line of code
			i = id[i];
		}
		return i;
	}
	
	public boolean isConnected(int p, int q) {
		return (root(p)==root(q));
	}
	 
	public void union(int p, int q) {
		int i  = root(p);
		int j  = root(q);
		id[i] = j;
	}
}

class QuickUnionWeightedPathCompression {
	private int[] id;
	private int[] size;
	
	public QuickUnionWeightedPathCompression(int N) {
		// TODO Auto-generated constructor stub
		id  = new int[N];
		size = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
		for (int i = 0; i < size.length; i++) {
			size[i] = 1;
		}
	}
	public int  root(int i) {
		while (i != id[i] ) {
			id[i]  = id[id[i]];
			i = id[i];
		}
		return i;
	}
	public boolean isConnected(int p, int q) {
		return (root(p)==root(q));
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if ( size[i] < size[j] ) {
			id[i] = id[j];
			size[j] += size[i];
		} else {
			id[j] = id[i];
			size[i] += size[j];
		}
	}
}
class UnionFind {
	int N;
	public UnionFind(int n) {
		// TODO Auto-generated constructor stub
		N = n; 
	}
	

}

public class UF {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		QuickUnionWeightedPathCompression quwpc = new QuickUnionWeightedPathCompression(n);
		//QuickUnionPathComression qupc = new QuickUnionPathComression(n);
		//QuickUnionWeightedUF quwf  = new QuickUnionWeightedUF(n);
		//QuickUnionUF qff = new QuickUnionUF(n);
		//QuickFindUF quf = new QuickFindUF(n);
		//UnionFind uf = new UnionFind(n);
		//System.out.println(n);
		for (int i = 0; i < n; i++) {
			int p = input.nextInt();
			int q = input.nextInt();
			System.out.println(p+" "+q);
			if(!quwpc.isConnected(p, q)) {
				quwpc.union(p, q);
				System.out.println(p + "   " + q);
			}
		}
		input.close();
		
	}
}

/* 
11
4 3
3 8
6 5
9 4
2 1
8 9
5 0
7 2
6 1
1 0
6 7 */
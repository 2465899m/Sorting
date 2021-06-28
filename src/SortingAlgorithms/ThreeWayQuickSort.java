package SortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreeWayQuickSort {
	private static int[] readFile(String filePath) throws IOException{
		ArrayList<Integer> arrayList = new ArrayList<>();
		try (Scanner s = new Scanner(new File(filePath))) {
		    while (s.hasNext()) {
		    	int p = Integer.parseInt(s.nextLine());
		        arrayList.add(p);
		    }
		}
		catch (FileNotFoundException e) {
		    // Handling any exception that may occur if file not ffound. 
		}
		int[] z = new int[arrayList.size()];
	    for (int i=0; i < z.length; i++)
	    {
	        z[i] = arrayList.get(i).intValue();
	    }
	    return z;
	}

	public static boolean isSorted(int a[]){
		int n = a.length;
		for (int i = 0; i < n-1; i++){
			if (a[i] > a[i+1]){
				return false;
			}
		}
		return true;
	}
	//Swapping a and b. 
	private static int[] swap(int[] A, int a, int b) {
		int key = A[a];
		A[a] = A[b];
		A[b] = key;
		return A;
	}
	private static int[] partitionThree(int[] A, int p, int r) {
		int lp = p;
		int i = p;
		int rp = r;
		int x = A[r];
		while(i<=rp) {
			if(A[i] < x) {
				swap(A, lp, i);
				lp++;
				i++;
			}
			else if(A[i]>x) {
				swap(A, rp, i);
				rp--;
			}
			else {
				i++;
			}
		}
		int[] partition = {lp, rp};
		return partition;
	}

	public static int[] threeWayQuickSort(int[] A, int p, int r) {
		if(p<r) {
			int[] partition = partitionThree(A,p,r);
			threeWayQuickSort(A, p, partition[0] - 1);
			threeWayQuickSort(A, partition[1], partition[0]);
			threeWayQuickSort(A, partition[1]+1, r);
		}
		return A;
	}
	public static void main(String... args) {
		String[] files = {"int10", "int50","int100","int1000","int20k", "int500k", "intBig", "dutch"};
		System.out.println("---------------------");
		System.out.println("THREE WAY QUICK SORT");
		for(String file: files) {
			try {
				System.out.println("---------------------");
				System.out.print("The name of the file-");
				System.out.println(file);
				int[] A = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = A.length;
				System.out.print("Is the file sorted?");
				long startTime = System.nanoTime();
				int[] B = threeWayQuickSort(A, 0, n-1);
				long endTime = System.nanoTime();
				System.out.println(isSorted(B));
				System.out.println("time taken: "+(endTime - startTime)+" ns");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

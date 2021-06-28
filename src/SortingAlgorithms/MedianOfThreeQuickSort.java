package SortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MedianOfThreeQuickSort {
	@SuppressWarnings("unused")
	private static void mtq(int arr[]) { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
    } 
	private static int[] readFile(String filePath) throws IOException{
		ArrayList<Integer> arrayList = new ArrayList<>();
		try (Scanner s = new Scanner(new File(filePath))) {
		    while (s.hasNext()) {
		    	int p = Integer.parseInt(s.nextLine());
		        arrayList.add(p);
		    }
		}
		catch (FileNotFoundException e) {
		    // Handling any exception that may occur if file not found. 
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
	//Median of three value partition. 
	private static int medianValuePivot(int[] z, int p, int r) {
		int c = (p + r) / 2;
		if (z[r] < z[p])
			z = swap(z, r, p);
		if (z[c] < z[p])
		    z = swap(z, c, p);
		if (z[r] < z[c])
		    z = swap(z, r, c);
		z = swap(z, r-1, c);
		int x = z[r-1];
		int i = p - 1;
		for (int j=p; j<r; j++) {
			if(z[j]<=x) {
				i += 1;
				z = swap(z, i, j);
			}
		}
		z = swap(z, i+1, r);
		return i+1;
		
		

	}
	//Part 1(c)
	public static int[] medianOfThreeQuickSort(int[] z, int p, int r) {
		if(p<r) {
			int q = medianValuePivot(z,p,r);
			medianOfThreeQuickSort(z,p,q-1);
			medianOfThreeQuickSort(z,q+1,r);
		}
		return z;
	}
	
//Part 3. 
	public static int[] pathologicalInput(int a) {
        int[] z= new int[a]; //initialising a new pathological array for part 3.
        
        int i=0; 
        for(int num=0; num<a;num++) {
          if(num%2==0){ 
            z[i] = num;
            i++;
          }
          else {
            z[a-i] = num;
          }
        }
        return z;
    }
	public static void main(String... args) {
		String[] files = {"int10", "int50","int100","int1000","int20k", "int500k", "intBig", "dutch"};
		System.out.println("--------------------------");
		System.out.println("MEDIAN OF THREE QUICK SORT");
		for(String file: files) {
			try {
				System.out.println("--------------------------");
				System.out.print("The name of the file-");
				System.out.println(file);
				int[] z = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = z.length;
				System.out.print("Is the file sorted?");
				
				long startTime = System.nanoTime();
				int[] B = medianOfThreeQuickSort(z, 0, n-1);
				long endTime = System.nanoTime();
				
				System.out.println(isSorted(B));
				System.out.println("time taken: "+(endTime - startTime)+" ns");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

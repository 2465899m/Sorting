package SortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InsertionSort {
	private static int[] readFile(String filePath) throws IOException{
		ArrayList<Integer> arrayList = new ArrayList<>();
		try (Scanner s = new Scanner(new File(filePath))) {
		    while (s.hasNext()) {
		    	int g = Integer.parseInt(s.nextLine());
		        arrayList.add(g);
		    }
		}
		catch (FileNotFoundException e) {
		    // Handling any exceptions that may occur. 
		}
		int[] D = new int[arrayList.size()];
	    for (int i=0; i < D.length; i++)
	    {
	        D[i] = arrayList.get(i).intValue();
	    }
	    return D;
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
	private static int[] insertionSort(int[] D, int g, int r) {
		for (int x = g + 1; x <= r; x++){
            int key = D[x];
            int j = x - 1;
            while (j >= 0 && key < D[j]){
                D[j + 1] = D[j];
                j--;
            }
            D[j + 1] = key;
        }
		return D;
	}

	public static void main(String... args) {
		String[] files = {"int10", "int50","int100","int1000","int20k", "int500k", "intBig", "dutch"};
		System.out.println("---------------------");
		System.out.println("INSERTION SORT");
		for(String file: files) {
			try {
				System.out.println("---------------------");
				System.out.print("The name of the file-");
				System.out.println(file);
				int[] D = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = D.length;
				System.out.print("Is the file sorted?");
				long startTime = System.nanoTime();
				int[] B = insertionSort(D, 0, n-1);
				long endTime = System.nanoTime();
				System.out.println(isSorted(B));
				System.out.println("time taken: "+(endTime - startTime)+" ns");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

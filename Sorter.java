package sort;

import java.util.*;

/*
 Sorter class utilizes four different sort methods to sort int arrays
 */

public class Sorter 
{
	//MERGESORT
	static void mergeSort(int a[], int left, int right) 
	{ 
		if (left < right) 
		{ 
			int middle = (left + right) / 2; 
			mergeSort(a, left, middle); 
			mergeSort(a, middle + 1, right); 
			merge(a, left, middle, right); 
		} 
	} 

	private static void merge(int a[], int p, int q, int r) 
	{ 
		int n1 = q - p + 1; 
		int n2 = r - q; 
		int L[] = new int[n1]; 
		int R[] = new int[n2]; 

		for (int i = 0; i < n1; ++i) 
		{
			L[i] = a[p + i]; 
		}
		for (int j = 0; j < n2; ++j) 
		{
			R[j] = a[q + 1 + j]; 
		}
		int i = 0;
		int j = 0; 
		int k = p;

		while (i < n1 && j < n2) 
		{ 
			if (L[i] <= R[j]) { 
				a[k] = L[i]; 
				i++; 
			} 
			else { 
				a[k] = R[j]; 
				j++; 
			} 
			k++; 
		} 

		while (i < n1 && k < a.length) 
		{ 
			a[k] = L[i]; 
			i++; 
			k++; 
		} 

		while (j < n2 && k < a.length) 
		{ 
			a[k] = R[j]; 
			j++; 
			k++; 
		} 
	}

	//QUICKSORT
	public static void quickSort(int[] A, int p, int r)
	{
		if(p < r)
		{
			int q = partition(A, p, r);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
	}

	private static int partition(int[] A, int p, int r)
	{
		int pivot = A[r];
		int i = p - 1;
		for(int j = p; j < r; j++)
		{
			if(A[j] <= pivot)
			{
				i++;
				exchange(A, i, j);
			}
		}
		exchange(A, i+1, r);
		return i + 1;
	}

	private static void exchange(int[] A, int p, int r)
	{
		int temp = A[p];
		A[p] = A[r];
		A[r] = temp;
	}

	//HEAPSORT
	public static void heapSort(int[] a)
	{
		buildMaxHeap(a);
		for(int i = a.length -1; i > 0; i--)
		{
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			maxHeapify(a, i, 0);
		}
	}

	public static void buildMaxHeap(int[] a)
	{
		int n = a.length; //heap size
		for(int i = a.length/2; i >= 0; i--)
		{
			maxHeapify(a, n, i);
		}
	}

	public static void maxHeapify(int a[], int n, int i) // n = heap size
	{ 
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;

		if (l < n && a[l] > a[largest]) {
			largest = l; 
		}

		if (r < n && a[r] > a[largest]) {
			largest = r; 
		}

		if (largest != i) 
		{ 
			int temp = a[i]; 
			a[i] = a[largest]; 
			a[largest] = temp; 
			maxHeapify(a, n, largest); 
		} 
	}

	//INSERTIONSORT
	public static void insertionSort(int[] a)
	{
		for (int j = 1; j < a.length; j++) {
			int key = a[j];
			int i = j-1;
			while (i >= 0 && a[i] > key)
			{
				a[i+1] = a[i];
				i--;
				a[i+1] = key;
			}
		}
	}

	
	/*
	 Main method creates four arrays with the same size (100000) and contents and sorts them using all four methods
	 It then times how long each method takes and compares the times at the end to see which one is the fastest
	 */
	public static void main (String[] args)
	{
		int[] test = new int[100000];
		Random gen = new Random();
		for(int i = 0; i < test.length - 1; i++)
		{
			test[i] = gen.nextInt(1000);
		}
		int[] test2 = test.clone();
		int[] test3 = test.clone();
		int[] test4 = test.clone();
		
		long startTime = System.nanoTime();
		mergeSort(test, 0, test.length-1);
		long endTime = System.nanoTime();
		long MSRunningTime = endTime - startTime;
		System.out.println("MergeSort Running Time: " + MSRunningTime + " nano seconds");

		startTime = System.nanoTime();
		quickSort(test2, 0, test2.length-1);
		endTime = System.nanoTime();
		long QSRunningTime = endTime - startTime;
		System.out.println("QuickSort Running Time: " + QSRunningTime + " nano seconds");

		startTime = System.nanoTime();
		heapSort(test3);
		endTime = System.nanoTime();
		long HSRunningTime = endTime - startTime;
		System.out.println("HeapSort Running Time: " + HSRunningTime + " nano seconds");
		
		startTime = System.nanoTime();
		insertionSort(test4);
		endTime = System.nanoTime();
		long ISRunningTime = endTime - startTime;
		System.out.println("InsertionSort Running Time: " + ISRunningTime + " nano seconds");

		if(MSRunningTime < QSRunningTime && MSRunningTime < HSRunningTime && MSRunningTime < ISRunningTime)
		{
			System.out.print("MergeSort was fastest");
		}
		else if(QSRunningTime < HSRunningTime && QSRunningTime < ISRunningTime)
		{
			System.out.print("QuickSort was fastest");
		}
		else if(HSRunningTime < ISRunningTime)
		{
			System.out.print("HeapSort was fastest");
		}
		else
		{
			System.out.print("InsertionSort was fastest");
		}
	}

}

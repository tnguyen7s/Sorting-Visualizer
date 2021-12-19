
import java.util.Arrays;

/*This class is used to process sorting to get parameters associated to the sorting process (time and number of comparisons)*/
public class Array{
    private int [] array;
    private int size;
    private int time;
    private int comparisons;
    public Array(int[] a)
    {
        this.array=Arrays.copyOf(a, a.length);
        this.size=a.length;
    }
    public int[] bubbleSort()
    {
        int tmp;
        boolean swapOccured=true;
        int i;
        long start=System.nanoTime();
        for (i=size-2; i>=0 && swapOccured; i--)
        {
            int j;
            swapOccured=false;
            for (j=0; j<=i; j++)
            {
                if (array[j]>array[j+1])
                {
                    swapOccured=true;
                    tmp=array[i];
                    array[i]=array[j];
                    array[j]=tmp;
                }
                comparisons++;
            }
        }
        long end = System.nanoTime();
        time = (int) (end-start);
        int [] returned = new int[2];
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
    }
    public int[] selectionSort()
    {
        int smallest;
        int tmp;
        long start=System.nanoTime();
        for (int i=0; i<=size-2; i++)
        {
            smallest=i;
            for (int j=i+1; j<size; j++)
            {
                comparisons++;
                if (array[j]<array[smallest])
                {
                    smallest=j;
                }
            }
            comparisons++;
            if (smallest!=i)
            {
                tmp=array[i];
                array[i]=array[smallest];
                array[smallest]=tmp;
            }
        }
        long end = System.nanoTime();
        int[] returned = new int[2];
        time=(int) (end-start);
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
    }
    public int[] insertionSort()    
    {
        long start = System.nanoTime();
        int unsorted;
        for (int i=1; i<size; i++)
        {
            unsorted=array[i];
            int j;
            for (j=i-1; j>=0; j--)
            {
                comparisons++;
                if (unsorted<=array[j])
                {
                    array[j+1]=array[j];
                }
                else
                {
                    break;
                }
            }
            array[j+1]=unsorted;
        }
        long end = System.nanoTime();
        time=(int)(end-start);
        int[] returned = new int[2];
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
    }
    public int[] shellSort()
    {
        int gap=size/2;
        long start = System.nanoTime();
        while (gap>=1)
        {
            for (int i=gap; i<size; i++)
            {
                int unsorted=array[i];
                int j;
                for (j=i-gap; j>=0; j=j-gap)
                {
                    comparisons++;
                    if (unsorted<array[j])
                    {
                        array[j+gap]=array[j];
                    }
                    else
                    {
                        break;
                    }
                }
                array[j+gap]=unsorted;
            }
            gap=gap/2;
        }
        long end = System.nanoTime();
        time=(int)(end-start);
        int[] returned = new int[2];
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
    }
    public int[] quickSort()
    {
        long startTime = System.nanoTime();
        quickSort(0, size-1);
        long endTime = System.nanoTime();
        time = (int)(endTime-startTime);
        int[] returned = new int[2];
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
    }
    private void quickSort(int start, int end)
    {
        if (start>=end || start<0 || start>=size || end<0 || end>=size){
            return;
        }
        int pivotIndex=start;
        int leftScanner=start;
        int rightScanner=end;
        int tmp;
        while(leftScanner<rightScanner)
        {
            while (array[leftScanner]<=array[pivotIndex] && leftScanner+1<=end)
            {
                comparisons++;
                leftScanner++;
            }
            while (array[rightScanner]>array[pivotIndex])
            {
                comparisons++;
                rightScanner--;
            }
            comparisons=comparisons+2;
            if (leftScanner<rightScanner){
                 tmp=array[leftScanner];
                array[leftScanner]=array[rightScanner];
                array[rightScanner]=tmp;
            }
        }
        tmp=array[pivotIndex];
        array[pivotIndex]=array[rightScanner];
        array[rightScanner]=tmp;
        quickSort(start,rightScanner-1);
        quickSort(rightScanner+1,end);
    }
    public int[] mergeSort()
    {
        long startTime = System.nanoTime();
        mergeSort(0,size-1);
        long endTime = System.nanoTime();
        time = (int)(endTime-startTime);
        int[] returned = new int[2];
        returned[0]=time;
        returned[1]=comparisons;
        return returned;
        
    }
    private void mergeSort(int start,int end)
    {
        if (start==end)
        {
            return;
        }
        int middle=start+(end-start)/2;
        mergeSort(start,middle);
        mergeSort(middle+1,end);
        merge(start,middle,end);
    }
    private void merge(int start, int middle, int end)
    {
        int [] a1 = new int[middle-start+1];
        int [] a2 = new int[end - middle];
        for (int i=0; i<a1.length; i++)
        {
            a1[i]=array[start+i];
        }
        for (int j=0; j<a2.length; j++)
        {
            a2[j]=array[middle+1+j];
        }
        int m1=0;
        int m2=0;
        int i;
        for (i=start; i<=end && m1<a1.length && m2<a2.length; i++)
        {
            comparisons++;
            if (a1[m1]<=a2[m2])
            {
                array[i]=a1[m1];
                m1++;
            }
            else
            {
                array[i]=a2[m2];
                m2++;
            }
        }
        while (m1<a1.length)
        {
            comparisons++;
            array[i]=a1[m1];
            i++;
            m1++;
        }
        while (m2<a2.length)
        {
            comparisons++;
            array[i]=a2[m2];
            i++;
            m2++;
        }
    }
}

public class ArraySort 
{
  private int comparisons = 0;
  private int swaps = 0;
  private int iterations = 0;
  private int[] numbers;
  
  public ArraySort(int[] array)
  { 
    numbers = array;
    reset();
  }
  
  public void setValue(int[] array)
  {
    reset();
    numbers = array;
  }
  
  public int[] bubbleSort(boolean efficient)
  {
    boolean sorted = false;
    do
    {
      sorted = true;
      for(int i = 0; i <= numbers.length - (iterations + 2); i++)
      {
        if(numbers[i] < numbers[i+1])
        {
          sorted = false;
          swap(i, i + 1);
          swaps++;
        }
        comparisons++;
      }
      if(efficient)
      {
        iterations++;
      }
    }
    while(!sorted);
    return numbers;
  }
  
  public int[] insertionSort()
  {
    int element = 0;
    int j = 0;
    for(int i = 1; i < numbers.length; i++)
    {
      element = numbers[i];
      j = i;
      while(j > 0 && numbers[j - 1] > element)
      {
        comparisons++;
        numbers[j] = numbers[j - 1];
        j -= 1;
      }
      if(j != 0)
      {
        comparisons++;
      }
      numbers[j] = element;
      swaps++;
    }
    return numbers;
  }
  
  public int[] selectionSort()
  {
    int min;
    reset();
    for(int i = 0; i <= numbers.length - 1; i++)
    {
      min = i;
      for(int j = i+1; j <= numbers.length - 1; j++)
      {
        if(numbers[j] < numbers[min])
        {
          min = j;
        }
        comparisons++;
      }
      if(min != i)
      {
        swap(min, i);
        swaps++;
      }
    }
    return numbers;
  }
  
  public int[] mergeSort()
  {
    reset();
    return mergeSort(numbers);
  }
  
  private int[] mergeSort(int[] arr)
  {
    int length = arr.length;
    if(length <= 1)
    {
      return arr;
    }
    int[] left = new int[length / 2];
    int[] right = new int[length - length / 2];
    for(int i = 0; i <= left.length - 1; i++)
    {
      left[i] = arr[i]; // initialize left array
    }
    for(int i = 0; i <= right.length - 1; i++)
    {
      right[i] = arr[i + length / 2]; // initialize right array
    }
    return merge(mergeSort(left), mergeSort(right));
  }
  
  private int[] merge(int[] left, int[] right)
  {
    int[] merged = new int[left.length + right.length];
    int i = 0; // index for left array
    int j = 0; // index for right array
    
    for(int k = 0; k <= merged.length - 1; k++)
    {
      if(i >= left.length) // we have exhausted the left list so add from the right
      {
        merged[k] = right[j++];
      }
      else if(j >= right.length) // we have exhausted the right list so add from the left
      {
        merged[k] = left[i++];
      }
      else if(left[i] <= right[j]) // left is smaller so add it
      {
        merged[k] = left[i++];
        comparisons++;
      }
      else // right is smaller so add it
      {
        merged[k] = right[j++];
        comparisons++;
      }
      swaps++;
    }
    return merged;
  }
  
  private void swap(int a, int b)
  {
    int placeHolder = 0;
    placeHolder = numbers[a];
    numbers[a] = numbers[b];
    numbers[b] = placeHolder;
  }
  
  public void getComparisons()
  {
    System.out.println(comparisons + " comparisons");
  }
  
  public void getSwaps()
  {
    System.out.println(swaps + " swaps");
  }
  
  public void getIterations()
  {
    System.out.println(iterations + " iterations");
  }
  
  private void reset()
  {
    comparisons = 0;
    swaps = 0;
  }
}

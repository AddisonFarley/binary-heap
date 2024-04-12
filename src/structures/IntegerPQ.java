package structures;

import java.util.Arrays;

public class IntegerPQ implements IPriorityQueue
{
    //min heap
    private static final int initialCapacity = 10;
    private int[] heap;
    private int size;

    public IntegerPQ()
    {
        heap = new int[initialCapacity];
    }

    @Override
    public void add(int element)
    {
        //check for available space
        if(size == heap.length)
        {
            resize();
        }

        //add element to next available index
        heap[size] = element;

        //swim up tree
        swim(size);
        size++;
    }

    private void swim(int index)
    {
        //continue until root is reached
        while(index > 0)
        {
            int parentIndex = (index - 1) / 2;

            //if out of order
            if(heap[index] < heap[parentIndex])
            {
                swap(index, parentIndex);

                //move up tree
                index = parentIndex;
            }
            //short circuit if in order
            else
            {
                break;
            }
        }
    }

    private void swap(int index1, int index2)
    {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void resize()
    {
        int[] newHeap = new int[heap.length * 2];

        //copy elements to the new heap
        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        heap = newHeap;
    }

    @Override
    public int remove()
    {
        emptyHeapException();

        //save old root to return
        int oldRoot = heap[0];

        //replace root with last occupied position in array
        heap[0] = heap[size - 1];
        heap[size - 1] = 0;

        //reduce heap size
        size--;

        //sink down tree
        sink(0);

        return oldRoot;
    }

    private void sink(int index)
    {
        int highestIndexWithChild = (size / 2) - 1;

        while(index <= highestIndexWithChild)
        {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;

            //compare to find smaller
            int smallestIndex = leftIndex;

            if(rightIndex < size && heap[rightIndex] < heap[leftIndex])
            {
                smallestIndex = rightIndex;
            }

            //swap if out of order
            if(heap[index] > heap[smallestIndex])
            {
                swap(index, smallestIndex);

                //move down to child element
                index = smallestIndex;
            }
            else
            {
                //short circuit
                break;
            }
        }
    }

    @Override
    public int peek()
    {
        emptyHeapException();

        return heap[0];
    }

    private void emptyHeapException()
    {
        if(size == 0)
        {
            throw new IllegalStateException("Heap is empty.");
        }
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(heap);
    }
}

package test;

import structures.IntegerPQ;

public class QueueTests
{
    public static void main(String[] args)
    {
        IntegerPQ queue = new IntegerPQ();
        int[] elementsToAdd = {11, 26, 1, 9, 88, 2};

        //add to queue
        for(int i: elementsToAdd)
        {
            queue.add(i);
        }

        System.out.println(queue);

        while(!queue.isEmpty())
        {
            int element = queue.remove();

            System.out.println(element);
        }
    }
}

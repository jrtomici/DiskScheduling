package diskscheduling;

import java.util.*;

public class DiskScheduling{

    public static void main(String[] args){
        int arraysize = 9;
        int[] blocks1 = {53, 98, 183, 37, 122, 14, 124, 65, 67};
        fcfs(blocks1, arraysize, blocks1[0]); System.out.println();
        int[] blocks2 = {53, 98, 183, 37, 122, 14, 124, 65, 67};
        sstf(blocks2, arraysize, blocks2[0]); System.out.println();
        int[] blocks3 = {53, 98, 183, 37, 122, 14, 124, 65, 67};
        scan(blocks3, arraysize, blocks3[0]); System.out.println();
        int[] blocks4 = {53, 98, 183, 37, 122, 14, 124, 65, 67};
        cscan(blocks4, arraysize, blocks4[0]);
    }
    
    static void fcfs(int[] blocks, int arraysize, int current){
        System.out.println("First Come First Serve:");
        int movement = 0;
        for (int i = 0; i < arraysize; i++){
            System.out.print(blocks[i] + " ");
            if (i != arraysize - 1)
                movement += Math.abs(blocks[i] - blocks[i + 1]);
        }
        System.out.println();
        System.out.println("Total head movement: " + movement);
    }
    
    static void sstf(int[] blocks, int arraysize, int current){
        System.out.println("Shortest Seek Time First:");
        int movement = 0, seektime = 200, next = 0;
        System.out.print(blocks[0] + " ");
        for (int i = 0; i < arraysize - 1; i++){
            for (int j = 0; j < arraysize; j++){
                if (blocks[i] != blocks[j]){
                    //find the shortest possible seek time
                    //keep track of index of block with shortest seek time
                    if (Math.abs(blocks[i] - blocks[j]) < seektime){
                        seektime = Math.abs(blocks[i] - blocks[j]);
                        next = j;
                    }
                }
            }
            if (i != arraysize - 1)
                movement += Math.abs(blocks[i] - blocks[next]);
            System.out.print(blocks[next] + " ");
            //"cross out" selected block
            blocks[i] = seektime = 200;
            //swap selected block with next block in the queue
            int temp = blocks[i + 1];
            blocks[i + 1] = blocks[next];
            blocks[next] = temp;
        }
        System.out.println();
        System.out.println("Total head movement: " + movement);
    }
    
    static void scan(int[] blocks, int arraysize, int current){
        System.out.println("SCAN:");
        int movement = 0, start = 0;
        Arrays.sort(blocks);
        //find head starting point
        for (int i = 0; i < arraysize; i++)
            if (blocks[i] == current)
                start = i;
        //start moving left
        for (int i = start; i >= 0; i--){
            System.out.print(blocks[i] + " ");
            if (i != 0)
                movement += Math.abs(blocks[i] - blocks[i - 1]);
        }
        //skip back to start and move right
        movement += Math.abs(blocks[0] - blocks[start + 1]);
        for (int i = start + 1; i < arraysize; i++){
            System.out.print(blocks[i] + " ");
            if (i != arraysize - 1)
                movement += Math.abs(blocks[i] - blocks[i + 1]);
        }
        System.out.println();
        System.out.println("Total head movement: " + movement);
        
    }
    
    static void cscan(int[] blocks, int arraysize, int current){
        System.out.println("C-SCAN:");
        int movement = 0, start = 0, max = 199;
        Arrays.sort(blocks);
        //find head starting point
        for (int i = 0; i < arraysize; i++)
            if (blocks[i] == current)
                start = i;
        //start moving right
        for (int i = start; i < arraysize; i++){
            System.out.print(blocks[i] + " ");
            if (i != arraysize - 1)
                movement += Math.abs(blocks[i] - blocks[i + 1]);
        }
        //go to zero and continue moving right
        movement += (max - blocks[arraysize - 1]) + (blocks[0]);
        for (int i = 0; i < start; i++){
            System.out.print(blocks[i] + " ");
            if (i != start - 1)
                movement += Math.abs(blocks[i] - blocks[i + 1]);
        }
        System.out.println();
        System.out.println("Total head movement: " + movement);
    }
}

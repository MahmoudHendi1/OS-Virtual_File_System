package PhysicalMemory;

import java.util.ArrayList;

public class PhysicalMemoryManager {
    private static int size;
    private AllocationStrategy strategy;
    public static boolean []bitVector;

    public PhysicalMemoryManager(int n, AllocationStrategy strategy) {
        size = n;
        bitVector = new boolean[size];
        this.strategy = strategy;
    }

    public static int getSize() {
        return size;
    }

    /*use Allocation Functions**/
    public ArrayList<Integer> allocateSpace(int sizeToAllocate){

        return strategy.allocate(sizeToAllocate);
    }
    public boolean deallocateSpace(ArrayList<Integer> allocatedBlocks){
        for (int allocatedBlock : allocatedBlocks) {
            if (bitVector[allocatedBlock] == false) return false;
            bitVector[allocatedBlock] = false;
        }
        return true;
    }
}

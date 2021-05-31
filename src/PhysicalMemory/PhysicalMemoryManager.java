package PhysicalMemory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class PhysicalMemoryManager implements Serializable {
    private static int size;
    public static AllocationStrategy strategy;
    public static boolean []bitVector;
    private static final long serialVersionUID = "PhysicalMemoryManager".hashCode();
    public PhysicalMemoryManager(int n, AllocationStrategy strategy) {
        size = n;
        bitVector = new boolean[size];
        this.strategy = strategy;
    }

    public static void setSize(int size) {
        PhysicalMemoryManager.size = size;
    }

    public static AllocationStrategy getStrategy() {
        return strategy;
    }

    public static void setStrategy(AllocationStrategy strategy) {
        PhysicalMemoryManager.strategy = strategy;
    }

    public static void setBitVector(boolean[] bitVector) {
        PhysicalMemoryManager.bitVector = bitVector;
    }

    public static int getSize() {
        return size;
    }

    public static boolean[] getBitVector() {
        return bitVector;
    }

    /*use Allocation Functions**/
    public ArrayList<Integer> allocateSpace(int sizeToAllocate){
        return strategy.allocate(sizeToAllocate);
    }
    public static void deallocateSpace(ArrayList<Integer> allocatedBlocks){
        for (int allocatedBlock : allocatedBlocks) {
            bitVector[allocatedBlock] = false;
        }
    }

    @Override
    public String toString() {
        return "PhysicalMemoryManager{" +
                "size=" + size +
                ", strategy=" + strategy +
                ", bitVector=" + Arrays.toString(bitVector) +
                '}';
    }
}

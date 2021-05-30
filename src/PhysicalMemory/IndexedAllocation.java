package PhysicalMemory;

import java.util.ArrayList;

public class IndexedAllocation implements AllocationStrategy {
    @Override
    public ArrayList<Integer> allocate(int size) {
        ArrayList<Integer> allocated = new ArrayList<>();
        int N = PhysicalMemoryManager.getSize();
        var bitVector = PhysicalMemoryManager.bitVector;
        for (int i = 0; i < N; ++i)
            if (!bitVector[i])
                allocated.add(i);
        if (allocated.size() < size + 1) return null; // extra space for the index
        for (var i : allocated)
            bitVector[i] = true;
        return allocated;
    }
}

package PhysicalMemory;

import java.util.ArrayList;

public class IndexedAllocation implements AllocatoinStrategy {
    @Override
    public ArrayList<Integer> Allocate(int size) {
        ArrayList<Integer> allocated = new ArrayList<>();
        int N = PhysicalMemeoryManger.getSize();
        var bitVector = PhysicalMemeoryManger.bitVector;
        for (int i = 0; i < N; ++i)
            if (!bitVector[i])
                allocated.add(i);
        if (allocated.size() < size + 1) return null; // extra space for the index
        for (var i : allocated)
            bitVector[i] = true;
        return allocated;
    }
}

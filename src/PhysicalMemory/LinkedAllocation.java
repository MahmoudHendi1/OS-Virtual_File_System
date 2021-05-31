package PhysicalMemory;

import java.io.Serializable;
import java.util.ArrayList;

public class LinkedAllocation implements AllocationStrategy, Serializable {
    private static final long serialVersionUID = "LinkedAllocation".hashCode();

    @Override
    public String toString() {
        return "LinkedAllocation{}";
    }

    @Override
    public ArrayList<Integer> allocate(int size) {
        ArrayList<Integer> allocated = new ArrayList<>();
        int N = PhysicalMemoryManager.getSize();
        var bitVector = PhysicalMemoryManager.bitVector;
        for (int i = 0; i < N; ++i) {
            if (allocated.size() >= size) break;
            if (!bitVector[i])
                allocated.add(i);
        }
        if (allocated.size() < size) return null;
        for(var i : allocated)
            bitVector[i] = true;
        return allocated;
    }
}

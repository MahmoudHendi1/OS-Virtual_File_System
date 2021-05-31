package PhysicalMemory;

import java.io.Serializable;
import java.util.ArrayList;

public class ContiguousAllocation implements AllocationStrategy , Serializable {

    private static final long serialVersionUID = "ContiguousAllocation".hashCode();

    @Override
    public String toString() {
        return "ContiguousAllocation{}";
    }

    @Override
    public ArrayList<Integer> allocate(int size) {
        int N = PhysicalMemoryManager.getSize();
        var bitVector = PhysicalMemoryManager.bitVector;
        int bstIdx = -1, bst = -1, lst = -1;
        for (int i = 0; i < N; ++i) {
            if (lst == -1 && bitVector[i])
                continue;
            if (!bitVector[i] && lst == -1)
                lst = i;
            if (!bitVector[i]) {
                if (bst < i - lst +1) {
                    bst = i - lst +1;
                    bstIdx = lst;
                }
            } else
                lst = -1;
        }
        if (bst < size) return null;
        ArrayList<Integer> allocated = new ArrayList<>();
        for (int i = bstIdx, j = 0; j < size; ++j, ++i)
            allocated.add(i);
        for(var i : allocated)
            bitVector[i] = true;
        return allocated;
    }
}

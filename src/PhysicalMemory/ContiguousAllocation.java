package PhysicalMemory;

import java.util.ArrayList;

public class ContiguousAllocation implements AllocatoinStrategy {
    @Override
    public ArrayList<Integer> Allocate(int size) {
        int N = PhysicalMemeoryManger.getSize();
        var bitVector = PhysicalMemeoryManger.bitVector;
        int bstIdx = -1, bst = -1, lst = -1;
        for (int i = 0; i < N - 1; ++i) {
            if (!bitVector[i] && lst == -1)
                lst = i;
            else if (!bitVector[i] && bitVector[i + 1]) {
                if (bst < i - lst) {
                    bst = i - lst;
                    bstIdx = i;
                }
            } else if (bitVector[i] && !bitVector[i + 1])
                lst = i + 1;

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

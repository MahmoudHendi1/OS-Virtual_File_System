package PhysicalMemory;

import java.util.ArrayList;

public interface AllocationStrategy {
    ArrayList<Integer> allocate(int size);
}

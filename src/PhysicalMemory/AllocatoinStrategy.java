package PhysicalMemory;

import java.util.ArrayList;

public interface AllocatoinStrategy {
    ArrayList<Integer> Allocate(int size);
}

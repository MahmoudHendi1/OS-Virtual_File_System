package PhysicalMemory;

public class PhysicalMemeoryManger {
    private static int size;
    public static boolean []bitVector;
    public PhysicalMemeoryManger(int n) {
        size = n;bitVector = new boolean[size];
    }

    public static int getSize() {
        return size;
    }

    /*use Allocation Functions**/
    boolean allocateSpace(int sizeToAllocate){

        return false;
    }
    boolean deallocateSpace(int sizeToDeallocate){

        return false;
    }
}

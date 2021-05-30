import PhysicalMemory.AllocationStrategy;
import PhysicalMemory.PhysicalMemoryManager;
import java.io.File;

public class VirtualFileSystem {
    File virtualFileSystemFile;
    Directory root;
    PhysicalMemoryManager manager;

    public VirtualFileSystem(String vfsPath, int diskSize, AllocationStrategy strategy) {
        this.virtualFileSystemFile = new File(vfsPath);
        /*Parse the file and load the file into the memory (yet to specify the data structure*/

        manager = new PhysicalMemoryManager(diskSize, strategy);
    }

    public VirtualFileSystem(int diskSize, AllocationStrategy strategy) {
        manager = new PhysicalMemoryManager(diskSize, strategy);
        root = new Directory("/", "root");
    }

    boolean createFile(String path, int size){

        return  false;
    }

    boolean createFolder(String path){
        return  false;

    }

    boolean deleteFile(String path){
        return  false;

    }

    boolean deleteFolder(String path){

        return  false;
    }

    void displayDiskStatus(){


    }

    void displayDiskStructure(){


    }



}

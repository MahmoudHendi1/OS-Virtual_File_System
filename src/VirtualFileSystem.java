import PhysicalMemory.AllocationStrategy;
import PhysicalMemory.PhysicalMemoryManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class VirtualFileSystem {
    File virtualFileSystemFile;
    Directory root;
    PhysicalMemoryManager manager;
    AllocationStrategy allocationStrategy;
    public VirtualFileSystem(String vfsPath, int diskSize, AllocationStrategy strategy) {
        this.virtualFileSystemFile = new File(vfsPath);
        /*Parse the file and load the file into the memory (yet to specify the data structure*/
        root = new Directory("/", "root");
        manager = new PhysicalMemoryManager(diskSize, strategy);
        this.allocationStrategy = strategy;
    }

    public VirtualFileSystem(int diskSize, AllocationStrategy strategy) {
        manager = new PhysicalMemoryManager(diskSize, strategy);
        root = new Directory("/", "root");
        this.allocationStrategy = strategy;
    }

    boolean createFile(String path, int size) {
        var dirs = path.split("/");
        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) return false;
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) return false;
        }
        //valid path
        var allocated = allocationStrategy.allocate(size);
        if (allocated == null) return false; //no space
        if (curDir.getFile(dirs[pos]) != null) return false; //already exits
        curDir.addFile(new MyFile(path, allocated));
        return true;


    }

    boolean createFolder(String path) {
        var dirs = path.split("/");
        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) return false;
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) return false;
        }
        if(curDir.getSubDirectory(dirs[pos])!=null) return false; // already exists
        curDir.addDirectory(new Directory(path, dirs[pos]));
        return true;
    }

    boolean deleteFile(String path) {
        return false;

    }

    boolean deleteFolder(String path) {

        return false;
    }

    void displayDiskStatus() {


    }

    void displayDiskStructure(){
        root.printDirectoryStructure(0);

    }



}

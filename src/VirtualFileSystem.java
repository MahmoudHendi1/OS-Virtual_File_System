import Persitience.FileDataStreamer;
import PhysicalMemory.AllocationStrategy;
import PhysicalMemory.PhysicalMemoryManager;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class VirtualFileSystem implements Serializable {
    Directory root;
    PhysicalMemoryManager manager;
    AllocationStrategy allocationStrategy;
    FileDataStreamer fds;
    String virtualFileSystemFilePath;
    private static final long serialVersionUID = "VirtualFileSystem".hashCode();

    public void setManager(PhysicalMemoryManager manager) {
        this.manager = manager;
    }

    public VirtualFileSystem(String vfsPath, int diskSize, AllocationStrategy strategy) {
        this.virtualFileSystemFilePath = vfsPath;
        fds = new FileDataStreamer();
        /*Parse the file and load the file into the memory (yet to specify the data structure*/
        root = new Directory(vfsPath,"root");
        manager = new PhysicalMemoryManager(diskSize, strategy);
        this.allocationStrategy = strategy;
    }
    public  VirtualFileSystem(VirtualFileSystem cpy){
        this.virtualFileSystemFilePath = cpy.virtualFileSystemFilePath;
        fds = cpy.fds;
        /*Parse the file and load the file into the memory (yet to specify the data structure*/
        root = cpy.root;
        manager = cpy.manager;
        System.out.println("disk size = " + PhysicalMemoryManager.getSize());
        this.allocationStrategy = cpy.allocationStrategy;
    }

    public VirtualFileSystem(int diskSize, AllocationStrategy strategy) {
        fds = new FileDataStreamer();
        manager = new PhysicalMemoryManager(diskSize, strategy);
        root = new Directory("/", "root");
        this.allocationStrategy = strategy;
    }

    boolean createFile(String path, int size) {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) {
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path!");
                return false;
            }
        }
        //valid path
        var allocated = allocationStrategy.allocate(size);
        if (allocated == null){ //no space
            System.out.println("Not enough space!");
            return false;
        }
        if (curDir.getFile(dirs[pos]) != null) {
            System.out.println("File already exists with this name!");
            return false;} //already exits
        curDir.addFile(new MyFile(path, allocated));


        displayDiskStructure();

        return true;


    }

    boolean createFolder(String path) {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")){
            System.out.println("Invalid path!");
            return false;}
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path");
                return false;}
        }
        if(curDir.getSubDirectory(dirs[pos])!=null) {
            System.out.println("A directory already exists with this name!");
            return false;} // already exists
        curDir.addDirectory(new Directory(path, dirs[pos]));

        displayDiskStructure();

        return true;
    }

    boolean deleteFile(String path) {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) {
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path!");
                return false;
            }
        }
        var toDel = curDir.getFile(dirs[dirs.length - 1]);
        if (toDel == null) {
            System.out.println("No such file or directory!");
            return false;
        } //not exits
        curDir.deleteFile(toDel);
        manager.deallocateSpace(toDel.getAllocatedBlocks());
        toDel.deleteFile();
        displayDiskStructure();
        return true;
    }

    boolean deleteFolder(String path) {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if(dirs.length == 1){
            System.out.println("Invalid Command");
            return false;
        }
        if (!dirs[0].equals("root")) {
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path!");
                return false;
            }
        }
        var toDel = curDir.getSubDirectory(dirs[pos]);
        if(toDel==null) {
            System.out.println("No such file or directory!");
            return false; //does not exits
        }
        toDel.deleteDirectory();
        curDir.deleteSubDirectory(toDel);
        displayDiskStructure();
        return true;
    }

    void displayDiskStatus() {


    }

    void displayDiskStructure(){
        root.printDirectoryStructure(0);
    }


    @Override
    public String toString() {
        return "VirtualFileSystem{" +
                ", root=" + root +
                ", manager=" + manager +
                ", allocationStrategy=" + allocationStrategy +
                '}';
    }
}

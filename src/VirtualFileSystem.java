import Persitience.FileDataStreamer;
import PhysicalMemory.AllocationStrategy;
import PhysicalMemory.PhysicalMemoryManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class VirtualFileSystem implements Serializable {
    User user;

    {
        try {
            user = UserSys.login("admin","admin",this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    boolean isDir(String path){
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")){
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;
        while (pos < dirs.length - 1) {
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path");
                return false;
            }
        }
        if(curDir.getSubDirectory(dirs[pos])!=null)
            return true;

        return false;
    }
    boolean createFile(String path, int size) throws IOException {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) {
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;
        StringBuilder path2= new StringBuilder(dirs[0]);

        while (pos < dirs.length - 1) {
            path2.append('/').append(dirs[pos]);
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path!");
                return false;
            }
        }
        if(user.role==0 &&UserSys.getGrants(user.name,path2.toString()).charAt(0)!='1'){
            System.out.println("You don't have the grant to create a folder");
            return false;
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

    boolean createFolder(String path) throws IOException {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")){
            System.out.println("Invalid path!");
            return false;}
        int pos = 1;
        StringBuilder path2= new StringBuilder(dirs[0]);

        while (pos < dirs.length - 1) {
            path2.append('/').append(dirs[pos]);
            curDir = curDir.getSubDirectory(dirs[pos++]);
            if (curDir == null) {
                System.out.println("Invalid path");
                return false;}
        }

        if(user.role==0 &&UserSys.getGrants(user.name,path2.toString()).charAt(0)!='1'){
            System.out.println("You don't have the grant to create a folder");
            return false;
        }
        if(curDir.getSubDirectory(dirs[pos])!=null) {
            System.out.println("A directory already exists with this name!");
            return false;
        } // already exists
        curDir.addDirectory(new Directory(path, dirs[pos]));

        displayDiskStructure();

        return true;
    }

    boolean deleteFile(String path) throws IOException {
        var dirs = path.split("/");
//        System.out.println(Arrays.toString(dirs));
        var curDir = root;
        if (!dirs[0].equals("root")) {
            System.out.println("Invalid path!");
            return false;
        }
        int pos = 1;

        StringBuilder path2= new StringBuilder(dirs[0]);

        while (pos < dirs.length - 1) {
            path2.append('/').append(dirs[pos]);
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

        if(user.role==0 &&UserSys.getGrants(user.name,path2.toString()).charAt(1)!='1'){
            System.out.println("You don't have the grant to delete this file");
            return false;
        }
        curDir.deleteFile(toDel);
        manager.deallocateSpace(toDel.getAllocatedBlocks());
        toDel.deleteFile();
        displayDiskStructure();
        return true;
    }

    boolean deleteFolder(String path) throws IOException {
        var dirs = path.split("/");
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
        StringBuilder path2= new StringBuilder(dirs[0]);

        while (pos < dirs.length - 1) {
            path2.append('/').append(dirs[pos]);
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
        if(user.role==0 &&UserSys.getGrants(user.name,path2.toString()).charAt(1)!='1'){
            System.out.println("You don't have the grant to delete this folder");
            return false;
        }
        toDel.deleteDirectory();
        curDir.deleteSubDirectory(toDel);
        displayDiskStructure();
        return true;
    }

    boolean login(String name , String pass) throws FileNotFoundException {
        User tmp=UserSys.login(name,pass,this);
        if(Objects.isNull(tmp)){
            System.out.println("Invalid User or Password");
            return false;
        }
        user=tmp;
        System.out.println("You are logged in, Welcome "+name+ "!");
        return true;

    }

    void displayDiskStatus() {
        var bitVector = PhysicalMemoryManager.getBitVector();
        int sz = bitVector.length;
        int cnt = 0;
        System.out.println("Used Blocks Are");
        for (int i = 0; i < sz; ++i) {
            if (bitVector[i]) {
                cnt++;
                System.out.print(i + " ,");
            }
        }
        System.out.println("\nTotal used Blocks  = " + cnt);
        System.out.println("Free Blocks Are:");
        cnt = 0;
        for (int i = 0; i < sz; ++i) {
            if (!bitVector[i]) {
                cnt++;
                System.out.print(i + " ,");
            }
        }
        System.out.println("\nTotal free Blocks  = " + cnt);
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

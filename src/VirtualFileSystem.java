import PhysicalMemory.PhysicalMemeoryManger;
import java.io.File;

public class VirtualFileSystem {
    File virtualFileSystemFile;
    Directory root;
    PhysicalMemeoryManger manager;

    public VirtualFileSystem(String vfsPath, int diskSize) {
        this.virtualFileSystemFile = new File(vfsPath);
        /*Parse the file and load the file into the memory (yet to specify the data structure*/

        manager = new PhysicalMemeoryManger(diskSize);
    }

    public VirtualFileSystem(int diskSize) {
        manager = new PhysicalMemeoryManger(diskSize);

    }

    boolean createFile(){
        return  false;

    }

    boolean createFolder(){
        return  false;

    }

    boolean deleteFile(){
        return  false;

    }

    boolean deleteFolder(){

        return  false;
    }

    void displayDiskStatus(){


    }

    void displayDiskStructure(){


    }



}

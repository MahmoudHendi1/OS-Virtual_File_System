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
        /***
         *
         * Checks if path exists, if no file exists with such name, if enough space exists
         *
         */
        return  false;

    }

    boolean createFolder(){
        /* Checks if path exists, if no file exists with such name, */
        return  false;

    }

    boolean deleteFile(){
        /*if  file exists with such name*/
        return  false;

    }

    boolean deleteFolder(){
        /*if  file exists with such name
        * :- delete all subfolders and subfiles*/

        return  false;
    }

    void displayDiskStatus(){
        /**show
         * 1-	Empty space
         * 2-	Allocated space
         * 3-	where are Empty Blocks in the Disk
         * 4-	where are Allocated  Blocks in the Disk*/

    }

    void displayDiskStructure(){
    /**
     * DisplayDiskStructure	This command will display the files
     * and folders in your system file in a tree structure */


    }



}

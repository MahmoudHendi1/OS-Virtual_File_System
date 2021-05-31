import PhysicalMemory.PhysicalMemoryManager;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Directory implements Serializable {
    private String directoryPath, name;
    private ArrayList<MyFile> myFiles;
    private ArrayList<Directory> subDirectories;
    private static final long serialVersionUID = "Directory".hashCode();
    public Directory(String directoryPath, String name) {
        this.directoryPath = directoryPath;
        this.name = name;
        myFiles = new ArrayList<>();
        subDirectories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private boolean deleted = false;

    public void addFile(MyFile file) {
        myFiles.add(file);
    }

    public MyFile getFile(String fileName) {
        for (var file : myFiles)
            if (file.getName().equals(fileName))
                return file;
        return null;
    }

    public void addDirectory(Directory dir) {
        subDirectories.add(dir);
    }

    public void deleteDirectory() {
        this.deleted = true;
        for (var file : myFiles) {
            file.deleteFile();
            PhysicalMemoryManager.deallocateSpace(file.getAllocatedBlocks());
        }
        myFiles.clear();
        for(var dir : subDirectories)
            dir.deleteDirectory();
        subDirectories.clear();
    }
    public void deleteFile(MyFile file){
        myFiles.remove(file);
    }
    Directory getSubDirectory(String Dir) {
        for (var dir : subDirectories)
            if (dir.getName().equals(Dir))
                return dir;
        return null;
    }


    public void printDirectoryStructure(int level) {

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < level; ++i)
            spaces.append('\t');

        System.out.println(spaces.toString() + '<'+name + '>');
        spaces.append('\t');
        for (var file : myFiles)
            System.out.println(spaces.toString() + file.getName());
        for (var dir : subDirectories)
            dir.printDirectoryStructure(level + 1);
    }

    public void deleteSubDirectory(Directory toDel) {
        subDirectories.remove(toDel);
    }

    @Override
    public String toString() {
        return "Directory{" +
                "directoryPath='" + directoryPath + '\'' +
                ", name='" + name + '\'' +
                ", myFiles=" + myFiles +
                ", subDirectories=" + subDirectories +
                ", deleted=" + deleted +
                '}';
    }
}

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Directory {
    private String directoryPath, name;
    private ArrayList<MyFile> myFiles;
    private ArrayList<Directory> subDirectories;

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
            spaces.append(' ');

        System.out.println(spaces.toString() + name);
        spaces.append(' ');
        for (var file : myFiles)
            System.out.println(spaces.toString() + file.getName());
        for (var dir : subDirectories)
            dir.printDirectoryStructure(level + 1);
    }
}

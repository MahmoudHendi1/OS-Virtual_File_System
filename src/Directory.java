import java.lang.reflect.Array;
import java.util.ArrayList;

public class Directory {
    private String directoryPath,name;
    private ArrayList<MyFile> myFiles;
    private ArrayList<Directory> subDirectories;

    public Directory(String directoryPath, String name) {
        this.directoryPath = directoryPath;
        this.name = name;
        myFiles = new ArrayList<>();
        subDirectories = new ArrayList<>();
    }

    private boolean deleted = false;

    public void addFile(MyFile file){
        myFiles.add(file);
    }

    public void addDirectory(Directory dir){
        subDirectories.add(dir);
    }

    public void deleteDirectory(){
        this.deleted = true;
    }


    public void printDirectoryStructure(int level) {

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < level; ++i)
            spaces.append(' ');

        System.out.println(spaces.toString()+name);
        spaces.append(' ');
        for (var file : myFiles)
            System.out.println(spaces.toString() + file.getName());
        for (var dir : subDirectories)
            dir.printDirectoryStructure(level + 1);
    }
}

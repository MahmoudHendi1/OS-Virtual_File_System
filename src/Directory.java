import java.util.ArrayList;
import java.util.Arrays;

public class Directory {
    private String directoryPath,name;
    private File[] files;
    private Directory[] subDirectories;
    private boolean deleted = false;


    public void printDirectoryStructure(int level) {

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < level; ++i)
            spaces.append(' ');

        System.out.println(spaces.toString()+name);
        spaces.append(' ');
        for (var file : files)
            System.out.println(spaces.toString() + file.getName());
        for (var dir : subDirectories)
            dir.printDirectoryStructure(level + 1);
    }
}

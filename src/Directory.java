public class Directory {
    private String directoryPath,name;
    private MyFile[] myFiles;
    private Directory[] subDirectories;

    public Directory() {
    }

    public Directory(String directoryPath, String name) {
        this.directoryPath = directoryPath;
        this.name = name;
    }

    private boolean deleted = false;


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

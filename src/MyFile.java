import java.io.Serializable;
import java.util.ArrayList;

class MyFile implements Serializable {
    private String filePath,name;
    private ArrayList<Integer> allocatedBlocks;
    private boolean deleted;
    private static final long serialVersionUID = "MyFile".hashCode();
    public MyFile(String filePath, ArrayList<Integer> allocatedBlocks) {
        this.filePath = filePath;
        this.allocatedBlocks = allocatedBlocks;
        this.deleted = false;
        var dirs = filePath.split("/");
        this.name = dirs[dirs.length - 1];
    }

    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void deleteFile(){
        deleted = true;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "filePath='" + filePath + '\'' +
                ", name='" + name + '\'' +
                ", allocatedBlocks=" + allocatedBlocks +
                ", deleted=" + deleted +
                '}';
    }
}

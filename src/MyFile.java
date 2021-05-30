import java.util.ArrayList;

class MyFile {
    private String filePath,name;
    private ArrayList<Integer> allocatedBlocks;
    private boolean deleted;

    public MyFile(String filePath, ArrayList<Integer> allocatedBlocks) {
        this.filePath = filePath;
        this.allocatedBlocks = allocatedBlocks;
        this.deleted =false;
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

}

class MyFile {
    private String filePath,name;
    private int[] allocatedBlocks;
    private boolean deleted;

    public MyFile(String filePath, int[] allocatedBlocks) {
        this.filePath = filePath;
        this.allocatedBlocks = allocatedBlocks;
        this.deleted =false;
    }
    public void delete(){
        deleted = true;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

}

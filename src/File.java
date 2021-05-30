class File {
    private String filePath,name;
    private int[] allocatedBlocks;
    private boolean deleted;

    public File(String filePath, int[] allocatedBlocks) {
        this.filePath = filePath;
        this.allocatedBlocks = allocatedBlocks;
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

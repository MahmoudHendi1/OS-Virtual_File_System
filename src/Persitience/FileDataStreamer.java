package Persitience;

import PhysicalMemory.ContiguousAllocation;
import PhysicalMemory.PhysicalMemoryManager;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Arrays;

public class FileDataStreamer implements DataStreamer,Serializable {
    private static final long serialVersionUID = "FileDataStreamer".hashCode();

    @Override
    public String toString() {
        return "FileDataStreamer{}";
    }

    static void writeToFile(Object t, String filePath) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(new File(filePath));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(t);
        objectOut.close();
        System.out.println("The Object is successfully written to a file");
    }

    static Object readFromFile(String filePath) {
        try {
            FileInputStream fi = new FileInputStream(new File(filePath));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Object ret = oi.readObject();
            oi.close();
            fi.close();
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Object obj, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            System.out.println("Saved!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object read(String filePath) {
        try {
            File file = new File(filePath);
            if (file.length() == 0 ){ //if file is empty
                return null;
            }
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);
            Object ret = oi.readObject();
            oi.close();
            fi.close();
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        PhysicalMemoryManager ps;
        DataStreamer ds = new FileDataStreamer();
        ps = (PhysicalMemoryManager) ds.read("lol.txt");



    }
}

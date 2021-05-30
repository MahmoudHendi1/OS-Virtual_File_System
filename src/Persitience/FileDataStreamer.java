package Persitience;

import java.io.*;
import java.util.Arrays;

public class FileDataStreamer implements DataStreamer {
    static void writeToFile(Object t, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(t);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

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

    public static void main(String[] args) {
       /* TestData t = new TestData(0);
        t.createChild();
        t.child.createChild();
       // writeToFile(t,"D:\\AOS\\test.txt");

        TestData t = (TestData) readFromFile("D:\\AOS\\test.txt");
        System.out.println(t.toString());
        */
        var x = "helloklsdmvclk;s".split("/");
        System.out.println(Arrays.toString(x));

    }
}

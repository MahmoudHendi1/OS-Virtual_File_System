import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class User implements Serializable {
    String name,pass;
    int role;
    VirtualFileSystem vfs;

    User(){

    }
    User(String _name,String _pass,VirtualFileSystem _vfs){
        name =_name;
        pass = _pass;
        vfs=_vfs;
        role=0;
    }

    public boolean grant(String name, String path, String grants) throws IOException {
        System.out.println("You are not the admin");
        return false;
    }
    public boolean CUser(String name, String pass) throws IOException {
        System.out.println("You are not the admin");
        return false;
    }


    public void tellUser(){
        System.out.println(name);
    }

}

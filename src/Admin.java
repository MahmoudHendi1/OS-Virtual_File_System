import java.io.*;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends User{

    Admin(String _name,String _pass, VirtualFileSystem _vfs){
        name =_name;
        pass = _pass;
        vfs=_vfs;
        role=1;
    }

    public boolean CUser(String name, String pass) throws IOException {
        if(Objects.nonNull(UserSys.isUser(name))){
            System.out.println("User Already Exist");
            return false;
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("user.txt", true), "UTF-8"));

        writer.append(name + " , " + pass + '\n');

        writer.close();
        System.out.println("User is created");
        return true;
    }

    public boolean grant(String name, String path, String grants) throws IOException {
        // Check if user and path exist
        if(Objects.isNull(UserSys.isUser(name))){
            System.out.println("There's no such a user.");
            return false;
        }
        if(!vfs.isDir(path)){
            System.out.println("There's no such a path.");
            return false;
        }

        // Check if grants is in the right format
        for(int i=0;i<2;i++)if( grants.length()!=2 && grants.charAt(i)!='0'&&grants.charAt(i)!='1') {
            System.out.println("Invalid Grants.");
            return false;

        }

        //load the current capabilities
        FileReader file =new FileReader("capabilities.txt");
        BufferedReader reader = new BufferedReader(file);
        String data;
        boolean added=true;
        StringBuilder S = new StringBuilder();
        while((data = reader.readLine()) != null){
            String[] cap = data.trim().split(",");

            if(cap[0].equals(path)){
                data = cap[0];
                for(int i=1;i<cap.length;i+=2){
                    data += ','+cap[i] + ',';
                    if(cap[i].equals(name)){
                        cap[i+1]=grants;
                        added=false;
                    }
                    data += cap[i+1];
                }
                if(added) S.append(','+name+','+grants);
                added=false;
            }

            S.append(data);
            S.append('\n');
        }
        if(added){
            S.append(path+','+name+','+grants+'\n');
        }
        file.close();

        BufferedWriter filebuf = new BufferedWriter(new FileWriter("capabilities.txt"));
        PrintWriter printout = new PrintWriter(filebuf);
        printout.print(S);
        printout.close();
        System.out.println("Grant " + name + " " + grants);
        return true;
    }
}

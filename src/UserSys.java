import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class UserSys {
    public static User isUser(String name) throws FileNotFoundException {
        Scanner sc=new Scanner(new File("user.txt"));
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] users = data.trim().split("\\s+");
            if(users[0].equals(name)){
                sc.close();
                return new User(name,users[2],null);
            }
        }
        sc.close();
        return null;
    }
    static int isParent(String par,String child){
        if(par.length()>=child.length()){
            return -1;
        }
        int i=0;
        for(;i<Math.min(par.length(),child.length());i++){
            if(par.charAt(i)!=child.charAt(i)){
                break;
            }
        }
        if(child.charAt(i)=='/'){
            return i;
        }
        return -1;
    }
    public static String getGrants(String name,String path) throws IOException {
        FileReader file =new FileReader("capabilities.txt");
        BufferedReader reader = new BufferedReader(file);
        String data;
        String ret=null;
        int mx=-1;
        while((data = reader.readLine()) != null){
            String[] cap = data.trim().split(",");

            if(cap[0].equals(path)){
                for(int i=1;i<cap.length;i+=2){
                    if(cap[i].equals(name)){
                        return cap[i+1];
                    }
                }
            }
            int tmp = isParent(cap[0],path);
            if(tmp>mx) {
                for(int i=1;i<cap.length;i+=2){
                    if(cap[i].equals(name)){
                        ret=cap[i+1];
                        mx=tmp;
                    }
                }
            }

        }
        if(ret!=null){
            return ret;
        }

        file.close();
        return "11";
    }
    public static User login(String name, String pass,VirtualFileSystem vfs) throws FileNotFoundException {
        if(name.equals("admin") && pass.equals("admin")) return new Admin(name,pass,vfs);
        User user = isUser(name);
        if(Objects.isNull(user) || !pass.equals(user.pass)){
            return null;
        }
        user.vfs=vfs;
        return user;
    }
}

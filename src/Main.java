import Persitience.DataStreamer;
import Persitience.FileDataStreamer;
import PhysicalMemory.AllocationStrategy;
import PhysicalMemory.ContiguousAllocation;
import PhysicalMemory.IndexedAllocation;
import PhysicalMemory.LinkedAllocation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataStreamer persist = new FileDataStreamer();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        System.out.println("Enter disk size (in KBs)"); // disk size is the number of blocks where each block is 1 KB in size
        int n;
        n = sc.nextInt();

        int choice = 1;
        do {
            System.out.println("Enter allocation method:\n1. Contiguous allocation\n2. Indexed allocation\n3. LinkedAllocation\n");
            choice = sc.nextInt();
        } while(choice < 1 || choice > 3);

        AllocationStrategy strategy = null;
        switch(choice){
            case 1:
                strategy = new ContiguousAllocation();
                break;
            case 2:
                strategy = new IndexedAllocation();
                break;
            case 3:
                strategy = new LinkedAllocation();
                break;
            default:
        }

        VirtualFileSystem vfs;


        System.out.println("Enter Disk structure file path.");
        String vfsPath; sc.nextLine(); ///If a file doesn't exist, it will be created by the program
        vfsPath = sc.nextLine();

        if (!vfsPath.equalsIgnoreCase("0")){
            vfs= new VirtualFileSystem(vfsPath, n, strategy);
        } else {
            vfs = new VirtualFileSystem(n, strategy);
        }

        System.out.println("Enter commands");
        String input; boolean quit = false;
        while(!quit){
            input = sc.nextLine();
            parser.parse(input);
            String line = parser.getCmd();
            switch (line) {
                case "createFile": {
                    // taking path and file size
                    if (parser.getArgument().size() == 2) {
                        vfs.createFile(parser.getArgument().get(0), Integer.parseInt(parser.getArgument().get(1)));
                    } else
                        System.out.println("invalid arguments");
                    break;
                }
                case "createFolder":
                    if (parser.getArgument().size() == 1)
                        vfs.createFolder(parser.getArgument().get(0));
                    else
                        System.out.println("invalid arguments");
                    break;
                case "deleteFile":
                    if (parser.getArgument().size() == 1)
                        vfs.deleteFile(parser.getArgument().get(0));
                    else
                        System.out.println("invalid arguments");

                    break;
                case "deleteFolder":
                    if (parser.getArgument().size() == 1)
                        vfs.deleteFolder(parser.getArgument().get(0));
                    else
                        System.out.println("invalid arguments");

                    break;
                case "displayDiskStatus":
                    vfs.displayDiskStatus();
                    break;
                case "displayDiskStructure":
                    vfs.displayDiskStructure();
                    break;
                case "quit":
                    quit = true;
                    break;
                default:
                    System.out.println("not a valid command!");
            }

        }

    }
}

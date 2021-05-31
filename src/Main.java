import Persitience.DataStreamer;
import Persitience.FileDataStreamer;
import PhysicalMemory.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DataStreamer persist = new FileDataStreamer();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        AllocationStrategy strategy = null;
        VirtualFileSystem vfs = null;
        boolean doneLoadin = true;
        // try load
//        try {
//           var tmp = (boolean[]) persist.read("Static.txt");
//            vfs = (VirtualFileSystem) persist.read("Data.txt");
//            PhysicalMemoryManager.setBitVector(tmp);
//            PhysicalMemoryManager.setSize(tmp.length);
//
//        } catch (Exception ex) {
//            System.out.println("Loading Failed!");
//            doneLoadin = false;
//        }
        String vfsPath = "";

        while(!vfsPath.equalsIgnoreCase("y") && !vfsPath.equalsIgnoreCase("n")) {
            System.out.println("Do you wish to load file structure from path? (y/n)");
            vfsPath = sc.nextLine();
        }

        if (vfsPath.equalsIgnoreCase("y")){
            vfsPath = "";
            while(!vfsPath.equalsIgnoreCase("y") && !vfsPath.equalsIgnoreCase("n")) {
                System.out.println("Use default path? (y/n)");
                vfsPath = sc.nextLine();
            }

            if (vfsPath.equalsIgnoreCase("y")){
                vfsPath = "./";
            }
            else {
                System.out.println("Enter Disk structure file path containing both static.txt & data.txt.");
                vfsPath = sc.nextLine(); ///If a file doesn't exist, it will be created by the program
            }

            try {
                var tmp = (boolean[]) persist.read(vfsPath + "\\static.txt");
                vfs = (VirtualFileSystem) persist.read(vfsPath + "\\data.txt");
                PhysicalMemoryManager.setBitVector(tmp);
                PhysicalMemoryManager.setSize(tmp.length);

            } catch (Exception ex) {
                System.out.println("Loading Failed!");
                doneLoadin = false;
            }

        }
        doneLoadin = vfs != null;

        if (!doneLoadin) {
            System.out.println("Enter disk size (in KBs)"); // disk size is the number of blocks where each block is 1 KB in size
            int n;
            n = Integer.parseInt(sc.nextLine());

            int choice = 1;
            do {
                System.out.println("Enter allocation method:\n1. Contiguous allocation\n2. Indexed allocation\n3. LinkedAllocation\n");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 || choice > 3);

            switch (choice) {
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

            vfs = new VirtualFileSystem(n, strategy);
        }

        System.out.println("Enter commands");
        String input; boolean quit = false;
        while(!quit){
            input = sc.nextLine();
            parser.parse(input);
            String line = parser.getCmd();
            if (line == null) line = "";
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
        persist.save(vfs, "data.txt");
        persist.save((PhysicalMemoryManager.getBitVector()), "static.txt");

    }


}


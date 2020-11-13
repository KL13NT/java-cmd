import java.util.ArrayList;
import java.util.Scanner;

/**
 * Terminal commands go here
 */

public class Terminal {
  // public void cp(String sourcePath, String destinationPath);

  // public void mv(String sourcePath, String destinationPath);

  // public void rm(String sourcePath);

  // public void pwd();

  // public void cat(String[] paths);

  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");
    help(new ArrayList<>());
   clear(new ArrayList<>());
    Scanner input = new Scanner(System.in);
    String User_input = input.nextLine();
    ArrayList<String> params = new ArrayList<String>();
    params.add(User_input);
    arggs(params);
  }

  public static void arggs(ArrayList<String> args) throws Exception {    
    if (args.size() < 1)
      throw new Exception("Must provide 1 parameter");
    switch (args.get(0)) {
      case "cd": {
        System.out.println("arg1: Path");
        break;
      }

      case "cat": {
        System.out.println("arg1: SourcePath");
        break;
      }

      case "more": {
        System.out.println("arg1: SourcePath");
        break;
      }

      case "mkdir": {
        System.out.println("arg1: SourcePath");
        break;
      }

      case "rmdir": {
        System.out.println("arg1: SourcePath");
        break;
      }

      case "mv": {
        System.out.println("arg1: SourcePath, arg2: DestinationPath");
        break;
      }

      case "rm": {
        System.out.println("arg1: SourcePath, arg2: DestinationPath");
        break;
      }

      case "cp": {
        System.out.println("arg1: SourcePath, arg2: DestinationPath");
        break;
      }

      default: {
        if (args.get(0).equalsIgnoreCase("date") || args.get(0).equalsIgnoreCase("help")
            || args.get(0).equalsIgnoreCase("pwd") || args.get(0).equalsIgnoreCase("clear")
            || args.get(0).equalsIgnoreCase("ls"))
          System.out.println("no parameters needed");
        else
          System.out.println("this arg doesnot exist");

      }
    }

  }

  public static void help(ArrayList<String> args) {
    System.out.println("args   :    List all command arguments");
    System.out.println("cat    :    read files from path and output it's content");
    System.out.println("cd     :    For change the directory");
    System.out.println("clear  :    move file to new directory");
    System.out.println("cp     :    copy file to specific destination");
    System.out.println("date   :    Current data/time");
    System.out.println("exit   :    Stop all");
    System.out.println("ls     :    show all files in specific directory");
    System.out.println("mkdir  :    create a new directory");
    System.out.println("more   :    Display output on screen at time");
    System.out.println("mv     :    move file to new directory");
    System.out.println("rm     :    remove the file(Delete)");
    System.out.println("rmdir  :    moves the directory");
    System.out.println("pwd    :    Display cureent directory");

  }



public static void clear(ArrayList<String> args) {

  System.out.println("\r\n".repeat(1000));
}

}
  

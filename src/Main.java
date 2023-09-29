import model.MerkleTree;

public class Main {



    // todo: Move everything to maven
    // todo: Make sh scripts for running the code via mvn:exec, and a mvn:compile & mvn:package
    // todo: Make an external folder which encompasses this and is "code"
    // todo: An allcode.x with all the code copied
    // todo: Zip it all up


    /*
     * A file named “run”, which should have execute permission and runs your code. Most likely this file
     * will be a bash shell script that invokes your sunlab-compiled executable. Note that it is necessary that
     * final compilation and final testing be done in the sunlab so that we do not have version or machine/OS
     * compatibility issues in testing your code. Your code will be run on a standard-issue student account
     * with no changes to any environment variables.
     *
     *
     * A file named “recompile”, which will recompile your code. It needs to have execute permission. This
     *   can work anyway you wish (e.g., a makefile).
     *
     *
     * A file named “allcode.X”, where X is the default file extension for your language (e.g., “.c” or “.java”).
     *  This one file needs to contain ALL your code. It does not have to compile properly if you are actually
     *  storing your code in multiple files. The sole purpose of this file is for sending code to Moss for similarity
     *  testing. Just “cat” all your code up into one file so we do not have to write a script to search out the
     *  code in each student’s directory structure for each possible language.
     *
     *
     *  A directory named “code” containing all other code or data you may need to make your run and
     *  recompile files work.
     *
     *
     * All contained in a zip file called <team>.zip, top level is the the above requirements
     */

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println(("Please provide a file in the arg 0."));
            System.exit(1);
            return;
        }

        String filePath = args[0];

        if(args[0].equals("GENERATE_TEST")) {
            // todo: generate test data
        }

        MerkleTree tree = MerkleTree.readFile(filePath);


// Good way of reading the file, however this example, if an error is encountered, bufferedreader will not close, better implementation at the MerkleTree class
//        Scanner scanner = new Scanner(System.in);
//
//        // Prompt user input for file path
//        System.out.print("Enter the file path: ");
//        String filePath = scanner.nextLine();
//        scanner.close();
//
//        try {
//            // Create a FileReader and BufferedReader
//            FileReader fileReader = new FileReader(filePath);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//
//            // Read and print from the file line by line
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // Close the readers
//            bufferedReader.close();
//            fileReader.close();
//        } catch (IOException e) {
//            System.err.println("Error reading the file: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
    }
}
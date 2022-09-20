import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
/**

The Blast Project
@author Thanh, Daniel, Donald
@version November 25th 2019


Description: This project reads DNA sequences from files and stores them to the database.
It also creates a hash map that stores each n-length substring in the DNA sequence.

 */


/**
 * This class reads in the input and prints the results.
 * @author Thanh, Daniel, Donald
 * @version November 26th 2019
 */
public class BlastApplication {

    public static void main(String[] args) {
        //region Parameters
        /*
         * Goals:
         *      + Query the user for the locations of:
         *          + Virus sequences file
         *          + Query sequences file
         *          + Minimum length of match
         *
         */
        //the file containing the dna data
        File dataFile = null;
        //the file containing the query data
        File queryFile = null;
        //prompt the user for both files
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //Sets the file chooser to the home directory
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text data", "txt", "fasta")); //TODO: Decide on exactly what file types are acceptable
        fileChooser.setDialogTitle("Select DNA Sequence File");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            dataFile = fileChooser.getSelectedFile();
        }
        fileChooser.setDialogTitle("Select Query Sequence File");
        result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            queryFile = fileChooser.getSelectedFile();
        }
        //get word length and significant match size
        Integer WordLength = ValidatedInputReader.getInteger("Put in a minimum match size", 7, "Integer Please");
        int n = WordLength; //TODO: Replace with user prompt.
        Integer SignificantMatchLength = ValidatedInputReader.getInteger("Put in a significant match size", 7, "Integer Please");
        int sML = SignificantMatchLength; //TODO: Replace with user prompt.
        //endregion
        //create objects and process files
        BlastProcessor blastProcessor = new BlastProcessor();
        blastProcessor.process(queryFile, dataFile, n, sML);
        //store and print the results
        List<MatchLocation> results = blastProcessor.getResults();
        List<DNASequence> dataSequences = blastProcessor.getDataSequences();
        int queryCount = blastProcessor.getQuerySequences().size();
   
        for (int i = 0; i < queryCount; i++) {
            int finalI = i;
            List<MatchLocation> filteredList = results.stream().filter(ml -> ml.getQueryLocation().getSequence() == finalI).collect(Collectors.toList());
            System.out.println("Query #" + i + ", Num Matches: " + filteredList.size());
            for (MatchLocation ml :
                    filteredList) {
                System.out.println(dataSequences.get(ml.getDNASequenceNumber()).getName()); //Name of the virus matched
                System.out.println("    " + "In Query: " + ml.getQueryLocation().getPosition());
                System.out.println("    " + "In Database: " + ml.getDNALocation());
                System.out.println("    " + "Length: " + ml.getLength());
            }
            System.out.println();
        }


    }
}

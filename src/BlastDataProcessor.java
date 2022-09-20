import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * BlastDataProcessor contains methods to store the dna data sequences and the query sequences into lists and contains a method to get each n-length substring within a sequence.
 * @author Thanh, Daniel, Donald
 * @version November 25th 2019
 */

public class BlastDataProcessor {


    /**
     * Scans in data from list of dna sequences
     * Assumes list is formatted as:
     *  idString Name of Virus
     *  Sequence
     *  Blank Line
     *  repeat until finished.
     * @param data Input file
     * @return List of DNA sequences
     * @throws FileNotFoundException
     */
    public static List<DNASequence> processDNAData(File data) throws FileNotFoundException {
    	//reads in the dna data
        DNADataReader reader = new DNADataReader(data.getAbsolutePath());
        List<DNASequence> sequences = reader.readData();
        return sequences;
    }

    /**
     * Scans in data from list of dna sequences
     * Assumes list is formatted as:
     *  Sequence
     *  Blank Line
     *  repeat until finished.
     * @param data Input file
     * @return List of sequences to be queried
     * @throws FileNotFoundException
     */
    public static List<String> processQueryData(File data) throws FileNotFoundException {
    	//reads in the query data
        QueryReader reader = new QueryReader(data.getAbsolutePath());
        List< String> queries = reader.readQueries();
        return queries;
    }

    /**
     * allSubstrings returns a list of each n-length substring within the given sequence.
     * @param sequence  Sequence to be processed
     * @param n         Length of substrings
     * @return          All Substrings of length n within the sequence
     */
    public static List<String> allSubstrings(String sequence, int n){
    	//make sure the length is within bounds
        if (n <= 0){
            throw new IllegalArgumentException("Length of substrings must be greater than 0");
        }
        if (n > sequence.length()){
            throw new IllegalArgumentException("N cannot be greater than length of sequence");
        }
        //add each substring to a list and return it
        List<String> substrings = new ArrayList<>();

        for (int i = 0; i < sequence.length() - n; i++) {
            substrings.add(sequence.substring(i, i+n)); //Adds the substring from i to i+n
        }

        return substrings;
    }


}

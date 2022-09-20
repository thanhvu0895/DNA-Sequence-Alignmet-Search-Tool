import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * BlastProcessor processes the files into lists using the BlastDataProcessor class
 * and searches for and extends hits in the query sequences.
 * @author Thanh, Daniel, Donald
 * @version November 26th 2019
 */
public class BlastProcessor {
    //region Instance Variables
    //TODO: Replace object with structure for storing locations of objects.
    private HashMap<String, List<Location>> allSequences = new HashMap<>();
    private ArrayList<MatchLocation> results = new ArrayList<>();
    List<DNASequence> dataSequences;
    List<String > querySequences;

    //endregion

    /**
     * process processes the files into lists using the BlastDataProcessor class
     * and searches for and extends hits in the query sequences.
     * @param queryFile the given file containing the query sequences.
     * @param sequenceFile the file containing all the dna sequences.
     * @param n the length of matches to look for.
     */
    public void process(File queryFile, File sequenceFile, int n, int sML) {
        //region Indexing Virus Sequences
        /*
         * Goals:
         *      +
         *
         */
    	//attempt to store dna and query data
        try {
            dataSequences = BlastDataProcessor.processDNAData(sequenceFile);
            querySequences = BlastDataProcessor.processQueryData(queryFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        //put dna substrings of length n into the hash map
        for (int i = 0; i < dataSequences.size(); i++) {
            String seq = dataSequences.get(i).getSequence();
            List<String> substrings = BlastDataProcessor.allSubstrings(seq, n);
            for (int i1 = 0; i1 < substrings.size(); i1++) {

                String substring = substrings.get(i1);
                if (allSequences.containsKey(substring)) {
                    allSequences.get(substring).add(new Location(i, i1));
                } else {

                    allSequences.put(substring, new ArrayList<Location>());
                    allSequences.get(substring).add(new Location(i, i1));
                }
            }
        }
        //run through the list of queries
        for (int i = 0; i < querySequences.size(); i++) {

        	//for each substring of length n in this query
            for (int k = 0; k < querySequences.get(i).length() - n; k++) {
            	//create match info object
                MatchLocation matchLocation;
                //pointer for current substring
                String substring = querySequences.get(i).substring(k, k + n);
       
                //extend the hit
                //get list of locations in the hash map for this substring
                List<Location> locations = allSequences.get(substring);
                //make sure the list isn't empty
                if (locations != null) {
                	//for each location
                    for (int j = 0; j < locations.size(); j++) {
                    	//initialize empty match info object
                        matchLocation = new MatchLocation(0, new Location(0, 0), new Location(0, 0), "");
                        //get a pointer for the current location
                        Location currentLoc = locations.get(j);
                        //get a pointer for the sequence this location is pointing to
                        String dataString = dataSequences.get(currentLoc.getSequence()).getSequence();
                        //pointer for the starting position in the sequence
                        Integer dataPosition = currentLoc.getPosition();
                        //the ending position in the sequence
                        Integer dataEndPosition = dataPosition + n;
                        //the ending position in the query
                        Integer newInt = k + n; 
                        //try to extend this hit
                        while (newInt < querySequences.get(i).length() && dataEndPosition < dataString.length() && dataString.substring(dataPosition, dataEndPosition + 1).equals(querySequences.get(i).substring(k, newInt + 1))) {
                            newInt++;
                            dataEndPosition++;
                        }
                        //create matchlocation object for this extension
                        MatchLocation newMatchLocation = new MatchLocation(newInt - k, locations.get(j), new Location(i, k), querySequences.get(i).substring(k, newInt));
                        //replace empty matchlocation objecy
                        if (newMatchLocation.getLength() > matchLocation.getLength()) {
                            matchLocation = newMatchLocation;
                        }
                        //check if the match is significant and if it's not part of a longer match already being saved
                        MatchLocation finalMatchLocation = matchLocation;
                        if (matchLocation.getLength() >= sML &&
                                results.stream().noneMatch(ml -> (ml.getDNASequenceNumber() == finalMatchLocation.getDNASequenceNumber() &&
                                        ml.getDNALocation().getPosition() + ml.getLength() == finalMatchLocation.getDNALocation().getPosition() + finalMatchLocation.getLength()

                                )))
                        {
                        	//save the match
                            results.add(matchLocation);
                        }
                    }


                }


            }

        }

    }
    //endregion
    //region

    /**
     * Returns the results of processing the queries
     *
     * @return the list of match info objects
     */
    public ArrayList<MatchLocation> getResults() {
        return results;
    }

    /**
     *
     * @return the list of dna data sequences
     */
    public List<DNASequence> getDataSequences() {
        return dataSequences;
    }

    /**
     *
     * @return the list of query sequences
     */
    public List<String> getQuerySequences() {
        return querySequences;
    }
}

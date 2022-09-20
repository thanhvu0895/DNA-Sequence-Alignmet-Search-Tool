/**
 *  MatchLocation objects contain info about a match.
 *  @author Thanh, Daniel, Donald
 *  @version November 25th 2019
 */
public class MatchLocation {
    private int length;
    private Location DNALocation;
    private Location queryLocation;
    private String content;

    /**
     * Constructor
     * @param n Length of the match
     * @param DNALocation the location of the match within the dna database
     * @param queryLocation the location of the match within the query sequence
     * @param content the matched substring
     */
    MatchLocation(
            int n,
            Location DNALocation,
            Location queryLocation,
            String content
    ){
        length = n;
        this.DNALocation = DNALocation;
        this.queryLocation = queryLocation;
        this.content = content;
    }

    /**
     * extend extends the match by the given character
     * @param ch the given character to add onto the match
     */
    public void extend(char ch){
        length++;
        content = content + ch;
    }

    /**
     *
     * @return length of match
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @return location of match in dna database
     */
    public Location getDNALocation() {
        return DNALocation;
    }

    /**
     *
     * @return location of match in query sequence
     */
    public Location getQueryLocation() {
        return queryLocation;
    }

    /**
     *
     * @return matched substring
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @return index of the dna sequence containing the match
     */
    public int getDNASequenceNumber(){
        return DNALocation.getSequence();
    }

    /**
     *
     * @return  index of the query sequence containing the match
     */
    public int getQuerySequenceNumber(){
        return queryLocation.getSequence();
    }

    /**
     *
     * @return string representation of this info object
     */
    public String toString() {
        return "There was a match length " + length + " at position: " + DNALocation+ "\n" + content;
    }
}

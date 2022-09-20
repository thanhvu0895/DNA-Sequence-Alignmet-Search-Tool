/**
 * This class represents a pairing of a sequence number and a position within that sequence.
 * @author Thanh, Daniel, Donald
 * @version November 25th 2019
 */
public class Location {
    private Integer sequence;
    private Integer position;

    /**
     * Constructor
     * @param sequence index of the dna sequence
     * @param position the starting position within the dna sequence
     */
    Location(Integer sequence, Integer position){
        this.sequence = sequence;
        this.position = position;
    }

    /**
     *
     * @return Integer object that represents the index of the dna sequence
     */
    public Integer getSequence() {
        return sequence;
    }
    /**
     *
     * @return Integer object that represents the starting position within the dna sequence
     */
    public Integer getPosition() {
        return position;
    }

    /**
     *
     * @return String representation of this location
     */
    public String toString() {
        return "<" + sequence + ", " + position + ">";
    }

}

/**
 *  This class represents a DNA sequence -
 *  it contains the GI, the description, and the (partial) DNA sequence
 *  @author Thanh, Daniel, Donald
 *  @version November 25th 2019
 */
public class DNASequence {
    private String id;
    private String name;
    private String sequence;

    /**
     * Constructor
     * @param id description of the sequence
     * @param name the name of the sequence
     * @param sequence the dna sequence
     */
    DNASequence(String id, String name, String sequence){
        this.id = id;
        this.name = name;
        this.sequence = sequence;
    }

    /**
     *
     * @return description of the sequence
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return name of the sequence
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the dna sequence
     */
    public String getSequence() {
        return sequence;
    }

    /**
     *
     * @return String representation of this object
     */
    public String toString(){
        return sequence;
    }

}

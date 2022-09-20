// Class: DNADataReader
//
// Author: Pamela Cutter & Alyce Brady
//
// Created on May 23, 2006
// Modified November 15, 2006
// Modified November 19, 2006 to extend DNASeqReader
// Modified March 30, 2010 by Nathan Sprague
//    refactored and simplified.

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class contains code to read DNA sequence data in from
 * a file.  It must be put into an ArrayList as it is read.
 *
 * @author Pamela Cutter
 * @author Alyce Brady
 * @author Nathan Sprague
 * @version Mar 30, 2010
 *
 * @author Thanh, Daniel, Donald
 * @version November 25th 2019
 */
public class DNADataReader
{
    private String filename;
    private BufferedReader reader = null;

    /** Constructs an object that reads DNA sequence information
     *  from a file.  The data is assumed to be in the
     *  following format:
     *
     *      GenInfoIdentifier  A string describing the sequence
     *      a group of nucleotide blocks, spanning 1 or more lines
     *
     *   For example   the following would be a valid single sequence:
     *
     *      94717691 Campo. sono. ichnovirus segment W, complete sequence
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     */
    public DNADataReader(String filename)
    {
        // Create an object that can read from the file.
        try
        {
            this.reader = new BufferedReader(new FileReader(filename));
        }
        catch (IOException e)
        {
            System.err.println("Cannot open file "+ filename+" for reading");
        }

        this.filename = filename;
    }

    /** Reads DNA sequence information from the given file, which must be
     *  in the format specified in the documentation for the DNADataReader
     *  constructor.
     *  Precondition: the file must have been successfully opened for
     *                reading.
     */
    public ArrayList<DNASequence> readData()
    {
        ArrayList<DNASequence> data = new ArrayList<DNASequence>();

        try
        {
            // Read complete lines at a time, until end of file.
            String next;
            while ( (next = reader.readLine()) != null)
            {
                Debug.println(next);

                if ( ! next.trim().equals(""))
                    data.add(readInfoForOneSequence(next));
            }
        }
        catch (IOException e)
        {
            System.err.println("Could not read file " + filename);
            return null;
        }

        return  data;
    }

    /** Reads in the sequence information for a single DNA sequence in the file.
     *      @param firstLine    the first line of info for this sequence
     *      @throws IOException
     */
    private DNASequence readInfoForOneSequence(String firstLine) throws IOException
    {
        // Put the space-separated items from the line into an array.
        String[] items = firstLine.split(" ");

        // The first line should contain the GenInfo identifier (GI)
        // and sequence description.
        String gi = items[0];     // GenInfo identifier
        String desc = "";         // Description
        for (int i = 1; i < items.length; i++)
            desc += items[i] + " ";

        // Then read in the lines with the sequence information.
        String seq = readSequenceString();

        Debug.println("Just read " + seq);

        return new DNASequence(gi, desc, seq);
    }



    /** Reads in the sequence string for a single DNA sequence.  The sequence
     *  is assumed to be presented as groups of space-separated nucleotide
     *  blocks, spanning 1 or more lines.  For example, the following would
     *  be a valid single-line sequence:
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     *  The method reads until it reaches an empty line.
     *      @throws IOException
     */
    public String readSequenceString() throws IOException
    {
        String seq="";      // Will contain complete nucleotide sequence
        String nextLine;

        //stop when we read a null line, or a line with only white-space.
        while (!((nextLine = this.reader.readLine()) == null) &&
                ! nextLine.trim().equals(""))
        {
            // Read a line and find the individual items.
            String[] items = nextLine.split(" ");

            // Concatenate all the blocks on this line.
            for (int i = 0; i< items.length; i++)
                seq += items[i];

        }

        return seq;
    }


}


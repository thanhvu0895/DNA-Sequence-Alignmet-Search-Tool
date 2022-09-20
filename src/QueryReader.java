// Class: QueryReader
//
// Author: Pamela Cutter & Alyce Brady
//
// Created November 19, 2006
// Modified November 15, 2009 Nathan Sprague, Changed query file format
// Modified May 25, 2010  Nathan Sprague, refactored

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains code to read DNA sequence queries from
 * a file.
 * 
 * @author Alyce Brady
 * @author Pamela Cutter
 * @version Nov 19, 2006
 *
 *
 */
public class QueryReader extends DNADataReader
{
    private String filename;

    /** Constructs an object that reads DNA sequence information
     *  from the given file.  The file may contain any number of 
     *  sequences.  Sequences should be separated by a single 
     *  empty line.
     *    
  
     *  For example, the following would specify two valid queries:
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     *       
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     */
    public QueryReader(String filename) 
    {
        super(filename);
        this.filename = filename;
    }

    /** Reads queries from the given file, which must be in the format
     *  specified in the documentation for the QueryReader constructor.
     *  Precondition: the file must have been successfully opened for
     *                reading.
     */
    public List<String> readQueries()
    {
        List<String> queries = new ArrayList<String>();
       
        try 
        {
            // Read a line containing the number of subsequent lines
        	// for the query, then read those line, until End-Of-File.
        	String seq;
        	while (true)
        	{

        		seq = readSequenceString();
        		if (!seq.equals(""))
        			queries.add(seq);
        		else 
        			break;

        	} 
        } 
        catch (IOException e) 
        {
            System.err.println("Error reading "+ filename);
            return null;
        }

        return queries;
    }
}

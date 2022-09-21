# DNA Sequence Alignmet Search Tool

http://www.cs.kzoo.edu/cs210/projects/DNASequenceMatching/BLASTppWithDesign.html  

This is a DNA Analysis Utility that performs a simple version of BLAST algorithm to find regions of local similarity between DNA sequences, built on Java.  
![BLAST](https://user-images.githubusercontent.com/75138396/191390339-b5be3ad4-1a0f-47dc-a17d-7a5aab4b3f07.png)

### Contributors:
Thanh, Daniel, Donald  

### BACKGROUND

DNA analysis is a subject that is in the news almost every day, whether it be a new advance in medical research, a criminal trial, or some other application. Individuals are identified by their DNA sequence. Individuals have been exonerated from crimes that they have been accused of, and even, in some cases, exonerated from crimes they have served prison time for committing, based on DNA evidence. We have a new tool for Forensic (Legal) Investigation that was not widely available to law enforcement officials and legal experts as recently as 20 years ago.  

### Project Description

We are using a real data set and will implement the BLAST program on a smaller scale, looking for exact matches among sequences. We are using virus data obtained from NCBI (National Center for Biotechnology Information). Each virus DNA sequence we will use contains just the first 1020 nucleotides in the sequence. Many of these sequences are much longer than that; several were shorter, and have been padded with x's so that they are all the same length.
This project reads DNA sequences from files and stores them to the database. It also creates a hash map that stores each n-length substring in the DNA sequence.

- DNASequence: This class represents a DNA sequence - it contains the GI, the description, and the (partial) DNA sequence.
- Location: This class represents a pairing of a sequence number and a position within that sequence.
- Database: This class contains the DNA sequence data, it contains the HashMap that holds all possible n-letter words contained in this DNA data, it has a method to find matches of substrings of a query sequence, a method to extend those matches, and possibly a method to display the matches.
- MatchElement: This class stores information for each match longer than a given threshold.
- BLASTMain: This class contains the main method which creates and runs the application.
- BLASTApp: This class gets the DNA data and the query data from the data readers. It has a run method that iterates through each query and displays any matches with substrings of the DNA data that are longer than a given threshold.
- DNADataReader: This class reads the DNA sequence data from a file, and puts it into an appropriate data structure.
- QueryReader: This class reads the query data from a file, and puts it into an appropriate data structure.


### Usage  

Make sure you have Java environment, Run BlastApplication and you will be prompted to select test Data file (.txt) that contains ADN sequence and a match size (by default: 7) implement Blast Algorithm on these samples. Results will be printed on console log.
 
### Sample Result:

DNA DATA:  
![image](https://user-images.githubusercontent.com/75138396/191403555-f4de1f7d-9f98-488a-b3b1-90d49297ddfd.png)
  
Result from analyzing DNA data with match size = 7:  
![image](https://user-images.githubusercontent.com/75138396/191403009-1ebc16a9-7440-4f03-af60-5d413ba58edf.png)
  
### TODO: 
Decide on exactly what file types are acceptable ❌




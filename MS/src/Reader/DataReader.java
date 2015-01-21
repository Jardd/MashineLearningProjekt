package Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.lang.String;

public class DataReader{

        /**
         * A simple method to read the entire content of OntoNote (pref: names) data into
         * a single String.
         *
         * @param file the input file
         * @return a {@link String} with the content of the OntoNote file
         */
        public static String readOntoNoteFileAsString(String file) {
                // start with an empty string
                String content = "";
                // read the content line-wise
                List<String> lines = readOntoNoteFileLineWise(file);
                // concatenate all strings
                for (String line : lines) {
                        content += line + " ";
                }
                // remove extra space at the end of the line
                content = content.trim();
                // adds start/end symbol
                content = "$" + content;
                content = content + "@";
                return content;
        }

        /**
         * A simple method to read the OntoNote data into a {@link List} of
         * {@link String} objects. Each element in the list is a line of
         * data from the source data file
         *
         * @param file the input file
         * @return a {@link List} where each element is a line from the input
         *      OntoNote file
         */
        public static List<String> readOntoNoteFileLineWise(String file) {
        		// init the list
                List<String> lines = new ArrayList<String>();
                // read the file
                try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                        // until we reach the end-of-file
                        while (bReader.ready()) {
                                // a single line
                                String line = bReader.readLine();
                                // clean-up
                                line = line.trim();
                                if (!line.isEmpty()) {
                                		// strip the enamex
                                		String line_stripped = line.replaceAll("<.*?>", "");
                                		// strip disfluencies
                                		String line_stripped2 = line_stripped.replaceAll("-LAB-disfluency-RAB-.*?-LAB-/disfluency-RAB-", "");
                                		// strip narrators
                                		if (line_stripped2.startsWith("[")){
                                			String line_stripped3 = line_stripped2.replaceAll("[.*?]", "");
                                			// store it in the list
                                			lines.add(line_stripped3);
                                		}else{
                                			// store it in the list
                                			lines.add(line_stripped2);
                                		}
                                }
                        }
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                }
                return lines;
        }
        /**
        * Is probably inefficient as hell, but should work just fine for testing purposes.
        * Because I am lazy and want to be able to dream of Betty tonight, i did it this way...
        * If anybody reads this (probably only Tobi otherwise Betty wouldn´t have asked what
        * my functions do) - with a bit more time and more incentives from Betty this code 
        * probably can be upgraded by myself.
        * 
        * Takes a {@link String} for two different OntoNote-Filetypes (name + parse),
        * excerpts the word-type of each word and stores it in one giant {@link List} of
        * {@Link String} Objects.
        * Hint => Every new sentence starts with a [Speaker]! (Except the first who got eaten?)
        */
        
        public static List<String> parseTheCorpus(String parseFile, String nameFile){
        	// READ ALL THE FILES!
        	String contents_parse = readOntoNoteFileAsString(parseFile);
        	String contents_name = readOntoNoteFileAsString(nameFile);
        	String[] contents_parse_split = contents_parse.split(" ");
    		String[] contents_name_split = contents_name.split(" ");
    		// init the returned List
			List<String> lines = new ArrayList<String>();
			// counters to prevent work
			int counter = 0;
			// for every word in the name_file
    		for( int i = 0; i < contents_name_split.length;i++){
    			// search the parsed version (with a reminder where we stopped last time)
    			for( int a = counter; a < contents_parse_split.length; a++){
    				// for it´s twin!
    				String word = contents_parse_split[a].replaceAll("\\)","");
    				if( contents_name_split[i].equals(word)){
    					// Get the type from the parsed version
    					String type = contents_parse_split[a-1].substring(1);
    					// Put the info together
    					/**
    					 * DUMMY TO FILL IN!
    					 */
    					if(type.equals("NNP") | type.equals("NN") | type.equals("NNS")){
    						lines.add(contents_name_split[i]+" - "+"NN");
    					}else if(type.equals("DT")){
    						lines.add(contents_name_split[i]+" - "+"DET");
    					}else{
    						lines.add(contents_name_split[i]+" - "+type);
    					}
    					/**
    					 * DUMMY TO FILL IN!
    					 */
    					// Mark the place, so we don´t search twice and leave
    					counter = a;
    					break;
    				}
    			}
    		}
    		// give back the info
    		return lines;
		}

        /**
         * Reads word-type + senses from an input OntoNote file and stores them into
         * a {@link ArrayList} whose items are {@Link Word} 
         *
         * @param file the input file
         * @return a {@link ArrayList} storing {@Link Word}
         */
        public static ArrayList<Lemma> readSenses(String file) {
                // init the Array to return
                ArrayList<Lemma> senses = new ArrayList<Lemma>();
                try (BufferedReader bReader = new BufferedReader(new FileReader(file)))  {
                        // until we reach the end-of-file
                        while (bReader.ready()) {
                                // a single line
                                String line = bReader.readLine();
                                // assumes each line has the
                                // format "longstuff sentence word_in_sentence lemma (placeholder) sense"
                                String[] splitLine = line.split("\\s+");
                                //System.out.println(splitLine.length);
                                // sanity check
                                /*if (splitLine.length != 5) {
                                        System.err.println("Invalid line: " + line);
                                        System.exit(1);
                                }*/
                                // create the word with every info except the placeholder
                                senses.add(new Lemma(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3], Integer.parseInt(splitLine[splitLine.length-1])));
                        }
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                }
                // Preach the Word!
                return senses;
        }
        
        /**
         * A simple method to read the OntoNote data into a {@link List} of
         * {@link String} objects. Each element in the list is a line of
         * data from the source data file
         *
         * @param file the input file
         * @return a {@link List} where each element is a line from the input
         *      OntoNote file
         */
        public static List<String> readOntoNote5(String file) {
        		// init the list
                List<String> lines = new ArrayList<String>();
                // read the file
                try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                        // until we reach the end-of-file
                        while (bReader.ready()) {
                                // a single line
                                String line = bReader.readLine();
                                // clean-up
                                line = line.trim();
                                if (!line.isEmpty()) {
                                		String[] line_split = line.split(" +");
                                		/**
                    					 * DUMMY TO FILL IN!
                    					 */
                                		String type = line_split[4];
                    					if(type.equals("NNP") | type.equals("NN") | type.equals("NNS")){
                    						lines.add(line_split[3] + " - " + "N");
                    					}else if(type.equals("DT")){
                    						lines.add(line_split[3] + " - " + "DET");
                    					}else if(type.equals("VBP")){
                    						lines.add(line_split[3] + " - " + "V");	
                    					}else{
                    						lines.add(line_split[3] + " - " +  type);
                    					}
                    					/**
                    					 * DUMMY TO FILL IN!
                    					 */

                                }
                        }
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                }
                return lines;
        }

        
        public static void main(String[] args) {

        	// YOU MIGHT WANT TO USE IT LIKE THIS.
        	
        	List<String> test2 = readOntoNote5("C:\\Users\\Earthhorn\\Desktop\\test2.txt");
        	System.out.println(Arrays.toString(test2.toArray()));
        }

}


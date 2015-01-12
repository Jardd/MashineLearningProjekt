package Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
         * Reads word-type + senses from an input OntoNote file and stores them into
         * a {@link ArrayList} whose items are {@Link Word} 
         *
         * @param file the input file
         * @return a {@link ArrayList} storing {@Link Word}
         */
        public static ArrayList<Word> readSenses(String file) {
                // init the Array to return
                ArrayList<Word> senses = new ArrayList<Word>();
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
                                senses.add(new Word(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3], Integer.parseInt(splitLine[splitLine.length-1])));
                        }
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                }
                // Preach the Word!
                return senses;
        }
}


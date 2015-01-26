package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.lang.String;

public class DataReader{

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
                                // checks content
                                if (!line.isEmpty()) {
                                		// cuts up stuff
                                		String[] line_split = line.split(" +");
                                		// standard row for lemma
                                		int lemma_row = 3;
                                		// check non-standard
                                		if (line_split[6].equals("-") != true){
                                			lemma_row = 6;
                                		}
                                		/**
                                		* DUMMY TO FILL IN!
                                		*/
                                		// checks the type
                                		String type = line_split[4];
                                		// changes types into common ones
                                		if(type.equals("NNP") | type.equals("NN") | type.equals("NNS")){
                                			lines.add(line_split[lemma_row] + " - " + "n");
                                		}else if(type.equals("DT")){
                                			lines.add(line_split[lemma_row] + " - " + "DET");
                                		}else if(type.equals("VBP")){
                    					lines.add(line_split[lemma_row] + " - " + "v");	
                                		}else{
                                			lines.add(line_split[lemma_row] + " - " +  type);
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

        public static List<String> readInventory_list(String file) {
    		// init the list
            List<String> lines = new ArrayList<String>();
            // read the file
            List<String> translator = new ArrayList<String>();
            try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                    // until we reach the end-of-file
                    while (bReader.ready()) {
                            // a single line
                            String line = bReader.readLine();
                            // clean-up
                            line = line.trim();
                            // check content
                            if (!line.isEmpty()) {
                            	// search for information
                            	if (line.startsWith("<sense group=")){
                            		String[] sense_line = line.split(" ");
                            		translator.add(sense_line[2].substring(3,4));
                            	// search for information part II
                            	}else if(line.startsWith("<wn version=")){
                            		String translate_line = line.replaceAll("<.*?>","");
                            		translator.add(translate_line);
                            	}
                            }
                    }
            } catch (IOException ioe) {
                    ioe.printStackTrace();
            }
            // process information
            for (int i = 0; i < translator.size(); i++){
            	if (i % 2 == 0) {
            		lines.add(translator.get(i)+ " = " + translator.get(i+1));
            	}
            }
            return lines;
    }
        
        public static HashMap<String,String> readInventory_map(String file) {
    		// init the list
            HashMap<String,String> lines = new HashMap<String,String>();
            // read the file
            List<String> translator = new ArrayList<String>();
            try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                    // until we reach the end-of-file
                    while (bReader.ready()) {
                            // a single line
                            String line = bReader.readLine();
                            // clean-up
                            line = line.trim();
                            //List<String> translator = new ArrayList<String>();
                            if (!line.isEmpty()) {
                            	// search info
                            	if (line.startsWith("<sense group=")){
                            		String[] sense_line = line.split(" ");
                            		translator.add(sense_line[2].substring(3,4));
                            	// search info II
                            	}else if(line.startsWith("<wn version=")){
                            		String translate_line = line.replaceAll("<.*?>","");
                            		translator.add(translate_line);
                            	}
                            }
                    }
            } catch (IOException ioe) {
                    ioe.printStackTrace();
            }
            // complete info
            for (int i = 0; i < translator.size(); i++){
            	if (i % 2 == 0) {
            		lines.put(translator.get(i), translator.get(i+1));
            	}
            }
            return lines;
    }
        
        
        public static void main(String[] args) {

        	// YOU MIGHT WANT TO USE THE NEW STUFF LIKE THIS.
//        	
        	List<String> test2 = readOntoNote5("Corpus\\test2.txt");
        	System.out.println(Arrays.toString(test2.toArray()));
//
//        	List<String> test4 = readInventory_list("C:\\Users\\Earthhorn\\Desktop\\sense-inventories\\abandon-v.xml");
//        	System.out.println(Arrays.toString(test4.toArray()));
//        	
//        	HashMap<String,String> test5 = readInventory_map("C:\\Users\\Earthhorn\\Desktop\\sense-inventories\\abandon-v.xml");
//        	System.out.println(test5);
        }

}


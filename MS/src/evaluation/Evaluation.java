package evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Evaluation {
	File gold;
	File auto;
	
	public Evaluation(String goldStandartPfad, String experimentPfad){
		gold=new File(goldStandartPfad);
		auto=new File(experimentPfad);	
		
		try {
			BufferedReader goldReader=new BufferedReader(new FileReader(gold));
			BufferedReader autoReader=new BufferedReader(new FileReader(gold));
			
			int linenumber=0;
			String line;
			while((line = goldReader.readLine()) != null){
				if(!line.startsWith("#") ||!line.startsWith("\n")){
					linenumber++;
					
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

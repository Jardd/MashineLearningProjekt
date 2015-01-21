package wordNet;

import java.io.IOException;

public class Manager {

	public static void main(String[] args) {
		
		PatternSeeker.seekSynsets("instrumentation", "noun");
		try {
			WorkingAlgorithm.getSenses("instrumentation", "n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


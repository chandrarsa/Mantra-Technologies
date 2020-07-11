package java2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadCSV {
	
	final private String fileLocaiton;
	final private List<BigDecimal> prices = new ArrayList<>();
	
	public ReadCSV(String fileLocation){
		this.fileLocaiton = fileLocation;
	}
	
	ReadMetaData readMetaData = (position, row, delimiter) -> {
		StringTokenizer tokenizer = new StringTokenizer(delimiter);
		int currentPosition =0;
		 while (tokenizer.hasMoreTokens()) {
			 if (currentPosition==position) {
				return new BigDecimal(tokenizer.nextToken());
			} else{
				currentPosition++;
			}
	     }
		return null;
	};
		
	private int getPricePosition(File file) throws IOException{
		Path filePath = Paths.get(file.getAbsolutePath());
		String firstLine = Files.lines(filePath).findFirst().get();
		StringTokenizer tokenizer = new StringTokenizer(" ");
		int counter = 0; 
		while (tokenizer.hasMoreElements()) {
			if (tokenizer.nextToken().equals("price")) {
				counter ++;
				return counter;
			} else {
				counter ++;
			}
		}
		return -1;
	}
	
	public void readPrices() throws IOException {

		File file = new File(fileLocaiton);
		int position = getPricePosition(file);

		if (file.isFile() && file.canRead()) {
			FileReader fileReader = null;
			 BufferedReader br = null;
			try {
				fileReader = new FileReader(file);
				 br = new BufferedReader(new FileReader(file));
			        br.readLine();
			        String line;
					while ((line = br.readLine()) != null) {
			            if (!line.isEmpty()) {
			            	prices.add(readMetaData.getPrice(position, line, " "));
			            }
			        }
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (fileReader.ready() && br.ready()) {
					fileReader.close();
					br.close();
				}
			}

		}
	}

	public List<BigDecimal> getPrices() {
		return prices;
	}
	
	
}

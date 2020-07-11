package java1;

public class Task implements Runnable{
	String fileLocation;
	FileReader fileReader;
	Integer counter;
	public Task(String fileLocation,Integer counter, FileReader fileReader){
		this.fileLocation = fileLocation;
		this.fileReader = fileReader;
		this.counter = counter;
	}
	@Override
	public void run() {
		fileReader.parseFile(fileLocation, counter);
	}
}

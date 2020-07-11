package java1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParsingFilenames {

	public void readFile() {
		// parsing file;
		FileReader fileParser = (locaiton, counter) -> {
			if (counter == 1) {
				System.out.println(locaiton + " " + Thread.currentThread().getName());
			}
		};

		Integer cores = Runtime.getRuntime().availableProcessors();
		ExecutorService service = null;
		if (cores.intValue() < 2) {
			service = Executors.newFixedThreadPool(cores);
		} else {
			service = Executors.newFixedThreadPool(2);
		}
		service.execute(new Task("location1",1, fileParser));
		service.execute(new Task("location2",2, fileParser));
		service.shutdown();
	}

	public static void main(String[] args) {
		new ParsingFilenames().readFile();
	}

}

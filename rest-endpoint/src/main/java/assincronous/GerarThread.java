package assincronous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GerarThread {	
	
	private static ExecutorService executor = Executors.newFixedThreadPool(20);
	
	public static void run(Runnable runnable) {
		executor.submit(runnable);
	}
}

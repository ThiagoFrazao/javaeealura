package lojas.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileUtils {
	
	private static final String SYSTEM_PATH = "/Alura/JavaEE/Livros/Capas/";
	
	public static String getPathImg(Part img) {
		String path = getSystemPath() + img.getSubmittedFileName();
		try {
			img.write(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return path;
	}
	
	public static void chargeOutputStream(Path path, OutputStream outputStream){
		try {
			//Input para carregar o arquivo para a memoria
			FileInputStream inputStream = new FileInputStream(path.toFile());
			//Channel para ler o arquivo a ser carregado
			ReadableByteChannel inputChannel  = Channels.newChannel(inputStream);
			//Channel para descarregar o arquivo carregado
			WritableByteChannel outputChannel = Channels.newChannel(outputStream);
			//Buffer para manter o arquivo em memoria temporariamente... o tamanho e customizavel
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
			
			try {
				//lendo o arquivo ate acabar...
				while(inputChannel.read(buffer) != -1){
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();					
				}
				inputChannel.close();
				outputChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}		
	}

	public static String getSystemPath() {
		return SYSTEM_PATH;
	}
}

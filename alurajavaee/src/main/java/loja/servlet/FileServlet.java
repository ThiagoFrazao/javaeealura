package loja.servlet;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lojas.utils.FileUtils;


@SuppressWarnings("serial")
@WebServlet("/file/*")
public class FileServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI().split("/file")[1];
		Path pathToFile = Paths.get(path);
		FileNameMap fileMap = URLConnection.getFileNameMap();
		String fileType = fileMap.getContentTypeFor("file:"+pathToFile);
		
		//Limpar o Response para alocar o arquivo
		resp.reset();
		//Colocando o tipo de arquivo a ser carregado no Response
		resp.setContentType(fileType);
		//O tamanho do arquivo a ser carregado
		resp.setHeader("Content-Lenght", String.valueOf(Files.size(pathToFile)));
		//O Local do arquivo a ser carregado
		resp.setHeader("Content-Disposition", "filename=\""+pathToFile.getFileName().toString()+ "\"");
		FileUtils.chargeOutputStream(pathToFile, resp.getOutputStream());
	}

}

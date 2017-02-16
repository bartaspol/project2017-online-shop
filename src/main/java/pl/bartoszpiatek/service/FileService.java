package pl.bartoszpiatek.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.bartoszpiatek.exceptions.InvalidFileException;
import pl.bartoszpiatek.model.dto.FileInfo;

@Service
public class FileService {
	
	@Value("${photo.file.extensions}")
	private String imageExtensions;
	
	private Random random = new Random();
	
	
	public FileInfo saveFile(MultipartFile file, String baseDir, String subDirPrefix, String filePrefix) throws InvalidFileException, IOException{
		int nFilename = random.nextInt(1000);
		
		String filename = String.format("%s%03d", filePrefix, nFilename);
		
		String extension = getFileExtension(file.getOriginalFilename());
		if(extension == null){
			throw new InvalidFileException("Nie ma rozszerzenia pliku...");
		}
		
		if(isImageExtension(extension) == false){
			throw new InvalidFileException("Nie jest to plik graficzny");
		}
		
		File subDirectory = makeSubdirectory(baseDir, subDirPrefix);
		Path filePath = Paths.get(subDirectory.getCanonicalPath(), filename + "." + extension);
		
		Files.deleteIfExists(filePath);
		
		Files.copy(file.getInputStream(), filePath);
		
		return new FileInfo(filename, extension, subDirectory.getName(), baseDir);
	}
	
	
	private String getFileExtension(String filename){
		int dotPosition = filename.lastIndexOf(".");
		
		if(dotPosition < 0){
			return null;
		}
		
		return filename.substring(dotPosition + 1).toLowerCase();
	}
	
	private boolean isImageExtension(String extension){
		
		String testExtension = extension.toLowerCase();
		for(String validExtension: imageExtensions.split(",")){
			if(testExtension.equals(validExtension)){
				return true;
			}
		}
		return false;
	}
	
	private File makeSubdirectory(String basePath, String prefix){
		int nDirectory = random.nextInt(1000);
		String sDirectory = String.format("%s%03d", prefix, nDirectory);
		
		File directory = new File(basePath, sDirectory);
		
		if(!directory.exists()){
			directory.mkdir();
		}
		
		return directory;
	}
}
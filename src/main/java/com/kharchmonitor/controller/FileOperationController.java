package com.kharchmonitor.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@RestController
@RequestMapping("/file")
public class FileOperationController {
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public void createFile(@RequestParam("file") MultipartFile uploadfile, @RequestParam("name") String name) throws IOException {
		InputStream inputStream =  new BufferedInputStream(uploadfile.getInputStream());
		System.out.println(uploadfile.getName());
		saveInputStream(inputStream, name);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{name:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> featchFile(@PathVariable String name) throws IOException {
		InputStreamResource file = getFile(name);
		return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + name)
                .body(file);
	}

	private InputStreamResource getFile(String fileName) throws IOException {
		GridFS gfsPhoto = getGS();
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		File output = new File("c:\\test\\outputFile.txt");
		imageForOutput.writeTo(output);
		return new InputStreamResource(new FileInputStream(output));
	}
	
	private void saveInputStream(InputStream  stream, String fileName) {
		try {
			GridFS gfsPhoto = getGS();
			GridFSInputFile gfsFile = gfsPhoto.createFile(stream);
			gfsFile.setFilename(fileName);
			gfsFile.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private GridFS getGS() throws UnknownHostException {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("file");
		GridFS gfsPhoto = new GridFS(db, "files");
		return gfsPhoto;
	}

}

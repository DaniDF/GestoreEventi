package it.Daniele.gestore.remote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import it.Daniele.gestore.settings.model.AppSettings;

public class RemoteControl {

	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings();		
		
		for(File x : appSettings.getPrefFiles()) {
			try {
				URL website = new URL((appSettings.getFilesRemoteSource() + x.toString().replace(appSettings.getFilesLocalSource(), "")));
				Path localDir = Paths.get(appSettings.getFilesLocalSource());
				
				if(Files.notExists(localDir)) Files.createDirectory(localDir);
				
				
				try(ReadableByteChannel rbc = Channels.newChannel(website.openStream());
	                FileOutputStream fos = new FileOutputStream(x)){
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				}
				
			}
			catch(IOException e) {
				System.err.println("Can't update some files\n" + e);
			}
		}

	}

}

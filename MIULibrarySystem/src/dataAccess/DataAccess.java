package dataAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DataAccess {
	public static final String OUTPUT_DIR;
	
	static {
		if (System.getProperty("os.name").contains("Mac")) {
			OUTPUT_DIR = System.getProperty("user.dir") + "/src/dataAccess/storage";
		} else {
			OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\dataAccess\\storage";
		}
	}
	
	static void saveToStorage(StorageType fileName,Object object) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, fileName.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(object);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType fileName) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, fileName.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
}

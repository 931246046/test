package storm_falcon.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileWriter {

	private File mFile = null;
	
	private BufferedWriter mWriter = null;

	private String lineSeparator = System.getProperty("line.separator");

	private int lineNum = 0;
	
	public boolean open(String strFilePath) {
		mFile = new File(strFilePath);
		if (!mFile.exists()) {
			mFile = FileHelper.createFile(strFilePath);
		}
		
		try {
			mWriter = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(mFile), "GBK"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean open(String strFilePath, String encoding) {
		mFile = new File(strFilePath);
		if (!mFile.exists()) {
			mFile = FileHelper.createFile(strFilePath);
		}
		
		try {
			mWriter = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(mFile), encoding));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setSeparator(String separator) {
		lineSeparator = separator;
	}
	
	public synchronized void writeLine(String line){
		try {
			mWriter.write(line);
			mWriter.write(lineSeparator);
			lineNum++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			mWriter.flush();
			mWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public <T> void writeAll(String strFilePath, String encoding, List<T> list) {
		open(strFilePath, encoding);
		for (T s : list) {
			writeLine(s.toString());
		}
		close();
	}

	public void writeAll(String strFilePath, List<String> list) {
		writeAll(strFilePath, "gbk", list);
	}
}

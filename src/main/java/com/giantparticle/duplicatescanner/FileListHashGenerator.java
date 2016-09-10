package com.giantparticle.duplicatescanner;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.giantparticle.duplicatescanner.cli.ICLIOptions;

public class FileListHashGenerator implements Runnable {

	private HashGenerator hashGenerator;
	private List<File> fileList;
	private int startIndex;
	private int endIndex;
	private HashMap<String, FileData> fileMap;
	private ICLIOptions options;
	
	public FileListHashGenerator() {
		hashGenerator = new HashGenerator();
	}

	/**
	 * Set the options for this generator
	 * 
	 * @param options
	 */
	public void setOptions(ICLIOptions options) {
		this.options = options;
	}
	
	public void setFileMap(HashMap<String, FileData> map) {
		fileMap = map;
	}

	public void setFileList(List<File> list) {
		setFileList(list, 0, list.size());
	}

	public void setFileList(List<File> list, int start, int end) {
		fileList = list;
		startIndex = start;
		endIndex = end;
	}

	@Override
	public void run() {
		for (int i = startIndex; i < endIndex; ++i) {
			File file = fileList.get(i);
			String md5 = hashGenerator.GenerateMD5String(file.getPath());
			if (md5 == null || md5.isEmpty())
				continue;

			// Print or no Print
			if (!this.options.isSilent()) {
				System.out.println(String.format("File: %s - %s", md5, file.getPath()));
			}

			synchronized (fileMap) {
				if (fileMap.containsKey(md5)) {
					fileMap.get(md5).FilePaths.add(file.getPath());
				} else {
					FileData data = new FileData();
					data.FilePaths.add(file.getPath());
					data.MD5Hash = md5;

					fileMap.put(md5, data);
				}
			}
		}
	}
}

/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Adds MD5 hashes to a given hash map based on a given file list.
 */
public class FileListHashGenerator implements Runnable {

	private HashGenerator hashGenerator;
	private List<File> fileList;
	private int startIndex;
	private int endIndex;
	private HashMap<String, FileData> fileMap;
	private ICLIOptions options;

	/**
	 * Empty constructor
	 */
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

	/**
	 * Sets the hash map where to add all the MD5 hashes
	 * 
	 * @param map MD5 hash map
	 */
	public void setFileMap(HashMap<String, FileData> map) {
		fileMap = map;
	}

	/**
	 * Set a list of files to calculate the MD5 hashes
	 * 
	 * @param list list of files
	 */
	public void setFileList(List<File> list) {
		setFileList(list, 0, list.size());
	}

	/**
	 * Set a list of files to calculate the MD5 hashes.
	 * 
	 * @param list list of files
	 * @param start starting index
	 * @param end end index
	 */
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

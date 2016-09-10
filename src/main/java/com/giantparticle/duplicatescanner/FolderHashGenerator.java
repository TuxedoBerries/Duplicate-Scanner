/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Generate MD5 hashes for all the files inside the source folder.
 */
public class FolderHashGenerator {

	private ICLIOptions options;

	/**
	 * Empty constructor
	 */
	public FolderHashGenerator() {
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
	 * Return a HashMap with an MD5 string hash as key and FileData as values
	 * for all the files inside the given folder.
	 * 
	 * @param source
	 *            Source folder where to scan all the files
	 * @return HashMap as MD5 string and File Data
	 */
	public HashMap<String, FileData> Generate(String source) {
		HashMap<String, FileData> map = new HashMap<>();
		List<File> fileList = getAllFiles(source);
		if (options.useThreads()) {
			List<Thread> threadList = new ArrayList<>();
			int start = 0;
			int delta = (fileList.size() / options.threadCount());
			// TODO: Fix Delta
			System.out.println(String.format("Threads: %d", options.threadCount()));
			for (int i = 0; i < options.threadCount(); ++i) {
				FileListHashGenerator generator = new FileListHashGenerator();
				generator.setOptions(options);
				int end = start + delta;
				if (i + 1 >= options.threadCount())
					end = fileList.size();
				generator.setFileList(fileList, start, end);
				generator.setFileMap(map);

				System.out.println(String.format("Thread[%d]: [%d - %d]", i, start, end));
				Thread thread = new Thread(generator);
				threadList.add(thread);
				start += delta;
			}

			// Start
			for (int i = 0; i < threadList.size(); ++i) {
				threadList.get(i).start();
			}

			try {
				for (int i = 0; i < threadList.size(); ++i) {
					threadList.get(i).join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			FileListHashGenerator generator = new FileListHashGenerator();
			generator.setOptions(options);
			generator.setFileList(fileList);
			generator.setFileMap(map);
			generator.run();
		}
		return map;
	}

	private List<File> getAllFiles(String source) {
		List<File> list = new ArrayList<>();
		addAllFiles(source, list);
		return list;
	}

	private void addAllFiles(String source, List<File> list) {
		File root = new File(source);
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; ++i) {
			File file = files[i];
			if (file.isFile()) {
				list.add(file);
			}
			if (file.isDirectory()) {
				addAllFiles(file.getPath(), list);
			}
		}
	}
}

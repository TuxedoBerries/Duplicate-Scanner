/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner;

import java.io.File;
import java.util.HashMap;

import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Generate MD5 hashes for all the files inside the source folder.
 */
public class FolderHashGenerator {

	private final HashGenerator hashGenerator;
	private ICLIOptions options;

	/**
	 * Empty constructor
	 */
	public FolderHashGenerator() {
		hashGenerator = new HashGenerator();
	}

	/**
	 * Set the options for this generator
	 * @param options
	 */
	public void setOptions(ICLIOptions options) {
		this.options = options;
	}

	/**
	 * Return a HashMap with an MD5 string hash as key and FileData as values for all the files inside the given folder.
	 * @param source Source folder where to scan all the files
	 * @return HashMap as MD5 string and File Data
	 */
	public HashMap<String, FileData> Generate(String source) {
		HashMap<String, FileData> map = new HashMap<>();
		AddFiles(source, map);
		return map;
	}

	private void AddFiles(String source, HashMap<String, FileData> map) {
		File root = new File(source);
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; ++i) {
			File file = files[i];
			if (file.isFile()) {
				String md5 = hashGenerator.GenerateMD5String(file.getPath());
				if (md5 == null || md5.isEmpty())
					continue;

				// Print or no Print
				if (!this.options.isSilent()) {
					System.out.println(String.format("File: %s - %s", md5, file.getPath()));
				}

				if (map.containsKey(md5)) {
					map.get(md5).FilePaths.add(file.getPath());
				} else {
					FileData data = new FileData();
					data.FilePaths.add(file.getPath());
					data.MD5Hash = md5;

					map.put(md5, data);
				}
			}
			if (file.isDirectory()) {
				AddFiles(file.getPath(), map);
			}
		}
	}
}

/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.processors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import com.giantparticle.duplicatescanner.FileData;
import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Move all the duplicated files into a given folder.
 */
public class MoveDuplicateProcessor extends BaseDuplicateProcessor {

	private boolean validDestination = false;

	@Override
	public void setOptions(ICLIOptions options) {
		super.setOptions(options);

		// Ensure Directory
		File destination = new File(options.getMoveDestination());
		if (destination.exists()) {
			if (!destination.isDirectory()) {
				System.err.println(String.format("Destination is not a valid folder %s", options.getMoveDestination()));
			} else {
				validDestination = true;
			}
		} else {
			validDestination = destination.mkdir();
		}
	};

	@Override
	protected void processFiles() {
		if (!validDestination)
			return;

		System.out.println("Duplicates");
		for (Map.Entry<String, FileData> entry : files.entrySet()) {
			FileData value = entry.getValue();
			if (value.FilePaths.size() > 1) {
				System.out.println(String.format("%s", value.MD5Hash));
				for (int i = 0; i < value.FilePaths.size(); ++i) {
					String source = value.FilePaths.get(i);
					Path destination = getDestinationPath(source);
					System.out.println(String.format("Moving %s", source));

					try {
						Files.move(Paths.get(source), destination, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						System.err.println(String.format("Error moving file %s to %s", source, destination.toString()));
					}
				}
				System.out.println("");
			}
		}
	}

	private Path getDestinationPath(String source) {
		String sourceName = new File(source).getName();
		return Paths.get(options.getMoveDestination(), sourceName);
	}
}

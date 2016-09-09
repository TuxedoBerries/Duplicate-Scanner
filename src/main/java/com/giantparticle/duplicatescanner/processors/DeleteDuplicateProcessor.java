/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.processors;

import java.io.File;
import java.util.Map;

import com.giantparticle.duplicatescanner.FileData;

/**
 * Delete all the duplicated files except the first one found.
 */
public class DeleteDuplicateProcessor extends BaseDuplicateProcessor {

	@Override
	protected void processFiles() {
		System.out.println("Duplicates");
		for (Map.Entry<String, FileData> entry : files.entrySet()) {
			FileData value = entry.getValue();
			if (value.FilePaths.size() > 1) {
				System.out.println(String.format("%s", value.MD5Hash));
				System.out.println(String.format("Keeping: %s", value.FilePaths.get(0)));
				for (int i = 1; i < value.FilePaths.size(); ++i) {
					String path = value.FilePaths.get(i);
					System.out.println(String.format("Deleting %s", path));
					File file = new File(path);
					file.delete();
				}
				System.out.println("");
			}
		}
	}
}

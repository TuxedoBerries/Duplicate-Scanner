/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.processors;

import java.util.Map;

import com.giantparticle.duplicatescanner.FileData;

/**
 * Display in the terminal all the duplicated files.
 */
public class DisplayDuplicateProcessor extends BaseDuplicateProcessor {

	@Override
	protected void processFiles() {
		System.out.println("Duplicates");
		for (Map.Entry<String, FileData> entry : files.entrySet()) {
			FileData value = entry.getValue();
			if (value.FilePaths.size() > 1) {
				System.out.println(String.format("%s", value.MD5Hash));
				for (int i = 0; i < value.FilePaths.size(); ++i) {
					System.out.println(String.format("- %s", value.FilePaths.get(i)));
				}
				System.out.println("");
			}
		}
	}
}

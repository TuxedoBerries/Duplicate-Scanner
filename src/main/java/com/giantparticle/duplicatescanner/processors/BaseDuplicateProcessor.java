/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.processors;

import java.util.HashMap;

import com.giantparticle.duplicatescanner.FileData;
import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Base class for processing duplicated files.
 */
public abstract class BaseDuplicateProcessor implements IDuplicateProcessor {

	protected ICLIOptions options;
	protected HashMap<String, FileData> files;

	/**
	 * Process the duplicated files
	 */
	public void processDuplicates(HashMap<String, FileData> map) {
		files = map;
		processFiles();
	}

	/**
	 * Set the command line options if needed
	 */
	public void setOptions(ICLIOptions options) {
		this.options = options;
	}

	/**
	 * Process the files
	 */
	protected abstract void processFiles();
}

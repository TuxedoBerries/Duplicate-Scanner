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
 * Interface to configure and execute a duplicate processor.
 */
public interface IDuplicateProcessor {

	/**
	 * Process the duplicated files.
	 * @param map File data
	 */
	void processDuplicates(HashMap<String, FileData> map);
	
	/**
	 * Set the options to process the duplicates
	 * @param options
	 */
	void setOptions(ICLIOptions options);
}

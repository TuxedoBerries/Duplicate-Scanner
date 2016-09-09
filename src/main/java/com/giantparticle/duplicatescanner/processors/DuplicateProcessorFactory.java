/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.processors;

import com.giantparticle.duplicatescanner.cli.ICLIOptions;

/**
 * Factory class that creates the proper duplicate processor.
 */
public class DuplicateProcessorFactory {

	public IDuplicateProcessor createProcessor(ICLIOptions options) {
		IDuplicateProcessor processor = null;
		
		// Delete
		if(options.deleteDuplicates()){
			processor = new DeleteDuplicateProcessor();
		}
		
		// Move
		if(options.moveDuplicates()){
			processor = new MoveDuplicateProcessor();
		}
		
		// Return Default
		if(processor == null){
			processor = new DisplayDuplicateProcessor();
		}
		
		processor.setOptions(options);
		return processor;
	}
}

/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.cli;

import java.util.HashMap;

import com.giantparticle.duplicatescanner.FileData;
import com.giantparticle.duplicatescanner.FolderHashGenerator;
import com.giantparticle.duplicatescanner.processors.DuplicateProcessorFactory;
import com.giantparticle.duplicatescanner.processors.IDuplicateProcessor;

/**
 * Main class
 */
public class DuplicateScanner {

	public static void main(String[] args) {
		FolderHashGenerator generator = new FolderHashGenerator();
		CLIOptions options = new CLIOptions();
		options.consumeArguments(args);
		if (!options.isAllSet())
			return;

		System.out.println("Checking Files");
		generator.setOptions(options);
		HashMap<String, FileData> data = generator.Generate(options.getSourceFolder());
		System.out.println("");

		DuplicateProcessorFactory factory = new DuplicateProcessorFactory();
		IDuplicateProcessor processor = factory.createProcessor(options);
		processor.processDuplicates(data);
	}
}

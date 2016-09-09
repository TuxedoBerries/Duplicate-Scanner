/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.cli;

/**
 * Command line option interface
 */
public interface ICLIOptions {

	/**
	 * Get the source folder to check duplicates
	 * @return
	 */
	String getSourceFolder();
	
	/**
	 * Indicate if all the options are set.
	 * @return true if all is set, false otherwise.
	 */
	boolean isAllSet();

	/**
	 * Indicate if the duplicates found should be deleted.
	 * @return true if the duplicates should be deleted, false otherwise.
	 */
	boolean deleteDuplicates();

	/**
	 * Indicate if the duplicates should be moved to a different folder.
	 * @return true if the duplicates should be moved, false otherwise.
	 */
	boolean moveDuplicates();

	/**
	 * If moveDuplicates is true, returns the destination folder.
	 * @return destination folder for the duplicates.
	 */
	String getMoveDestination();
	
	/**
	 * Indicate if the files should be check in silent mode.
	 * @return true if silent mode is on, false otherwise.
	 */
	boolean isSilent();
}

/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for MD5 hash and file paths associated.
 */
public class FileData {
	public String MD5Hash;
	public List<String> FilePaths;

	/**
	 * Empty constuctor
	 */
	public FileData() {
		FilePaths = new ArrayList<>(1);
	}
}

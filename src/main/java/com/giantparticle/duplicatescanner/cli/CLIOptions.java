/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Encapsulate the command line arguments into one class
 */
public class CLIOptions implements ICLIOptions {

	private static final String HELP_LONG = "help";
	private static final String DELETE_LONG = "delete";
	private static final String MOVE_LONG = "move";
	private static final String SILENT_LONG = "silent";
	private static final String HELP_SHORT = "h";
	private static final String DELETE_SHORT = "d";
	private static final String MOVE_SHORT = "m";
	private static final String SILENT_SHORT = "s";
	private static final String MOVE_FOLDER = "folder";

	private static final String SYNTAX = "dscanner <SOURCE FOLDER>";
	private static final String HEADER = "Identify duplicated files by comparing MD5 hashes.";
	private static final String FOOTER = "";

	private Options options;
	private String sourceFolder = null;
	private boolean allSet = false;
	private boolean deleteFiles = false;
	private boolean moveFiles = false;
	private String moveFolder = null;
	private boolean silent = false;

	/**
	 * Main constructor
	 */
	public CLIOptions() {
		options = new Options();
		// Add Help
		Option help = new Option(HELP_SHORT, HELP_LONG, false, "Print this message.");
		// Add Silent
		Option silent = new Option(SILENT_SHORT, SILENT_LONG, false, "Do not print each file scanned.");

		OptionGroup group = new OptionGroup();
		// Add Delete
		Option delete = new Option(DELETE_SHORT, DELETE_LONG, false, "Delete duplicated files (Keep the first file).");
		// Add Move
		Option move = new Option(MOVE_SHORT, MOVE_LONG, true, "Move duplicated files to a folder.");
		move.setArgName(MOVE_FOLDER);
		group.addOption(delete);
		group.addOption(move);
		group.setRequired(false);

		// Add all options
		options.addOption(help);
		options.addOption(silent);
		options.addOptionGroup(group);
	}

	/**
	 * Print help for the command line.
	 */
	public void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.setArgName("SOURCE FOLDER");
		formatter.printHelp(SYNTAX, HEADER, options, FOOTER, true);
	}

	/**
	 * Consume the command line arguments in order to generate the proper data.
	 * @param args command line arguments.
	 */
	public void consumeArguments(String[] args) {
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			printHelp();
		}

		if (cmd == null)
			return;

		if (cmd.hasOption(HELP_SHORT) || cmd.hasOption(HELP_LONG)) {
			printHelp();
			return;
		}

		if (cmd.hasOption(DELETE_SHORT) || cmd.hasOption(DELETE_LONG)) {
			deleteFiles = true;
		}

		if (cmd.hasOption(MOVE_SHORT)) {
			moveFiles = true;
			moveFolder = cmd.getOptionValue(MOVE_SHORT);
		}
		if (cmd.hasOption(MOVE_LONG)) {
			moveFiles = true;
			moveFolder = cmd.getOptionValue(MOVE_LONG);
		}

		// Source Folder
		String[] arguments = cmd.getArgs();
		if (arguments == null || arguments.length <= 0) {
			printHelp();
			return;
		}
		sourceFolder = arguments[0];

		allSet = true;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public boolean isAllSet() {
		return allSet;
	}

	public boolean deleteDuplicates() {
		return deleteFiles;
	}

	public boolean moveDuplicates() {
		return moveFiles;
	}

	public String getMoveDestination() {
		return moveFolder;
	}

	public boolean isSilent() {
		return silent;
	}
}

/**
 * 
 */
package org.berkholz.vcard2fritzXML;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author Marcel Berkholz <code@berkholz.org>
 * 
 */
public class CommandOptions {

	Options options;
	CommandLine cmd;
	String outputFile = "";
	String vcardDirectory = "";
	String vcardFile = "";
	String phonebookName = "Privat"; // Default phonebook name is "Privat"
	String[] args;

	/**
	 * 
	 * @param String
	 *            [] args - Command line arguments.
	 * @throws ParseException
	 * 
	 */
	public CommandOptions(String[] args) throws ParseException {
		this.options = new Options();
		this.args = args;

	}

	/**
	 * Define the possible command line options.
	 * 
	 * @throws ParseException
	 */
	public void setOptions() throws ParseException {
		// options definitions
		this.options.addOption("h", "help", false, "display help");
		this.options.addOption("d", "directory", true, "directory with vcards");
		this.options.addOption("f", "file", true, "file with all vcards");
		this.options.addOption("o", "outfile", true, "output file");
		this.options.addOption("n", "phonebookname", true, "name of phonebook");
		this.options.addOption("v", "verbose", false, "verbose");

		// instantiate parser with our options
		CommandLineParser parser = new GnuParser();
		this.cmd = parser.parse(this.options, this.args);
	}

	/**
	 * Checks the options, if all recommended options are given and if excluding parameters are met.
	 */
	public void checkOptions() {
		if (cmd.hasOption("h")) {
			Main.printHelp();
			System.exit(0);
		}

		if (cmd.hasOption("o")) {
			this.outputFile = cmd.getOptionValue("o");
			System.out.println("Printing out to file: " + this.outputFile);
		}

		// check if both options (-f and -d) are given or not.
		if (!cmd.hasOption("d") & !cmd.hasOption("f")) {
			System.out.println("You have to specify either the -d or -f option.");
			System.exit(1);
		} else if (cmd.hasOption("d") & cmd.hasOption("f")) {
			System.out.println("You have to specify only one of the -d or -f options, not both.");
			System.exit(1);
		}

		if (cmd.hasOption("d")) {
			System.out.println("The import of a directory within vcards is not yet implemented");
			System.exit(1);
			// TODO: Implement the import of vcards from a directory
			// vcardDirectory = cmd.getOptionValue("d");
			// System.out.println("Reading vcard files from directory: " + vcardDirectory);
		}

		if (cmd.hasOption("f")) {
			vcardFile = cmd.getOptionValue("f");
			System.out.println("Reading vcard file: " + vcardFile);
		}

		if (cmd.hasOption("n")) {
			phonebookName = cmd.getOptionValue("n");
			System.out.println("Name of phonebook is " + this.phonebookName);
		}

	}
}
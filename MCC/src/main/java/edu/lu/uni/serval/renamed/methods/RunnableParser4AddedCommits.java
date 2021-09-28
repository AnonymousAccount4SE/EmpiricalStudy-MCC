package edu.lu.uni.serval.renamed.methods;

import java.io.File;

public class RunnableParser4AddedCommits implements Runnable {

	private File prevFile;
	private File revFile;
	private File diffentryFile;
	private CodeChangeParser4AddedCommits parser;

	public RunnableParser4AddedCommits(File prevFile, File revFile, File diffentryFile, CodeChangeParser4AddedCommits parser) {
		this.prevFile = prevFile;
		this.revFile = revFile;
		this.diffentryFile = diffentryFile;
		this.parser = parser;
	}

	@Override
	public void run() {
		parser.parse(revFile, prevFile, diffentryFile);
	}
}
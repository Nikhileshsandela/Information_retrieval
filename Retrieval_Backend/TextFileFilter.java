package Retrieval;

// this class for filtering .txt files

import java.io.File; 
import java.io.FileFilter;

public class TextFileFilter implements FileFilter 
{
	@Override 
	public boolean accept(File pathname) 
	{ 
		if(pathname.getName().toLowerCase().endsWith(".txt") || pathname.getName().toLowerCase().endsWith(".html"))
			return true;
		return false; 
	}
}
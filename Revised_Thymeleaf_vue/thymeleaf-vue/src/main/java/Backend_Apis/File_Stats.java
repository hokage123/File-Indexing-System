package Backend_Apis;
import java.io.*;
import java.util.*;
class File_Stats
{
	private int number_Of_Lines;
	private int number_Of_Words;
	private int size;
	public void set_Number_Of_Lines(String file_To_Raw_String)
	{
		int required_count=0;
		char[] file_To_char_Array=file_To_Raw_String.toCharArray();
		for(char ch:file_To_char_Array)
		{
			if(ch=='\n')
				required_count++;
		}
		number_Of_Lines=required_count;
	}
	public void set_Number_Of_Words(String[] converted_File_To_Words)
	{
		number_Of_Words=converted_File_To_Words.length;
	}
	public void set_Size(String file_To_Raw_String)
	{
		size=file_To_Raw_String.length();
	}
	public int get_Number_Of_Lines()
	{
		return number_Of_Lines;
	}
	public int get_Number_Of_Words()
	{
		return number_Of_Words;
	}
	public int get_Size()
	{
		return size;
	}
}
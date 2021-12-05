package Backend_Apis;
import java.io.*;
import java.util.*;
public class File_Read
{
	String file_Name;
	String all_file_content;
	public File_Read(String file_Name)throws IOException
	{
		this.file_Name=file_Name;
		this.all_file_content=file_To_Content();
	}
	public String file_To_Content()throws IOException
	{
		BufferedReader reader = new BufferedReader( 
                              new InputStreamReader(new FileInputStream(file_Name)));
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		while (reader.read(buffer) != -1) {
			stringBuilder.append(new String(buffer));
			buffer = new char[10];
		}
		reader.close();
		String content = stringBuilder.toString();
		return content;	
	}
	public String file_To_Raw_String()
	{
		return all_file_content;
	}
	public String file_To_String()
	{
		int index=0;
		ArrayList<Character> ar=new ArrayList<>();
		while(index<all_file_content.length())
		{			
			char d=all_file_content.charAt(index);
			if(d>='a' && d<='z')
				ar.add(d);
			else if(d>='A' && d<='Z')
				ar.add(d);
			else
				ar.add(' ');
			index++;
		}
		char char_Array[]=new char[ar.size()];
		for(int i=0;i<ar.size();i++)
			char_Array[i]=ar.get(i);
		String s=new String(char_Array);
		//fin.close();
		return s;
	}
	public String[] file_To_Words()throws Exception
	{
		return file_To_String().toLowerCase().split("[ \n\t]");
	}
}
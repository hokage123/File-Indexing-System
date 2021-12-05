package Backend_Apis;
import java.util.*;
import java.io.*;
class File_Searcher
{
	private Map<String,Map<String,String>> name_Path_And_Content;
	public File_Searcher()
	{
		name_Path_And_Content=new HashMap<String,Map<String,String>>();
	}
	void add_File(String filename,String path,String content)
	{
		if(name_Path_And_Content.containsKey(filename))
		{
			name_Path_And_Content.get(filename).put(path,content);
		}
		else
		{
			Map<String,String> new_HashMap=new HashMap<String,String>();
			new_HashMap.put(path,content);
			name_Path_And_Content.put(filename,new_HashMap);
		}
		//System.out.println(name_Path_And_Content);
	}
	void update(String filename)
	{
		
		Map<String,String> updated_HashMap=new HashMap<String,String>();
		if(name_Path_And_Content.containsKey(filename))
		{
			for(Map.Entry<String,String> entry:name_Path_And_Content.get(filename).entrySet())
			{
				File file_present=new File(entry.getKey());
				if(file_present.exists())
					updated_HashMap.put(entry.getKey(),entry.getValue());
			}
			name_Path_And_Content.put(filename,updated_HashMap);
			if(name_Path_And_Content.get(filename).isEmpty())
			{
				name_Path_And_Content.remove(filename);
			}
		}
	}
	Map<String,String> search_File_By_Name(String filename)
	{
		update(filename);
		if(name_Path_And_Content.containsKey(filename))
		{
			return name_Path_And_Content.get(filename);
		}
		return null;
	}
}
package Backend_Apis;
import java.util.*;
import java.io.*;
class Read_Directory
{
	private String dir;
	private ArrayList<String> directory_Files;
	private ArrayList<String> directory_Added_Files;
	private HashSet<String> directory_Modified_Files;
	private HashSet<String> directory_Deleted_Files;
	private HashMap<String,String> Last_Operation;
	public Read_Directory(String dir)
	{
		this.dir=dir;
		directory_Files=new ArrayList<>();
		directory_Added_Files=new ArrayList<>();
		directory_Modified_Files=new HashSet<>();
		directory_Deleted_Files=new HashSet<>();
		Last_Operation=new HashMap<>();
		set_directory_Files();
	}
	public void tree_Structure(ArrayList<String> required_To_Update,String filename)
	{
		File file=new File(filename);
		if(!file.isDirectory())
			required_To_Update.add(filename);
		else
		{
			String files[]=file.list();
			for(int i=0;i<files.length;i++)
				tree_Structure(required_To_Update,(filename+File.separator+files[i]));
		}
	}
	public void File_And_Operation(String fileName,String operation)
	{
		Last_Operation.put(fileName,operation);
	}
	public String get_Last_Operation(String fileName)
	{
		//System.out.println(fileName);
		//System.out.println(Last_Operation);
		if(Last_Operation.containsKey(fileName)==true)
		return Last_Operation.get(fileName);
		return null;
	}
	public void remove_Operation(String fileName)
	{
		Last_Operation.remove(fileName);
	}
	public void set_directory_Files()
	{
		tree_Structure(directory_Files,dir);
	}
	public void add_directory(String new_dir)
	{
		tree_Structure(directory_Added_Files,new_dir);
	}
	public void add_directory_Modified_File(String filename)
	{
		directory_Modified_Files.add(filename);
	}
	public void add_directory_Deleted(String del_dir)
	{
		//tree_Structure(directory_Deleted_Files,del_dir);
	}
	public ArrayList<String> get_directory_Files()
	{
		return directory_Files; 
	}
	public ArrayList<String> get_directory_Added_Files()
	{
		return directory_Added_Files;
	}
	public HashSet<String> get_directory_Modified_Files()
	{
		return directory_Modified_Files;
	}
	public HashSet<String> get_directory_Deleted_Files()
	{
		return directory_Deleted_Files;
	}
}
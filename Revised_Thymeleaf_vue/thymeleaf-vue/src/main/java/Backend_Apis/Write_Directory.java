package Backend_Apis;
import java.io.*;
import java.nio.*;
import java.util.*;
class Write_Directory
{
	private HashMap<String,File_Data_Serialization_And_Deserialization> all_File_all_Data;
	private String read_dir;
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	private File output_file;
	public Write_Directory(String dir)
	{
		this.read_dir=dir;
		this.all_File_all_Data=new HashMap<>();
	}
	public void create_Output_File()throws IOException
	{
		output_file=new File((read_dir+File.separator+"output.txt"));
		if(!output_file.exists())
		{
			output_file.createNewFile();
		}
	}
	public void store_all_File_all_Data()throws IOException
	{
		fileOutputStream=new FileOutputStream((read_dir+File.separator+"output.txt"));
		objectOutputStream=new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(all_File_all_Data);
	}
	public void put_File_Data(String filename,File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization)
	{
		//System.out.println("ankit"+"  "+filename);
		all_File_all_Data.put(filename,file_Data_Serialization_And_Deserialization);
		
	}
	public void remove_File_Data(String filename)
	{
		
		all_File_all_Data.remove(filename);
	}
	public boolean if_Output_Contains(String filename)
	{
		return all_File_all_Data.containsKey(filename);
	}
	/*public void make_Writing_Directory()
	{
		tree_Structure(write_dir,read_dir);
	}
	public void tree_Structure(String new_Filename,String filename)
	{
		File input_file=new File(filename);
		if(!input_file.isDirectory())
			read_File_write_File_pair.put(filename,new_Filename);
		else
		{
			String files[]=file.list();
			for(int i=0;i<files.length;i++)
				tree((new_Filename+File.separator+files[i]),(filename+File.separator+files[i]));
		}	
	}
	public HashMap<String,String> get_read_File_write_File_pair()
	{
		return read_File_write_File_pair;
	}*/	
}
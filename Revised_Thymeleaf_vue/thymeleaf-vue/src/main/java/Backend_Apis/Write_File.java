package Backend_Apis;
import java.io.*;
import java.util.*;
class Write_File
{
	private String filename;
	private File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization;
	//private File_Stats file_Stats;
	private Read_Directory read_Directory;
	private Write_Directory write_Directory;
	public Write_File(String filename,File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization,Read_Directory read_Directory,Write_Directory write_Directory)
	{
		this.filename=filename;
		//System.out.println(filename);
		//new_Filename=write_Directory.get_File_To_Write(filename);
		this.file_Data_Serialization_And_Deserialization=file_Data_Serialization_And_Deserialization;
		//file_Stats=this.file_Stats;
		this.read_Directory=read_Directory;
		this.write_Directory=write_Directory;
	}
	public void write_Content()
	{
		//File temp=new File(new_Filename);	
		if(read_Directory.get_Last_Operation(filename)==null)
		{
			if(!write_Directory.if_Output_Contains(filename))
			{
				read_Directory.remove_Operation(filename);
				write_Directory.put_File_Data(filename,file_Data_Serialization_And_Deserialization);
			}
		}
		else if(read_Directory.get_Last_Operation(filename).equals("DELETE"))
		{
			read_Directory.remove_Operation(filename);
			write_Directory.remove_File_Data(filename);
		}
		else if(read_Directory.get_Last_Operation(filename).equals("MODIFY"))
		{
			read_Directory.remove_Operation(filename);
			write_Directory.put_File_Data(filename,file_Data_Serialization_And_Deserialization);
		}
		else
		{
			if(!write_Directory.if_Output_Contains(filename))
			{
				read_Directory.remove_Operation(filename);
				write_Directory.put_File_Data(filename,file_Data_Serialization_And_Deserialization);
			}
		}
	}	
}
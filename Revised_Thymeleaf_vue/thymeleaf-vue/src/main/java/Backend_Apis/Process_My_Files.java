package Backend_Apis;
import java.io.*;
import java.util.*;
class Process_My_Files
{
	private Read_Directory read_Directory;
	private Write_Directory write_Directory;
	public Process_My_Files(Read_Directory read_Directory,Write_Directory write_Directory)
	{
		this.read_Directory=read_Directory;
		this.write_Directory=write_Directory;
	}
	public synchronized void process_My_File(String filepath,File_Searcher my_File_Searcher)throws Exception
	{
		
			File file=new File(filepath);
			if(file.exists())
				{	
					
					File_Read file_Read=new File_Read(filepath);
					File_Keywords_Frequencies file_Keywords_Frequencies=new File_Keywords_Frequencies();
					File_Stats file_Stats=new File_Stats();
					String file_To_Raw_String=file_Read.file_To_Raw_String();
					String file_To_String=file_Read.file_To_String();
					String[] file_To_Words=file_Read.file_To_Words();
					file_Keywords_Frequencies.convert_File_To_Frequency_Maps(file_To_Words);
					ArrayList<HashMap<String,Integer>> all_Keywords_Frequencies=file_Keywords_Frequencies.get_all_Keywords_Frequencies();
					file_Stats.set_Number_Of_Lines(file_To_Raw_String);
					file_Stats.set_Number_Of_Words(file_To_Words);
					file_Stats.set_Size(file_To_Raw_String);
					File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization=new File_Data_Serialization_And_Deserialization(all_Keywords_Frequencies,file_Stats.get_Number_Of_Lines(),file_Stats.get_Number_Of_Words(),file_Stats.get_Size());
					Write_File write_File=new Write_File(filepath,file_Data_Serialization_And_Deserialization,read_Directory,write_Directory);
					write_File.write_Content();
					my_File_Searcher.add_File(file.getName(),filepath,file_To_Raw_String);
				}
			
	}
	
}
package Backend_Apis;
import java.io.*;
import java.util.*;
class Publish_My_Stats
{
	private String dir;
	private FileInputStream fileInputStream;
	private ObjectInputStream objectInputStream;
	public Publish_My_Stats(String dir)throws IOException
	{
		this.dir=dir;
		fileInputStream=new FileInputStream(dir+File.separator+"output.txt");
		objectInputStream=new ObjectInputStream(fileInputStream);
	}
	public void publish_My_Stats()throws IOException,ClassNotFoundException
	{
		File file=new File(dir+File.separator+"Published_Directory_Stats.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		StringBuilder stringbuilder=new StringBuilder();
		HashMap<String,File_Data_Serialization_And_Deserialization> all_File_all_Data=(HashMap<String,File_Data_Serialization_And_Deserialization>)objectInputStream.readObject();
		stringbuilder.append("{filepath"+"\t"+"number_Of_Lines"+"\t"+"number_Of_Words"+"\t"+"size}"+"\n");
		for(String filepath:all_File_all_Data.keySet())
		{
			File temp_file=new File(filepath);
			if(temp_file.exists())
			{
				//String[] temp=filepath.split(File.separator);
				String filename=file.getName();
				File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization=all_File_all_Data.get(filepath);
				int number_Of_Lines=file_Data_Serialization_And_Deserialization.get_Number_Of_Lines();
				int number_Of_Words=file_Data_Serialization_And_Deserialization.get_Number_Of_Words();
				int size=file_Data_Serialization_And_Deserialization.get_Size();
				stringbuilder.append("{"+filepath+"\t"+number_Of_Lines+"\t"+number_Of_Words+"\t"+size+"}"+"\n");	
			}	
			
		}
		bufferedWriter.write(stringbuilder.toString());
		objectInputStream.close();
		fileInputStream.close();
		bufferedWriter.close();
	}	
}
package Backend_Apis;
import java.io.*;
import java.util.*;
class Sorter
{
	private String dir;
	private FileInputStream fileInputStream;
	private ObjectInputStream objectInputStream;
	private ArrayList<Candidate> To_Be_Sorted;
	public Sorter(String dir)throws IOException
	{
		this.dir=dir;
		fileInputStream=new FileInputStream(dir+File.separator+"output.txt");
		objectInputStream=new ObjectInputStream(fileInputStream);
		To_Be_Sorted=new ArrayList<>();
	}
	public void sort(String feature)throws IOException,ClassNotFoundException
	{
		HashMap<String,File_Data_Serialization_And_Deserialization> all_File_all_Data=(HashMap<String,File_Data_Serialization_And_Deserialization>)objectInputStream.readObject();
		for(String filepath:all_File_all_Data.keySet())
		{
			File file=new File(filepath);
			if(file.exists())
			{
				//String[] temp=filepath.split(File.separator);
				String filename=file.getName();
				File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization=all_File_all_Data.get(filepath);
				int size=file_Data_Serialization_And_Deserialization.get_Size();
				int number_Of_Lines=file_Data_Serialization_And_Deserialization.get_Number_Of_Lines();
				int number_Of_Words=file_Data_Serialization_And_Deserialization.get_Number_Of_Words();
				To_Be_Sorted.add(new Candidate(filepath,filename,size,number_Of_Lines,number_Of_Words));	
			}	
			
		}
		if(feature.equals("By_Size"))
			Collections.sort(To_Be_Sorted,new Sort_By_Size());
		else if(feature.equals("By_FileName"))
			Collections.sort(To_Be_Sorted,new Sort_By_Filename());
		else if(feature.equals("By_Number_Of_Lines"))
			Collections.sort(To_Be_Sorted,new Sort_By_Number_Of_Lines());
		else if(feature.equals("By_Number_Of_Words"))
			Collections.sort(To_Be_Sorted,new Sort_By_Number_Of_Words());
			
		File file=new File(dir+File.separator+"Sorted_File_Details.txt");
		file.createNewFile();
		BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir+File.separator+"Sorted_File_Details.txt")));
		StringBuilder stringbuilder=new StringBuilder();
		stringbuilder.append("{"+"filepath"+"\t"+"filename"+"\t"+"size"+"\t"+"number_Of_Lines"+"\t"+"number_Of_Words"+"}"+"\n");
		for(Candidate candidate:To_Be_Sorted)
		{
			stringbuilder.append(candidate.toString()+"\n");
		}
		bufferedWriter.write(stringbuilder.toString());
		objectInputStream.close();
		fileInputStream.close();
		bufferedWriter.close();
	}	
}
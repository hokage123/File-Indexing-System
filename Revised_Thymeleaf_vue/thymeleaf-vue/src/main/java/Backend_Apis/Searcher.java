package Backend_Apis;
import java.util.*;
import java.io.*;
class Searcher
{
	private String dir;
	private FileInputStream fileInputStream;
	private ObjectInputStream objectInputStream;
	public Searcher(String dir)throws FileNotFoundException,IOException
	{
		this.dir=dir;
		fileInputStream=new FileInputStream(dir+File.separator+"output.txt");
		objectInputStream=new ObjectInputStream(fileInputStream);
	}
	public void total_Number_Of_This_Token(String token)throws IOException,ClassNotFoundException		
	{
		int total_Number_Of_This_Token=0;
		HashMap<String,File_Data_Serialization_And_Deserialization> all_File_all_Data=(HashMap<String,File_Data_Serialization_And_Deserialization>)objectInputStream.readObject();
		File file1=new File(dir+File.separator+"Searched_Keyword.txt");
		file1.createNewFile();
		BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir+File.separator+"Searched_Keyword.txt")));
		StringBuilder stringbuilder=new StringBuilder();
		stringbuilder.append("{"+"filepath"+"\t"+"total_Number_Of_Token_In_Current_File"+"}\n");
		for(String filepath:all_File_all_Data.keySet())
		{
			File file=new File(filepath);
			//System.out.println(filepath+" "+all_File_all_Data.containsKey(filepath));
			File_Data_Serialization_And_Deserialization file_Data_Serialization_And_Deserialization=all_File_all_Data.get(filepath);
			//System.out.println(file_Data_Serialization_And_Deserialization);
			ArrayList<HashMap<String,Integer>> frequency_Table=file_Data_Serialization_And_Deserialization.get_all_Keywords_Frequencies();
			int total_Number_Of_Token_In_Current_File=0;
			if(frequency_Table!=null && file.exists())
			{	
				for(HashMap<String,Integer> frequency_Table_shot:frequency_Table)
				{	
						//System.out.println(frequency_Table_shot);
						if(frequency_Table_shot.containsKey(token))
						{
							//System.out.println(filepath);
							total_Number_Of_This_Token+=frequency_Table_shot.get(token);
							total_Number_Of_Token_In_Current_File+=frequency_Table_shot.get(token);
						}
				}
			}
			if(total_Number_Of_Token_In_Current_File>0)
			stringbuilder.append("{"+filepath+"\t"+total_Number_Of_Token_In_Current_File+"}\n");
			
		}
		stringbuilder.append("total_Number_Of_This_Token="+total_Number_Of_This_Token);
		bufferedWriter.write(stringbuilder.toString());
		objectInputStream.close();
		fileInputStream.close();
		bufferedWriter.close();
	}	
}
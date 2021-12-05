package Backend_Apis;
import java.util.*;
import java.io.*;
class File_Data_Serialization_And_Deserialization implements Externalizable
{
	private ArrayList<HashMap<String,Integer>> all_Keywords_Frequencies;
	private int number_Of_Lines;
	private int number_Of_Words;
	private int size;
	//Mandatory default constructor
	public File_Data_Serialization_And_Deserialization()
	{
		
	}
	public File_Data_Serialization_And_Deserialization(ArrayList<HashMap<String,Integer>> all_Keywords_Frequencies,int number_Of_Lines,int number_Of_Words,int size)
	{
		this.all_Keywords_Frequencies=all_Keywords_Frequencies;
		this.number_Of_Lines=number_Of_Lines;
		this.number_Of_Words=number_Of_Words;
		this.size=size;
	}
	/*mandatory writeExternal method*/
	public void writeExternal(ObjectOutput out)throws IOException
	{
		//System.out.println("writeExternal");
		out.writeObject(all_Keywords_Frequencies);
		out.writeInt(number_Of_Lines);
		out.writeInt(number_Of_Words);
		out.writeInt(size);
	}
	/*mandatory readExternal method*/
	public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException
	{
		//System.out.println("readExternal");
		all_Keywords_Frequencies=(ArrayList<HashMap<String,Integer>>)in.readObject();
		number_Of_Lines=in.readInt();
		number_Of_Words=in.readInt();
		size=in.readInt();
	}
	public void print_all_keywords()
	{
		for(HashMap<String,Integer> hmap:all_Keywords_Frequencies)
		{
			for(Map.Entry<String,Integer> ef:hmap.entrySet())
			{
				System.out.println(ef.getKey()+" "+ef.getValue());
			}
		}
	}
	public ArrayList<HashMap<String,Integer>> get_all_Keywords_Frequencies()
	{
		return all_Keywords_Frequencies;
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
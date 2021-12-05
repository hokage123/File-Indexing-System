package Backend_Apis;
class Candidate
{
	String filepath;
	String filename;
	Integer size;
	Integer number_Of_Lines;
	Integer number_Of_Words;
	
	public Candidate(String filepath,String filename,int size,int number_Of_Lines,int number_Of_Words)
	{
		this.filepath=filepath;
		this.filename=filename;
		this.size=size;
		this.number_Of_Lines=number_Of_Lines;
		this.number_Of_Words=number_Of_Words;
	}
	public String toString()
	{
		return ("{"+filepath+"\t"+filename+"\t"+size+"\t"+number_Of_Lines+"\t"+number_Of_Words+"}");
	}
}
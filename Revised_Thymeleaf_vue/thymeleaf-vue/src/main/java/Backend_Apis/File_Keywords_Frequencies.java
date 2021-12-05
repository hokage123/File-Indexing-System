package Backend_Apis;
import java.io.*;
import java.util.*;
class File_Keywords_Frequencies
{
	private ArrayList<HashMap<String,Integer>> all_Keywords_Frequencies;
	private HashSet<String> not_a_keyword;
	public File_Keywords_Frequencies()
	{
		all_Keywords_Frequencies=new ArrayList<HashMap<String,Integer>>();
		not_a_keyword=new HashSet<String>();	
	}
	public void filing_Not_A_Keyword()
	{
		not_a_keyword.add("is");
		not_a_keyword.add("am");
		not_a_keyword.add("are");
		not_a_keyword.add("was");
		not_a_keyword.add("were");
		not_a_keyword.add("be");
		not_a_keyword.add("being");
		not_a_keyword.add("been");
		not_a_keyword.add("have");
		not_a_keyword.add("has");
		not_a_keyword.add("had");
		not_a_keyword.add("do");
		not_a_keyword.add("does");
		not_a_keyword.add("did");
		not_a_keyword.add("shall");
		not_a_keyword.add("will");
		not_a_keyword.add("should");
		not_a_keyword.add("would");
		not_a_keyword.add("may");
		not_a_keyword.add("might");
		not_a_keyword.add("must");
		not_a_keyword.add("can");
		not_a_keyword.add("could");
		not_a_keyword.add("i");
		not_a_keyword.add("you");
		not_a_keyword.add("he");
		not_a_keyword.add("she");
		not_a_keyword.add("it");
		not_a_keyword.add("they");
		not_a_keyword.add("my");
		not_a_keyword.add("mine");
		not_a_keyword.add("his");
		not_a_keyword.add("her");
		not_a_keyword.add("hers");
		not_a_keyword.add("its");
		not_a_keyword.add("me");
		not_a_keyword.add("you");
		not_a_keyword.add("him");
		not_a_keyword.add("it");
		not_a_keyword.add("that");
		not_a_keyword.add("another");
		not_a_keyword.add("each");
		not_a_keyword.add("everything");
		not_a_keyword.add("nobody");
		not_a_keyword.add("either");
		not_a_keyword.add("someone");
		not_a_keyword.add("myself");
		not_a_keyword.add("yourself");
		not_a_keyword.add("himself");
		not_a_keyword.add("herself");
		not_a_keyword.add("itself");
		not_a_keyword.add("this");
		not_a_keyword.add("for");
		not_a_keyword.add("and");
		not_a_keyword.add("or");
		not_a_keyword.add("nor");
		not_a_keyword.add("but");
		not_a_keyword.add("yet");
		not_a_keyword.add("so");
		not_a_keyword.add("about");
		not_a_keyword.add("above");
		not_a_keyword.add("according");
		not_a_keyword.add("to");
		not_a_keyword.add("across");
		not_a_keyword.add("after");
		not_a_keyword.add("against");
		not_a_keyword.add("ahead");
		not_a_keyword.add("of");
		not_a_keyword.add("along");
		not_a_keyword.add("admist");
		not_a_keyword.add("among");
		not_a_keyword.add("amongst");
		not_a_keyword.add("apart");
		not_a_keyword.add("from");
		not_a_keyword.add("around");
		not_a_keyword.add("as");
		not_a_keyword.add("far");
		not_a_keyword.add("well");
		not_a_keyword.add("aside");
		not_a_keyword.add("at");
		not_a_keyword.add("because");
		not_a_keyword.add("before");
		not_a_keyword.add("behind");
		not_a_keyword.add("below");
		not_a_keyword.add("beneath");
		not_a_keyword.add("beside");
		not_a_keyword.add("besides");
		not_a_keyword.add("between");
		not_a_keyword.add("beyond");
		not_a_keyword.add("by");
		not_a_keyword.add("means");
		not_a_keyword.add("concerning");
		not_a_keyword.add("despite");
		not_a_keyword.add("down");
		not_a_keyword.add("due");
		not_a_keyword.add("during");
		not_a_keyword.add("case");
		not_a_keyword.add("inside");
		not_a_keyword.add("instead");
		not_a_keyword.add("into");
		not_a_keyword.add("except");
		not_a_keyword.add("excluding");
		not_a_keyword.add("following");
		not_a_keyword.add("since");
		not_a_keyword.add("of");
		not_a_keyword.add("on");
		not_a_keyword.add("behalf");
		not_a_keyword.add("before");
	}
	public int contains_the_key(String keyword)
	{
		int count=0;
		for(HashMap<String,Integer> hm:all_Keywords_Frequencies)
		{
			if(hm.containsKey(keyword))
				return count;
			count++;
		}
		return -1;
	}
	public int check_size()
	{
		if(all_Keywords_Frequencies.size()==0)
			return 1;
		else if((all_Keywords_Frequencies.get(all_Keywords_Frequencies.size()-1).size())>100000)
			return 1;
		return 0;
	}
		
	public void add_My_Token(String token)
	{
		int index=contains_the_key(token);
		if(!not_a_keyword.contains(token))
		{	
			if(index==-1)
			{
				if(check_size()==1)
				{
					HashMap<String,Integer> new_HashMap=new HashMap<>();
					new_HashMap.put(token,1);
					all_Keywords_Frequencies.add(new_HashMap);
				}
				else
				{
					all_Keywords_Frequencies.get(all_Keywords_Frequencies.size()-1).put(token,1);
				}
			}
			else
			{
				all_Keywords_Frequencies.get(index).put(token,(all_Keywords_Frequencies.get(index).get(token)+1));
			}
		}
	}
	
	public void convert_File_To_Frequency_Maps(String[] converted_File_To_Words)
	{
		filing_Not_A_Keyword();
		for(String s:converted_File_To_Words)
			add_My_Token(s);
	}
	public ArrayList<HashMap<String,Integer>> get_all_Keywords_Frequencies()
	{
		return all_Keywords_Frequencies;
	}
}
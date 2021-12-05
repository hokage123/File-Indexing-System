package Backend_Apis;
import java.io.*;
import java.util.*;
public class Main_Calls implements Runnable
{
	private String dir;
	private Read_Directory read_Directory;
	private Write_Directory write_Directory;
	private Process_My_Files process_My_Files;
	private Watcher watcher;
	private File_Searcher my_File_Searcher;
	public Main_Calls(String dir)throws Exception
	{
		this.dir=dir;
		read_Directory=new Read_Directory(dir);
		write_Directory=new Write_Directory(dir);
		process_My_Files=new Process_My_Files(read_Directory,write_Directory);
		my_File_Searcher=new File_Searcher();
		watcher=new Watcher(dir,read_Directory,write_Directory,process_My_Files,my_File_Searcher);
	}
	@SuppressWarnings("deprecation")
	public void run()
	{
		try
		{
			
			/*Scanner sc=new Scanner(System.in);
			System.out.println("Enter the directory");
			String dir=sc.next();*/
			File previous_output=new File(dir+File.separator+"output.txt");
			previous_output.delete();	
			//WatchDir watch_Dir=new WatchDir(dir,read_Directory,write_Directory,process_My_Files);
			//ArrayList<String> Directory_Files=read_Directory.get_directory_Files();
			for(String filepath:read_Directory.get_directory_Files())
			{
				process_My_Files.process_My_File(filepath,my_File_Searcher);
			}
			
		}
		catch(Exception exp)
		{}
	}
	public void Publish_Directory_Stats()throws Exception
	{
		File previous_stats=new File(dir+File.separator+"Published_Directory_Stats.txt");
		previous_stats.delete();
		write_Directory.create_Output_File();
		write_Directory.store_all_File_all_Data();
		Publish_My_Stats publish_My_Stats=new Publish_My_Stats(dir);
		publish_My_Stats.publish_My_Stats();
	}
	public void sort_By_Factor(String factor)throws Exception
	{
		File previous_sorted_List=new File(dir+File.separator+"Sorted_File_Details.txt");
		previous_sorted_List.delete();
		write_Directory.create_Output_File();
		write_Directory.store_all_File_all_Data();
		Sorter sorter=new Sorter(dir);
		sorter.sort(factor);;	
	}
	public void search_By_Keyword(String keyword)throws Exception
	{
		write_Directory.create_Output_File();
		write_Directory.store_all_File_all_Data();
		Searcher searcher=new Searcher(dir);
		searcher.total_Number_Of_This_Token(keyword);	
	}
	/*public void start_Directory_Watcher()throws Exception
	{
		watcher=new Watcher(dir,read_Directory,write_Directory,process_My_Files);
	}*/
	public void stop_Directory_Watcher()throws Exception
	{
		watcher.stop();
	}
	public Map<String,String> search_By_Filename(String filename)
	{
		return my_File_Searcher.search_File_By_Name(filename);
	}
}
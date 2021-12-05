package Backend_Apis;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
class Main_Thread
{
	HashMap<String,Main_Calls> entry_Of_Processed_Directories;
	ArrayList<String> directory_Entries;
	public Main_Thread()
	{
		entry_Of_Processed_Directories=new HashMap<>();
		directory_Entries=new ArrayList<>();
	}
	public static void main(String[] gh)
	{
		try
		{
			Scanner scanner=new Scanner(System.in);
			Main_Thread main_Thread=new Main_Thread();
			String curr_running_dir="";
			ExecutorService executor = Executors.newFixedThreadPool(5);
			main_Thread.my_Constant_Printer();
			int input=scanner.nextInt();
			while(input!=9)
			{
				
				switch(input)
				{
					case 1:
					{
						/*if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.stop_Directory_Watcher();
						}*/	
						System.out.println("Enter the Directory Path");
						File new_Dir=new File(scanner.next());
						curr_running_dir=new_Dir.getAbsolutePath();
						if(!main_Thread.have_Previously_Processed(curr_running_dir) && new_Dir.exists())
						{
							Runnable worker=new Main_Calls(curr_running_dir);
							executor.execute(worker);
							main_Thread.add_New_Directory_In_Process(curr_running_dir,((Main_Calls)worker));
							//main_Thread.add_Current_Directory(curr_running_dir);
							//((Main_Calls)worker).start_Directory_Watcher();
						}
						else if(main_Thread.have_Previously_Processed(curr_running_dir))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							//main_Calls.start_Directory_Watcher();
						}
						else
							curr_running_dir="";	
						break;	
					}
					case 2:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.Publish_Directory_Stats();
							System.out.println("Details in "+curr_running_dir+File.separator+"Published_Directory_Stats.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}		
						break;
					}
					case 3:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.sort_By_Factor("By_Size");
							System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 4:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.sort_By_Factor("By_FileName");
							System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 5:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.sort_By_Factor("By_Number_Of_Lines");
							System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 6:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							main_Calls.sort_By_Factor("By_Number_Of_Words");
							System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 7:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							System.out.println("Enter the Keyword");
							String keyword=scanner.next(); 
							main_Calls.search_By_Keyword(keyword);
							System.out.println("Details in "+curr_running_dir+File.separator+"Searched_Keyword.txt");
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 8:
					{
						if(!curr_running_dir.equals(""))
						{
							Main_Calls main_Calls=main_Thread.get_My_Processings(curr_running_dir);
							System.out.println("Enter the filename");
							String keyword=scanner.next(); 
							System.out.println(main_Calls.search_By_Filename(keyword).size());
							
						}
						else
						{
							System.out.println("Enter Directory First");
						}
						break;
					}
					case 9:
					{
						if(!curr_running_dir.equals(""))
						{
							for(String dir:main_Thread.entry_Of_Processed_Directories.keySet())
							main_Thread.get_My_Processings(dir).stop_Directory_Watcher();
							//System.out.println("Details in"+curr_running_dir+File.separator+"Searched_Keyword.txt");
						}
						break;
					}
					default:
						System.out.println("Invalid Input");
				}
				main_Thread.my_Constant_Printer();
				input=scanner.nextInt();
			}
			 executor.shutdown();  
	        while (!executor.isTerminated()) {}
			System.out.println("Taking an exit");
			System.exit(0);
			scanner.close();
		}
		catch(Exception exp)
		{}
	}
	public void my_Constant_Printer()
	{
		System.out.println("Enter 1 for new Directory");
		System.out.println("Enter 2 for publishing Directory Stats");
		System.out.println("Enter 3 to sort files by Size Factor");
		System.out.println("Enter 4 to sort files by FileName");
		System.out.println("Enter 5 to sort files by number of lines");
		System.out.println("Enter 6 to sort files by number of words");
		System.out.println("Enter 7 to search by given keyword in Directory");
		System.out.println("Enter 8 to search by given filename in Directory");
		System.out.println("Enter 9 to Exit from Interface");
	}
	public void add_New_Directory_In_Process(String dir,Main_Calls main_Calls)
	{
		entry_Of_Processed_Directories.put(dir,main_Calls);
	}
	public void add_Current_Directory(String dir)
	{
		directory_Entries.add(dir);
	}
	public boolean have_Previously_Processed(String dir)
	{
		return entry_Of_Processed_Directories.containsKey(dir);
	}
	public Main_Calls get_My_Processings(String dir)
	{
		return entry_Of_Processed_Directories.get(dir);
	}
}
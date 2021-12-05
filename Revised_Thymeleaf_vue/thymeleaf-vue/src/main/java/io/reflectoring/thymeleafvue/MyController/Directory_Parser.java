package io.reflectoring.thymeleafvue.MyController;
import Backend_Apis.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
//@SpringBootApplication
@Controller
//@RequestMapping(value="/")

public class Directory_Parser{
	String curr_running_dir="";
	HashMap<String,Main_Calls> entry_Of_Processed_Directories=new HashMap<>();
	ArrayList<String> directory_Entries=new ArrayList<String>();
	ExecutorService executor = Executors.newFixedThreadPool(5);
	@GetMapping("/Indexing_Directory_System/Process_This_Directory")
	public String get_My_Directory()
	{
		return "Process_This_Directory";
	}
	
	@PostMapping("/Indexing_Directory_System/Process_This_Directory")
	public String process_My_Directory(@RequestParam String Directory_Name,Model model)throws Exception
	{
		File new_Dir=new File(Directory_Name);
		curr_running_dir=new_Dir.getAbsolutePath();
		if(!have_Previously_Processed(curr_running_dir) && new_Dir.exists())
		{
			Runnable worker=new Main_Calls(curr_running_dir);
			executor.execute(worker);
			add_New_Directory_In_Process(curr_running_dir,((Main_Calls)worker));
		}
		return "Options";
	}
//	@GetMapping("/Indexing_Directory_System/Process_This_Directory/Options")
//	public String process_My_Directory(Model model)throws Exception
//	{
//		return "Options";
//	}
	@PostMapping("/Indexing_Directory_System/Process_This_Directory/Options")
	public String Get_Directory_Info(@RequestParam String options_for_directory,Model model)throws Exception
	{
		String text="";
		String path="";
	switch(options_for_directory)
	{
		case "1":
		{
			if(!curr_running_dir.equals(""))
			{
				Main_Calls main_Calls=get_My_Processings(curr_running_dir);
				main_Calls.Publish_Directory_Stats();
				path=curr_running_dir+File.separator+"Published_Directory_Stats.txt";
			}
			else
			{
				return "Options";
			}		
			break;
		}
		case "2":
		{
			if(!curr_running_dir.equals(""))
			{
				Main_Calls main_Calls=get_My_Processings(curr_running_dir);
				main_Calls.sort_By_Factor("By_Size");
				path=curr_running_dir+File.separator+"Sorted_File_Details.txt";
			}
			else
			{
				return "Options";
			}
			break;
		}
		case "3":
		{
			if(!curr_running_dir.equals(""))
			{
				Main_Calls main_Calls=get_My_Processings(curr_running_dir);
				main_Calls.sort_By_Factor("By_FileName");
				path=curr_running_dir+File.separator+"Sorted_File_Details.txt";
			}
			else
			{
				return "Options";
			}
			break;
		}
		case "4":
		{
			if(!curr_running_dir.equals(""))
			{
				Main_Calls main_Calls=get_My_Processings(curr_running_dir);
				main_Calls.sort_By_Factor("By_Number_Of_Lines");
				path=curr_running_dir+File.separator+"Sorted_File_Details.txt";
			}
			else
			{
				return "Options";
			}
			break;
		}
		case "5":
		{
			if(!curr_running_dir.equals(""))
			{
				Main_Calls main_Calls=get_My_Processings(curr_running_dir);
				main_Calls.sort_By_Factor("By_Number_Of_Words");
				path=curr_running_dir+File.separator+"Sorted_File_Details.txt";
			}
			else
			{
				return "Options";
			}
			break;
		}
		case "6":
			return "search_file_or_keyword";
		default:
			return "Options";
	}
    	File_Read read_result=new File_Read(path);
    	text=read_result.file_To_Content();
		model.addAttribute("data", text);
		//return new File(curr_running_dir+File.separator+"Published_Directory_Stats.txt").toString();
		return "Result";
	}
	@PostMapping("/Indexing_Directory_System/Process_This_Directory/Options/search")
	public String search_radio(@RequestParam String options_for_search,Model model)throws Exception
	{
		switch(options_for_search)
		{
			case "1":
				return "search";
			case "2":
				return "search_file";
		}
		return null;
	}
	@PostMapping("/Indexing_Directory_System/Process_This_Directory/Options/search/file")
	public String search_this_file(@RequestParam String filename,Model model)throws Exception
	{
		Main_Calls main_Calls=get_My_Processings(curr_running_dir);
		ArrayList<String> All_Paths=new ArrayList<>();
		for(Map.Entry<String,String> entry:main_Calls.search_By_Filename(filename).entrySet())
		{
			All_Paths.add(entry.getKey());
		}
		model.addAttribute("Files_List", All_Paths);
		return "result_FilePaths";
	}
	@PostMapping("/Indexing_Directory_System/Process_This_Directory/Options/search/keyword")
	public String search_this_keyword(@RequestParam String keyword,Model model)throws Exception
	{
		String text="";
		String path="";
		if(!curr_running_dir.equals(""))
		{
			Main_Calls main_Calls=get_My_Processings(curr_running_dir);
			main_Calls.search_By_Keyword(keyword.toLowerCase());
			path=curr_running_dir+File.separator+"Searched_Keyword.txt";
		}
		File_Read read_result=new File_Read(path);
    	text=read_result.file_To_Content();
		model.addAttribute("data", text);
		return "Result";
	}
//	@PostMapping(value="/Indexing_Directory_System/Process_This_Directory/Options", params="Sort_By_Size")
//	@ResponseBody
//	public String Get_Directory_Sorted_By_Size(Model model)throws Exception
//	{
//		if(!curr_running_dir.equals(""))
//		{
//			Main_Calls main_Calls=get_My_Processings(curr_running_dir);
//			main_Calls.sort_By_Factor("By_Size");
//			System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
//		}
//		return new File(curr_running_dir+File.separator+"Sorted_File_Details.txt").toString();
//	}
//	@PostMapping(value="/Indexing_Directory_System/Process_This_Directory/Options", params="Sort_By_FileName")
//	@ResponseBody
//	public String Get_Directory_Sorted_By_FileName(Model model)throws Exception
//	{
//		if(!curr_running_dir.equals(""))
//		{
//			Main_Calls main_Calls=get_My_Processings(curr_running_dir);
//			main_Calls.sort_By_Factor("By_FileName");
//			System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
//		}
//		return new File(curr_running_dir+File.separator+"Sorted_File_Details.txt").toString();
//	}
//	@PostMapping(value="/Indexing_Directory_System/Process_This_Directory/Options", params="Sort_By_Number_Of_Lines")
//	@ResponseBody
//	 public String Get_Directory_Sorted_By_Number_Of_Lines(Model model)throws Exception
//	{
//		if(!curr_running_dir.equals(""))
//		{
//			Main_Calls main_Calls=get_My_Processings(curr_running_dir);
//			main_Calls.sort_By_Factor("By_Number_Of_Lines");
//			System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
//		}
//		return new File(curr_running_dir+File.separator+"Sorted_File_Details.txt").toString();
//	}
//	@PostMapping(value="/Indexing_Directory_System/Process_This_Directory/Options", params="Sort_By_Number_Of_Words")
//	@ResponseBody
//	public String Get_Directory_Sorted_By_Number_Of_Words(Model model)throws Exception
//	{
//		if(!curr_running_dir.equals(""))
//		{
//			Main_Calls main_Calls=get_My_Processings(curr_running_dir);
//			main_Calls.sort_By_Factor("By_Number_Of_Words");
//			System.out.println("Details in "+curr_running_dir+File.separator+"Sorted_File_Details.txt");
//		}
//		return new File(curr_running_dir+File.separator+"Sorted_File_Details.txt").toString();
//	}
//	@PostMapping(value="/Indexing_Directory_System/Process_This_Directory/Options", params="Search_This_Keyword")
//	@ResponseBody
//	public String Searching_Directory_For_Keywords(@RequestParam String Search_This_Keyword)throws Exception
//	{
//		if(!curr_running_dir.equals(""))
//		{	
//			Main_Calls main_Calls=get_My_Processings(curr_running_dir); 
//			main_Calls.search_By_Keyword(Search_This_Keyword.toLowerCase());
//			System.out.println("Details in"+curr_running_dir+File.separator+"Searched_Keyword.txt");
//		}
//		return new File(curr_running_dir+File.separator+"Searched_Keyword.txt").toString();
//	}
	public void add_New_Directory_In_Process(String dir,Main_Calls main_Calls)throws Exception
	{
		entry_Of_Processed_Directories.put(dir,main_Calls);
	}
	public void add_Current_Directory(String dir)throws Exception
	{
		directory_Entries.add(dir);
	}
	public boolean have_Previously_Processed(String dir)throws Exception
	{
		return entry_Of_Processed_Directories.containsKey(dir);
	}
	public Main_Calls get_My_Processings(String dir)throws Exception
	{
		return entry_Of_Processed_Directories.get(dir);
	}
}
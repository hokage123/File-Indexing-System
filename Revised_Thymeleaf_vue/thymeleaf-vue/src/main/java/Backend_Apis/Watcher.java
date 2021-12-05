package Backend_Apis;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.nio.file.ClosedWatchServiceException;
class Watcher implements Runnable
{
	private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private boolean exit;
    private String name;
    private Path baseDir;
	private Read_Directory read_Directory;
	private Write_Directory write_Directory;
	private Process_My_Files process_My_Files;
	private HashSet<String> output_Files;
    Thread t;
	private File_Searcher my_File_Searcher;
    /**
     * Creates a WatchService and registers the given directory
     */
    Watcher(String dir,Read_Directory read_Directory,Write_Directory write_Directory,Process_My_Files process_My_Files,File_Searcher my_File_Searcher) throws IOException {
        baseDir =Paths.get(dir);
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
        exit = false;
        name = "WatchDirThread";
        t = new Thread(this, name);
        walkAndRegisterDirectories(baseDir);
        t.start();
		this.read_Directory=read_Directory;
		this.write_Directory=write_Directory;
		this.process_My_Files=process_My_Files;
		output_Files=new HashSet<>();
		this.my_File_Searcher=my_File_Searcher;
		output_Files.add("output.txt");
		output_Files.add("Sorted_File_Details.txt");
		output_Files.add("Published_Directory_Stats.txt");
		output_Files.add("Searched_Keyword.txt");
    }
 
    /**
     * Register the given directory with the WatchService; This function will be called by FileVisitor
     */
    private void registerDirectory(Path dir) throws IOException 
    {
        WatchKey key = dir.register(watcher,ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }
 
    /**
     * Register the given directory, and all its sub-directories, with the WatchService.
     */
    private void walkAndRegisterDirectories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
 
    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() throws InterruptedException{
        while(!exit){
            try { 
                Thread.sleep(100); 
            } 
            catch (InterruptedException e) { 
                System.out.println("Caught:" + e); 
            } 
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (ClosedWatchServiceException x) {
                return;
            }
 
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();
 
                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);
 
                // if directory is created, and watching recursively, then register it and its sub-directories
                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) {
                            walkAndRegisterDirectories(child);
                            //read_Directory.add_directory(child.toString());
                        }
                        else
                        {
                        	File temp_file=new File(child.toString());
                        	try
                        	{
	                        	if(!output_Files.contains(temp_file.getName()))
	                        	process_My_Files.process_My_File(child.toString(),my_File_Searcher);
                        	}
                        	catch(Exception expt)
                        	{}
                        }
                    } catch (IOException x) {
                        // do something useful
                    }
                }
                else if(kind == ENTRY_DELETE){
                   
                }
            }
 
            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
 
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    /** 
     *This function is used initiate/start the thread execution
     */
    public void run(){
        try{
            processEvents();
        }
        catch(InterruptedException x){

        }
    }


    /**
     * This function is used to stop the execution of the thread bysetting exit to true and terminating the watchService 
     */
    public void stop() throws IOException{ 
        exit = true; 
        watcher.close();
    }
	}	
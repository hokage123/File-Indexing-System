package Backend_Apis;
import java.io.*;
import java.util.*;
class Sort_By_Filename implements Comparator<Candidate>
{
    
    public int compare(Candidate a, Candidate b)
    {
        return a.filename.compareTo(b.filename);
    }
}
package Backend_Apis;
import java.io.*;
import java.util.*;
class Sort_By_Number_Of_Words implements Comparator<Candidate>
{
    
    public int compare(Candidate a, Candidate b)
    {
        return a.number_Of_Words.compareTo(b.number_Of_Words);
    }
}
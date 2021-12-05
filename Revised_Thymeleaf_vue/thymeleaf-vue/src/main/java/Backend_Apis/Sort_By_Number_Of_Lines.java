package Backend_Apis;
import java.io.*;
import java.util.*;
class Sort_By_Number_Of_Lines implements Comparator<Candidate>
{
    
    public int compare(Candidate a, Candidate b)
    {
        return a.number_Of_Lines.compareTo(b.number_Of_Lines);
    }
}
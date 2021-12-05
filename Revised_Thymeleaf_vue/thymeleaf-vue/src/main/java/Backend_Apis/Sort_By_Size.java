package Backend_Apis;
import java.util.*;
class Sort_By_Size implements Comparator<Candidate>
{
    
    public int compare(Candidate a, Candidate b)
    {
        return a.size.compareTo(b.size);
    }
}
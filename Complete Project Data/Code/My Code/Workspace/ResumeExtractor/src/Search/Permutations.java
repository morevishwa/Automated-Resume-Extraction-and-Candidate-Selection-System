package Search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations<T> 
{
	static ArrayList<List<Integer>> combination=new ArrayList<List<Integer>>();
	
//	public static void main(String[] args) {
//		String selectedInput[]=new String[20];
//		selectedInput[0]="java";
//		selectedInput[1]="c";
//		selectedInput[2]="1st class";
//		selectedInput[3]="pune";
//		
//		PermutationsCollection(selectedInput,4);
//	}
    public static String PermutationsCollection(String[] selectedInput,int key) throws Exception 
    {
        Permutations<Integer> obj = new Permutations<Integer>();
        Collection<Integer> input = new ArrayList<Integer>();
        
        ArrayList<ArrayList<String>> combinationStringSet=new ArrayList<ArrayList<String>>();
        
        for(int index=0;index < key;index++)
        {
        	input.add(index);
        }
                System.out.println(input);
        Collection<List<Integer>> output = obj.permute(input);
        int k = 0;
        Set<List<Integer>> pnr = null;
        ArrayList<List<Integer>> simple=new ArrayList<List<Integer>>();
        for (int i = 0; i <= input.size(); i++) 
        {
            pnr = new HashSet<List<Integer>>();
            for(List<Integer> integers : output)
            {
            	pnr.add(integers.subList(i, integers.size()));
            	simple.add(integers.subList(i, integers.size()));
            	uniqueArray(integers.subList(i, integers.size()));
            }
            k = input.size()- i;
            System.out.println("P("+input.size()+","+k+") :"+ 
            "Count ("+pnr.size()+") :- "+pnr);         
         }
        int count=1;
        int size=pnr.size();
        for(List<Integer> integers : combination)
        {
        	ArrayList<String> stringCollection=new ArrayList<String>();
        	for(Integer no : integers)
        	{
        		int index=(int)no;
        		stringCollection.add(selectedInput[index]);
        	}
        	System.out.print("\n"+count+"\t"+integers+"\t"+stringCollection);
        	count++;        	
        	combinationStringSet.add(stringCollection);
        }
        
       String path = ConbinationListOFResume.searchReume(combinationStringSet, selectedInput);
       return path;
    }
    
    public Collection<List<T>> permute(Collection<T> input) 
    {
        Collection<List<T>> output = new ArrayList<List<T>>();
        
        if (input.isEmpty()) 
        {
            output.add(new ArrayList<T>());
            return output;
        }
        List<T> list = new ArrayList<T>(input);
        T head = list.get(0);
        List<T> rest = list.subList(1, list.size());
        for (List<T> permutations : permute(rest)) 
        {
            List<List<T>> subLists = new ArrayList<List<T>>();
            for (int i = 0; i <= permutations.size(); i++) 
            {
                List<T> subList = new ArrayList<T>();
                subList.addAll(permutations);
                subList.add(i, head);
                subLists.add(subList);
            }
            output.addAll(subLists);
        }
        return output;
    }
        
    public static void uniqueArray(List<Integer> integers)
    {
    	 if (integers.size()>0 && integers!=null) 
         {
             if(combination.size()==0)
             {
            	 combination.add(integers);
             }
             else
             {
            	 int flag=0;
            	 for(List<Integer> integerCollection : combination)
                 {
            		 int count=0;
            		 int size=integerCollection.size();
            		 for(Integer no:integers)
            		 {
            			int reply=compaire(no,integerCollection);
            			count=count+reply;            			
            		 }
            		 if(count==size)
            		 {
            			 flag=1;
            			 break;
            		 }
                 }
            	 if(flag==0)
            	 {
            		 combination.add(integers);
            	 }
             }
         }
    }
	private static int compaire(Integer no, List<Integer> integerCollection) 
	{
		for(Integer integerCollectionNo:integerCollection)
		{
			if(integerCollectionNo.equals(no))
			{
				return 1;
			}
		}
		return 0;
	}
}
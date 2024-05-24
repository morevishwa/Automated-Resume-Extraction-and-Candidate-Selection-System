package Upload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EmailExtractor
{
	
	
	public static String getEmail(String line)
        {
		ArrayList<String> emailList = new ArrayList();
		String Email=null;
        final String RE_MAIL = "([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})";
	    Pattern p = Pattern.compile(RE_MAIL);
	    Matcher m = p.matcher(line);
	    while(m.find())
            {
                if(!emailList.contains(m.group(1)))
                {
		        emailList.add(m.group(1));	    	                                  
                }
	    }
            for(int i=0;i<emailList.size();i++)
            {
            	if(i==0)
            		Email=emailList.get(i);            		
                System.out.println(emailList.get(i));
            }
            return Email;
	}
	
   public static String readFile( String finlename)
        {
		String filetoRead = finlename;
		String email=null;
		System.out.println("Email extract file nm:"+filetoRead);
		try{
		    FileInputStream fstream = new FileInputStream(filetoRead);

		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));

		    String strLine ="";

		    while ((strLine = br.readLine()) != null)   {
		    	//fileContent += strLine+"\n";
                       // System.out.println("In readFile id");               
		    	email=getEmail(strLine);
		    	if(email!=null)
		    		break;
		    }
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
		    //String emailID=emailList.get(0);
		    return email;		   
	}
}

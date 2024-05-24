function validateForm()
{
var x=document.forms["upload"]["file"].value;
if (x==null || x=="")
  {
  alert("Please Enter the File Name");
  return false;
  }
}

function myFunction(email) {
	alert("I am an alert box! " + email);
	var user='<%= session.getAttribute("currentSessionUser") %>';
	alert("2");
   	var msg1 = ShortListMassage.filename(email,user) ;
   	alert(msg1);   	
	
}

function clearText()
{
    document.getElementById('username').value='';
    document.getElementById('password').value='';
}

function loginvalidation()
{
    var user=document.forms["login"]["username"].value;
    var pass=document.forms["login"]["password"].value;

    if(user==null || user=="")
        {
            alert("Please Enter the User Name");
             return false;
        }

        if(pass==null || pass=="")
        {
            alert("Please Enter the Password");
             return false;
        }
}

function validate(theForm){
	  if(theForm.Designation.value.length==0){
	  alert("Designation can't be blank");
	  theForm.Designation.focus();
	   return false;
	  }else if(theForm.Skills.value.length==0){
	  alert("Skills can't be blank");
	  theForm.Skills.focus();
	  return false;
	  }else if(theForm.Experience.value.length==0){
	  alert("Experience can't be blank");
	  theForm.Experience.focus();
	  return false;
	  }
	  else if(theForm.Keywords.value.length==0){
		  alert("Keywords can't be blank");
		  theForm.Keywords.focus();
		  return false;
		  }
	  else if(theForm.Location.value.length==0){
		  alert("Location can't be blank");
		  theForm.Location.focus();
		  return false;
		  }
	}
function validateKeyword(theForm){
	  if(theForm.keyword_desc.value.length==0){
	  alert("keyword_desc can't be blank");
	  theForm.name.focus();
	   return false;
	  }
}
function validateReg(theForm){
	  if(theForm.name.value.length==0){
	  alert("Name can't be blank");
	  theForm.name.focus();
	   return false;
	  }else if(theForm.address.value.length==0){
	  alert("address can't be blank");
	  theForm.address.focus();
	  return false;
	  }else if(theForm.contactnumber.value.length==0){
	  alert("contactnumber can't be blank");
	  theForm.contactnumber.focus();
	  return false;
	  }
	  else if(theForm.emailid.value.length==0){
		  alert("emailid can't be blank");
		  theForm.emailid.focus();
		  return false;
		  }
	  else if(theForm.password.value.length==0){
		  alert("password can't be blank");
		  theForm.password.focus();
		  return false;
		  }
	  else if(theForm.confirmpassword.value.length==0){
		  alert("confirmpassword can't be blank");
		  theForm.confirmpassword.focus();
		  return false;
		  }
	  else if(theForm.birthdate.value.length==0){
		  alert("birthdate can't be blank");
		  theForm.birthdate.focus();
		  return false;
		  }
	  else if(theForm.gender.value.length==0){
		  alert("gender can't be blank");
		  theForm.gender.focus();
		  return false;
		  }	
	  
	  var x = theForm.contactnumber.value;
	    if(isNaN(x)|| x.indexOf(" ")!=-1)
		{
	         alert("Enter numeric value");
			 return false;
	    }
	    if (x.length != 10)
		{
    			alert("enter 10 characters contact no "); 
				return false;
	    }
	       			 
	  var email = theForm.emailid.value;
	  var atpos=email.indexOf("@");
	  var dotpos=email.lastIndexOf(".");
	  if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
	    {
	    alert("Not a valid e-mail address");
	    return false;
	    }
	    
	    var inputtxt = theForm.password.value;
	    if (inputtxt.length < 8)
		{
	    	alert('Password should be between 8 to 15 characters');
	    	theForm.contactnumber.focus();
				return false;
	    }
			  			
	    if (theForm.password.value != theForm.confirmpassword.value) { 
	    	   alert("Your password and confirmation password do not match.");
	    	   theForm.confirmpassword.focus();
	    	   return false; 
	    	}	    	   	    	       			 	       			
	}

function validatechange(theForm)
{
	if(theForm.user.value.length==0)
	 {
		  alert("User name can't be blank");
		  theForm.name.focus();
		  return false;
	 }  
	if(theForm.OldPassword.value.length==0)
	{
		  alert("Old Password can't be blank");
		  theForm.name.focus();
		  return false;
	  }
	  else if(theForm.NewPassword.value.length==0)
	  {
		  alert("password can't be blank");
		  theForm.password.focus();
		  return false;
	  }
	  else if(theForm.confirmPassword.value.length==0)
	  {
		  alert("confirmpassword can't be blank");
		  theForm.confirmpassword.focus();
		  return false;
	  }	    
	  
	  var inputtxt = theForm.password.value;
	  if (inputtxt.length < 8)
	  {
		  alert('Password should be between 8 to 15 characters');
		  theForm.contactnumber.focus();
		  return false;
	  }
			  			
	  if (theForm.NewPassword.value != theForm.confirmPassword.value) 
	  { 
		  alert("Your password and confirmation password do not match.");
		  theForm.confirmpassword.focus();
		  return false; 
	  }	    	   	    	       			 	       			
	}


function validateforgotpassword(theForm)
{
	var email = theForm.emailid.value;
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
	{
		alert("Not a valid e-mail address");
		return false;
	}
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Welcome</title>
 <link rel="stylesheet" href="GuestBook.css">  
</head>

<body>
  <!-- multistep form -->
<form id="msform" name="myForm" action = "welcome.jsp">
  <!-- progressbar -->
  <ul id="progressbar">
    <li class="active"></li>
    <li></li>
    <li></li>
  </ul>
  <!-- fieldsets -->
  <fieldset>
    <h2 class="fs-title">We would love to know about you.</h2>
    <h3 class="fs-subtitle">What's Your Name</h3>
    <input type="text" name="fname" placeholder="First Name" value = " "/>
	 <div id = "fname_error" class="val_error"></div>
    <input type="text" name="lname" placeholder="Last Name" />
	 <div id = "lname_error" class="val_error"></div>
    <h3 class="fs-subtitle">Where are you visiting from?</h3>
    <input type="text" name="stAddress" placeholder="Street Address"/>
	<input type="text" name="city" placeholder="City"/>
    <input type="text" name="state" placeholder="State"/>
    <input type="text" name="zip" placeholder="Zip Code"/>
    <input type="text" name="country" placeholder="Country"/>
     
    <input type="button" name="next" class="next action-button" value="Next" />
  </fieldset>
  
  
  <fieldset>
    <h2 class="fs-title">Great!</h2>
    <h3 class="fs-subtitle">Did you stay in Monroe-West Monroe Hotel?</h3>
	  <input type="radio" name="radio" id="radio1" class="radio"/>
	  <label for="radio1">Yes</label>
	  <input type="radio" name="radio" id="radio2" class="radio"/>
	  <label for="radio2">No</label>
  		<h3 class="fs-subtitle">How did you hear about Monroe-West Monroe CVB?</h3>
  		<select>
  		<option value="billboard">Billboard</option>
  		<option value="interstate sign">Interstate Sign</option>
  		<option value="other">Other</option>
		</select>
		<h3 class="fs-subtitle">Where are you travelling?</h3>
		<input type="text" name="destination" placeholder="Enter City Name"/>
	  <input type="button" name="previous" class="previous action-button" value="Previous" />
	  <input type="button" name="next" class="next action-button" value="Next" />
  </fieldset> 
  <fieldset>
    <h2 class="fs-title">Almost done :)</h2>
    <h3 class="fs-subtitle">Why are you travelling?</h3>
    <select>
  	<option value="business">Business</option>
  	<option value="pleasure">Pleasure</option>
  	<option value="other">Other</option>
	</select>
	<h3 class="fs-subtitle">How many people are travelling with you?</h3>
	<input type="text" name="party" placeholder="Enter a number"/>
    <h3 class="fs-subtitle">Would you be interested to learn more about Monroe and West Monroe?</h3>
<input type="radio" name="radio" id="radio3" class="radio"/>
<label for="radio3">Yes, I will like to.</label>

<input type="radio" name="radio" id="radio4" class="radio"/>
<label for="radio4">No, I am good.</label>
  	<h3 class="fs-subtitle">Your email address is required to keep you informed about our beautiful city and hotels.</h3>
	<input type="text" name="email" placeholder="Email" />
  	<input type="button" name="previous" class="previous action-button" value="Previous" />
  	<input type="submit" name="submit" class="submit action-button" value="Submit"/>
  </fieldset>
</form>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
<script src="GuestBook.js"></script>
</body>
</html>
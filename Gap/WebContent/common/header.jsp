<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>

<style>
* {
  box-sizing: border-box;
}

body {
  font: 16px Arial;  
}

/the container must be positioned relative:/
.autocomplete {
  position: relative;
  display: inline-block;
}

input {
  border: 1px solid transparent;
  background-color: #f1f1f1;
  padding: 10px;
  font-size: 16px;
}

input[type=text] {
  background-color: #f1f1f1;
  width: 100%;
  height: 30px;
  border-radius: 5px;
  
}

input[type=submit] {
  background-color: #e6e6fa;
  color: bleck;
  cursor: pointer;
  height: 30px;
  border-radius: 5px;
}

.autocomplete-items {
  position: absolute;
  border: 1px solid #d4d4d4;
  border-bottom: none;
  border-top: none;
  z-index: 99;
  /position the autocomplete items to be the same width as the container:/
  top: 100%;
  left: 0;
  right: 0;
}

.autocomplete-items div {
  padding: 10px;
  cursor: pointer;
  background-color: #fff; 
  border-bottom: 1px solid #d4d4d4; 
}

/when hovering an item:/
.autocomplete-items div:hover {
  background-color: #e9e9e9; 
}

/when navigating through the items using the arrow keys:/
.autocomplete-active {
  background-color: DodgerBlue !important; 
  color: #ffffff; 
}
</style>

<header>	

<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>
	
	<div>
		<p align="left">
		<a href="Index.jsp"><img src = "Elementi/logo.png" width="75" height="75"></a>
		<p align="center">
	</div>
	
	<!-- <div> -->
	
	
<form autocomplete="off" action="RicercaControl" method="get">
  <div class="autocomplete" style=" width:150px; top:20px; height: 35px; ">
    <input id="myInput"  type="text" name="ricerca" placeholder="Ricerca per nome">
  </div>
  <input type="submit" style="height: 30px">
</form>

	 <!-- </div> -->
	 
	<%
	UtenteBean utente = new UtenteBean();
	HttpSession sessione = request.getSession(false);
	   if (sessione != null)
	   {
			utente = (UtenteBean) sessione.getAttribute("utente");
			if(utente != null)
			{
			%>
			<div class="sposta" >
				<p class="sposta" id="iconaProfilo"><a href="profilo.jsp"><img src = "Elementi/profilo.png" width="30" height="30"></a>
				<%=utente.getNome()%></p>
			
			<% 
			CarrelloBean carrello = (CarrelloBean) sessione.getAttribute ("carrello");
			if(carrello != null)
			{ %>
				<p class="sposta"><a href="logout.jsp"><img src = "Elementi/logout.png" width="35" height="35"></a>
				<a href="carrello.jsp"><img src = "Elementi/carrello.png" width="35" height="35"></a></p>
				<span id="carrello"><%=carrello.getQuantita() %></span>
			</div>
			<%}
			}
	 		else 
	 		{
			 %>
			 
	<div>
	 	<p class="logRec" align="right" >
	 	<a  class="link" title="login" href="loginUser.jsp"> LOGIN</a> 
	 	<a  class="link" title="registrazione" href="Registrazione.jsp" >/REG</a>
		</p>
	</div> 
		
		<%	}
		} 	%>
</header>

<nav id="navbar">
	<ul><h3>
	<li><a class="dropdown" href="#">Poltrone</a>
		<div class="dropdown-content">
			<a href="./TipologiaControl?tipo=manuale">Manuale</a>
			<a href="./TipologiaControl?tipo=elettrica">Elettricha</a>
			<a href="./TipologiaControl?tipo=pouf">Pouf</a>
		</div>
	</li>
	<li><a href="#" class="dropdown">Pareti Attrezzate</a>
		<div class="dropdown-content">
			<a href="./TipologiaControl?tipo=moderna">Moderna</a>
			<a href="./TipologiaControl?tipo=classica">Classica</a>
		</div>
	</li>
	<li><a href="./chiSiamo.jsp">Chi siamo</a></li>
	
	<% 
	if(utente != null){
		 %>
		 <li id="profilo"><a href="./AdminControl">Profilo</a></li>
		<% 	
	if(utente.getRuolo().equals("amministratore"))
		{%>
		<li><a href="./AdminControl">Amministratore</a></li>
		
		<%} }%>
</h3></ul>
</nav>

<script>

function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /execute a function when someone writes in the text field:/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /close any already open lists of autocompleted values/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /create a DIV element that will contain the items (values):/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /append the DIV element as a child of the autocomplete container:/
      this.parentNode.appendChild(a);
      /for each item in the array.../
      for (i = 0; i < arr.length; i++) {
        /check if the item starts with the same letters as the text field value:/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /create a DIV element for each matching element:/
          b = document.createElement("DIV");
          /make the matching letters bold:/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /insert a input field that will hold the current array item's value:/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /execute a function when someone clicks on the item value (DIV element):/
          b.addEventListener("click", function(e) {
              /insert the value for the autocomplete text field:/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /execute a function presses a key on the keyboard:/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /and and make the current item more visible:/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /and and make the current item more visible:/
        addActive(x);
      } else if (e.keyCode == 13) {
        /If the ENTER key is pressed, prevent the form from being submitted,/
        e.preventDefault();
        if (currentFocus > -1) {
          /and simulate a click on the "active" item:/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /a function to classify an item as "active":/
    if (!x) return false;
    /start by removing the "active" class on all items:/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /add class "autocomplete-active":/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /a function to remove the "active" class from all autocomplete items:/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /execute a function when someone clicks in the document:/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
  });
}

/An array containing all the country names in the world:/
var countries = ["zeus","arrow","markus","hugo","robert","silandrio","rana","zelbio","attr1","attr2","attr3","attr4"];

/initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:/
autocomplete(document.getElementById("myInput"), countries);


function toggle() 
{
	if(document.getElementById("navbar").style.display=="none"){
		document.getElementById("navbar").style.display="block";
	}else{
		document.getElementById("navbar").style.display="none";
		}
}
</script>
function validationGmail(){
const emailInput = document.getElementById("gmailID").value;
const emailError = document.getElementById("emailError");

const emailRegex = /^[a-zA-Z0-9._]+@gmail\.com$/;
if(!emailRegex.test(emailInput)){
emailError.textContent = "enter a valid Gmail address";
}else{
emailError.textContent="";
}
}

function checkEmail(){
const emailInput = document.getElementById("gmailID").value;
const emailError = document.getElementById("emailError");

const xhttp=new XMLHttpRequest();

xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/checkEmail/"+emailInput);
xhttp.send();
xhttp.onload=function(){
emailError.innerHTML=this.responseText;
}

}





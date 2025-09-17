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

function validationName(){
const nameInput = document.getElementById("inputName").value;
const nameError = document.getElementById("nameError");

const name = ^[A-Za-z\s]+$ ;
if(!name.test(nameInput)){
nameError.textContent = "allows only letters (A–Z, a–z) and spaces."
}else{
nameError.textContent="";
}
}

function validationPhoneNo(){
const inputPhoneNo = document.getElementById("inputPhone").value;
const inputPhoneNoError = document.getElementById("phoneNoError");

const phoneRex = /^(?:\+91|91)?[6-9]\d{9}$/;
if(!phoneRex.test(inputPhoneNo)){
inputPhoneNoError.textContent = "Please enter a valid Indian phone number."
}else{
inputPhoneNoError.textContent="";
}
}


function validateLicenseNo() {
    const licenseInput = document.getElementById("licenseNumber").value.trim();
    const licenseError = document.getElementById("licenseError");


    const licenseRex = /^[A-Za-z]{2,5}-?\d{4,10}$/;

    if (!licenseRex.test(licenseInput)) {
      licenseError.textContent = "Please enter a valid License / Reg. No. (e.g., MC-123456)";
    } else {
      licenseError.textContent = "";
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





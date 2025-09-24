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
const name = /^[A-Za-z\s]+$/;
if(!name.test(nameInput)){
nameError.textContent = "allows only letters (A–Z, a–z) and spaces."
}else{
nameError.textContent="";
}
}

function validationAge(){
const inputAge = document.getElementById("ageID").value;
const inputAgeError = document.getElementById("ageError");
if(inputAge < 15){
inputAgeError.textContent = "Age Should be more than 15."
}else{
inputAgeError.textContent="";
}
}

function validationPhoneNo(){
const inputPhoneNo = document.getElementById("inputPhone").value;
const inputPhoneNoError = document.getElementById("phoneNoError");
const phoneRex = /^[6-9]\d{9}$/;
if(!phoneRex.test(inputPhoneNo)){
inputPhoneNoError.textContent = "first digit must be between 6 and 9"
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

function validateQualification() {
    const input = document.getElementById("qualification").value.trim();
    const errorDiv = document.getElementById("qualificationError");
    // Regex: allows letters, dots, spaces, and commas
    const qualificationRegex = /^[A-Za-z.,\s]{2,50}$/;
    if (!qualificationRegex.test(input)) {
        errorDiv.textContent = "Please enter a valid qualification (e.g., MBBS, MD, MS)";
    } else {
        errorDiv.textContent = "";
    }
}


function getDoctorEmail() {
    const doctorSelect = document.getElementById("doctorID");
    const emailInput = document.getElementById("gmailID");
    const selectedOption = doctorSelect.options[doctorSelect.selectedIndex];
    const email = selectedOption.getAttribute("data-email");
    emailInput.value = email || "";
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

//function getDoctorMail(){
//const doctorName = document.getElementById("doctorID").value;
//const doctorNameError = document.getElementById("optionDoctorNameError");
//const xhttp = new XMLHttpRequest();
//xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/getDoctorName/"+doctorName);
//xhttp.send();
//xhttp.onload=function(){
//doctorNameError.innerHTML=this.responseText;
//}
//}



function checkSpecialty(){
const inputSpecialty = document.getElementById("specialty").value;
const patientsError = document.getElementById("specialtyError");
const xhttp = new XMLHttpRequest();
xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/getDoctorName/"+inputSpecialty);
xhttp.send();
xhttp.onload=function(){
patientsError.innerHTML = this.responseText;
}
}

function checkSlotsAssigned(){
const inputSlot = document.getElementById("timeID").value;
const inputGmail = document.getElementById("gmailID").value;
const existSlot = document.getElementById("existSlotsError");
const xhttp = new XMLHttpRequest()
xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/getDoctorSlots/"+inputSlot+"/"+inputGmail);
xhttp.send();
xhttp.onload=function(){
existSlot.innerHTML = this.responseText;
}
}


function validateSlotForm() {
    const existSlot = document.getElementById("existSlotsError").innerText.trim();

    if (existSlot !== "") {
        alert("Cannot submit: " + existSlot);
        return false;
    }
    return true;
}



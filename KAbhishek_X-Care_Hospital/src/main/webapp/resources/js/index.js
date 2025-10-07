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
const nameRex = /^[A-Za-z]+(\s[A-Za-z]+)*$/;
if(!nameRex.test(nameInput)){
nameError.textContent = "Numbers and special characters are not allowed"
return
}else{
nameError.textContent="";
return
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
const inputPhone = document.getElementById("inputPhone");
const inputPhoneNo = inputPhone.value;
const inputPhoneNoError = document.getElementById("phoneNoError");

const phoneRex = /^[6-9]\d{9}$/;
const maxLength = 10;

if(inputPhoneNo.length>maxLength){
inputPhone.value = inputPhoneNo.slice(0,maxLength);
inputPhoneNoError.textContent = "Phone number cannot be more than 10 digits"
return
}

if( inputPhoneNo.length > 0 && !/^[6-9]/.test(inputPhoneNo)){
inputPhoneNoError.textContent = "first digit must be between 6 and 9"
return
}

if(inputPhoneNo.length === 10 && !phoneRex.test(inputPhoneNo)) {
       inputPhoneNoError.textContent = "Invalid phone number";
       return;
       }
inputPhoneNoError.textContent="";
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

function clearError(){
document.getElementById("errorID").innerHTML = "";
}


function getDoctorEmailAndID() {
    const doctorSelect = document.getElementById("doctorID");
    const emailInput = document.getElementById("gmailID");
    const selectedOption = doctorSelect.options[doctorSelect.selectedIndex];
    document.getElementById("doctorIDHidden").value = selectedOption.getAttribute("data-id");
    const email = selectedOption.getAttribute("data-email");
    emailInput.value = email || "";
}

function getSlotID() {
const  select = document.getElementById("timeID");
const selectedOption = select.options[select.selectedIndex];
document.getElementById("slotIDHidden").value = selectedOption.getAttribute("data-id");
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


function checkEmail(){
const emailInput = document.getElementById("gmailID").value;
const emailError = document.getElementById("emailError");
const xhttp=new XMLHttpRequest();
xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/checkEmail/"+emailInput);
xhttp.send();
xhttp.onload=function(){
const response = this.responseText;
if(response == "Email not exists"){
emailError.innerHTML = response;
document.getElementById("getOTPButtonID").disabled = true;
}else{
emailError.innerHTML = "";
document.getElementById("getOTPButtonID").disabled = false;
}
}
}

function checkKeys(event) {
  const expError = document.getElementById('experienceError');

  const allowedKeys = [
      'Backspace', 'Tab', 'ArrowLeft', 'ArrowRight',
      'Delete', 'Home', 'End'
    ];

    if (allowedKeys.includes(event.key)) {
      expError.textContent = "";
      return;
    }


    if (
      (event.key >= 'a' && event.key <= 'z') ||
      (event.key >= 'A' && event.key <= 'Z') ||
      ['+', '-', 'e', 'E', '.', ',', '/'].includes(event.key)
    ) {
      event.preventDefault();
      expError.textContent = "Only numbers allowed.";
    } else {
      expError.textContent = "";
    }
  }

function validateExperience(){
 const expInput = document.getElementById('experience');
  const expError = document.getElementById('experienceError');
  const value = expInput.value;

  // Range check
  if (value !== "") {
    const numValue = parseInt(value);
    if (numValue < 0) {
      expError.textContent = "Experience cannot be negative.";
      expInput.value = "";
    } else if (numValue > 60) {
      expError.textContent = "Experience cannot exceed 60 years.";
      expInput.value = "";
    } else {
      expError.textContent = "";
    }
  }
}






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




function validateSlotForm() {
    const existSlot = document.getElementById("existSlotsError").innerText.trim();

    if (existSlot !== "") {
        alert("Cannot submit: " + existSlot);
        return false;
    }
    return true;
}

function validateAdminGmailForm(){
const gmailError = document.getElementById("emailError").innerText;
if(gmailError != ""){
alert("Cannot submit: " + gmailError);
return false;
}
return true;
}





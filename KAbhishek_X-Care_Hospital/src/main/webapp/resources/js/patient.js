function validationName(){
const nameInput = document.getElementById("inputName").value;
const nameError = document.getElementById("nameError");
const nameRex = /^[A-Za-z]+(\s[A-Za-z]+)*$/;
if(!nameRex.test(nameInput)){
nameError.textContent = "Numbers and special characters are not allowed";
return
}else{
nameError.textContent="";
return
}
}

function validationAge(){
const inputAge = document.getElementById("ageID").value;
const inputAgeError = document.getElementById("ageError");
if(inputAge < 1 ){
inputAgeError.textContent = "Age Should be more than 1."
return
}
if(inputAge > 120){
inputAgeError.textContent = "Age Should be below than 120."
return
}
inputAgeError.textContent="";
return
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

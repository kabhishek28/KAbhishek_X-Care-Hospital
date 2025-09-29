
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

function checkSpecialtyDoctor(){
const inputSpecialty = document.getElementById("specialty").value;
const doctorsList = document.getElementById("doctorList");
const xhttp new XMLHttpRequest()
xhttp.open("GET","http://localhost:8080/KAbhishek_X_Care_Hospital/getDoctorList/"+specialty);
xhttp.send();
}
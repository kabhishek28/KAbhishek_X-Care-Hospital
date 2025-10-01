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
if(inputAge < 1){
inputAgeError.textContent = "Age Should be more than 1."
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


function checkSpecialtyDoctor() {
    const inputSpecialty = document.getElementById("specialtyID").value;
    const doctorDropdown = document.getElementById("doctorList");
    const doctorMessage = document.getElementById("doctorMessage");

    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/getDoctorList/" + inputSpecialty);
    xhttp.send();
    xhttp.onload = function () {
        console.log("Raw Response:", this.responseText);

        try {
            const doctors = JSON.parse(this.responseText);

            doctorDropdown.innerHTML = '<option selected disabled>Select doctor</option>';
            doctorMessage.innerHTML = "";

            if (doctors.length === 0) {
                doctorMessage.innerHTML = "Doctor Not exists for this specialty";
            } else {
                doctors.forEach(d => {
                    const opt = document.createElement("option");
                    opt.value = d.id; // better to use d.id if available
                    opt.text = `${d.name} - ${d.specialty}`;
                    doctorDropdown.add(opt);
                });

            }
        } catch (err) {
            console.error("Invalid JSON:", err);
            doctorMessage.innerHTML = "Server did not return valid JSON";
        }
    };
}

function checkSpecialtyDoctorSlot(){
const inputDoctorID = document.getElementById("doctorList").value;
    const appointmentDropdown = document.getElementById("appointmentTime");

const doctorSlotDropdown = document.getElementById("doctorSlotList");
const doctorSlotMessage = document.getElementById("doctorSlotMessage");
console.log(inputDoctorID);

const xhttp = new XMLHttpRequest;
xhttp.open("GET","http://localhost:8080/KAbhishek_X-Care_Hospital/getDoctorSlot/"+inputDoctorID);
xhttp.send();
xhttp.onload = function(){
if (this.status === 200) {
            const slots = JSON.parse(this.responseText); // Parse JSON list
            console.log(slots);

            // Clear existing options except the first
            appointmentDropdown.innerHTML = '<option selected disabled>Select time</option>';

            // Populate the dropdown
            slots.forEach(slot => {
                const option = document.createElement("option");
                option.value = slot; // value can be same as text
                option.textContent = slot;
                appointmentDropdown.appendChild(option);
            });
        } else {
            console.error("Error fetching slots:", this.status);
        }
    };
}




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
    const inputSpecialty = document.getElementById("specialty").value;  // ✅ fixed
    const doctorList = document.getElementById("doctorList");

    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/KAbhishek_X_Care_Hospital/patient/getDoctorList/" + inputSpecialty);
    xhttp.send();
    xhttp.onload = function(){
        const doctors = JSON.parse(this.responseText);  // ✅ will now be JSON
        if(doctors.length === 0){
            doctorList.innerHTML = "<p>Doctor Not exists</p>";
        } else {
            let html = "<ul>";
            doctors.forEach(d => {
                html += `<li>${d.name} - ${d.specialty}</li>`;
            });
            html += "</ul>";
            doctorList.innerHTML = html;
        }
    }
}

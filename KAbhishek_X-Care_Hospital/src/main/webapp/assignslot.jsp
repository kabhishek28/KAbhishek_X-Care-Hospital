<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!--<!doctype html>-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>X-Care Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<style>
    .btn-blink {
      animation: blink 2s infinite;
    }
    @keyframes blink {
      0%   { opacity: 1; }
      50%  { opacity: 0.6; }
      100% { opacity: 1; }
    }
 .dropdown-menu .dropdown-item:hover {
    background-color: #0055aa;
<!--    color: #FFD700 !important;-->
    }
</style>
<main>
    <!-- As a link -->
    <nav class="navbar bg-body-tertiary py-1">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <span><img src="img/Hospital logo3.png" alt="Hospital Logo" width="70" height="60"></span>
                <span><img src="img/Hospital logo4.png" alt="Hospital Name" width="150" height="60"></span>

            </a>
            <div class="" role="group" aria-label="Hospital Actions">
                <a type="button" class="btn btn-success btn-blink shadow-sm  me-2" href="#">Access Lab Reports</a>
                <a type="button" class="btn btn-primary btn-blink shadow-sm  me-2" href="#">Appointment</a>
                <a type="button" class="btn  btn-danger btn-blink me-2 shadow-sm " href="#">Emergency Ambulance</a>
                <a type="button" class="btn btn-outline-dark me-4 shadow" href="adminLogin" >Admin Login</a>
            </div>
        </div>
    </nav>

    <nav class="navbar justify-content-center  " style="background-color: #003366;">
        <ul class="nav nav-underline d-flex align-items-center gap-5">
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="getHome">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="#">About Us</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link text-white dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Doctors</a>
                <ul class="dropdown-menu  shadow" style="background-color: #003366;">
                    <li><a class="dropdown-item text-white" href="registerDoctor">Doctor Registration</a></li>
                    <li><a class="dropdown-item text-white" href="allDoctorsList">UpDate Doctors</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="patient">Patients Registration</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link text-white dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Slot</a>
                <ul class="dropdown-menu  shadow" style="background-color: #003366;">
                    <li><a class="dropdown-item text-white" href="setSlot">Define Slot Timing</a></li>
                    <li><a class="dropdown-item text-white" href="assignSlot">Assign Slot to Doctor</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="#">Health Package</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link text-white dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Hospitals</a>
                <ul class="dropdown-menu  shadow" style="background-color: #003366;">
                    <li><a class="dropdown-item text-white" href="#">Bangalore</a></li>
                    <li><a class="dropdown-item text-white" href="#">Mangalore</a></li>
                    <li><a class="dropdown-item text-white" href="#">Udupi</a></li>
                    <li><a class="dropdown-item text-white" href="#">Pune</a></li>
                    <li><a class="dropdown-item text-white" href="#">Delhi</a></li>
                    <li><a class="dropdown-item text-white" href="#">Goa</a></li>
                </ul>
            </li>
            <li class="nav-item dropdown">

                <a class="nav-link text-white dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Specialities</a>
                <ul class="dropdown-menu shadow" style="background-color: #003366;">
                    <li><a class="dropdown-item text-white " href="#">Anaesthesiology</a></li>
                    <li><a class="dropdown-item text-white" href="#">Bone Marrow</a></li>
                    <li><a class="dropdown-item text-white" href="#">Cardiac Sciences</a></li>
                    <li><a class="dropdown-item text-white" href="#">Critical Care Medicine</a></li>
                    <li><a class="dropdown-item text-white" href="#">Maxillofacial and Dental Surgery</a></li>
                    <li><a class="dropdown-item text-white" href="#">Dentistry</a></li>
                    <li><a class="dropdown-item text-white" href="#">Dermatology</a></li>
                    <li><a class="dropdown-item text-white" href="#">Emergency Medicine</a></li>
                    <li><a class="dropdown-item text-white" href="#">Endocrinology and Diabetology</a></li>
                </ul>
            </li>
        </ul>
    </nav>

</main>
<body>
<h1>Define Slot timing </h1>
<div class="d-flex justify-content-center mt-5">
    <div class="card shadow-lg p-4 rounded-4" style="width: 24rem;">
        <h5 class="text-center mb-4 fw-bold">Check Doctors</h5>
        <form action="findDoctorsSlots" method="post">
            <div class="mb-4">
                <label for="specialty" class="form-label fw-semibold">Specialty</label>
                <select id="specialty" name="specialty" class="form-select" required >
                    <option disabled ${selectedSpecialty == null ? "selected" : ""}>Choose specialty...</option>
                    <option value="CARDIOLOGY" ${selectedSpecialty == 'CARDIOLOGY' ? "selected" : ""}>Cardiology</option>
                    <option value="DERMATOLOGY" ${selectedSpecialty == 'DERMATOLOGY' ? "selected" : ""}>Dermatology</option>
                    <option value="NEUROLOGY" ${selectedSpecialty == 'NEUROLOGY' ? "selected" : ""}>Neurology</option>
                    <option value="ORTHOPEDICS" ${selectedSpecialty == 'ORTHOPEDICS' ? "selected" : ""}>Orthopedics</option>
                    <option value="PEDIATRICS" ${selectedSpecialty == 'PEDIATRICS' ? "selected" : ""}>Pediatrics</option>
                    <option value="PSYCHIATRY" ${selectedSpecialty == 'PSYCHIATRY' ? "selected" : ""}>Psychiatry</option>
                    <option value="RADIOLOGY" ${selectedSpecialty == 'RADIOLOGY' ? "selected" : ""}>Radiology</option>
                    <option value="GENERAL_MEDICINE" ${selectedSpecialty == 'GENERAL_MEDICINE' ? "selected" : ""}>General Medicine</option>
                    <option value="SURGERY" ${selectedSpecialty == 'SURGERY' ? "selected" : ""}>Surgery</option>
                    <option value="GYNECOLOGY" ${selectedSpecialty == 'GYNECOLOGY' ? "selected" : ""}>Gynecology</option>
                    <option value="ENT" ${selectedSpecialty == 'ENT' ? "selected" : ""}>ENT</option>
                    <option value="OPHTHALMOLOGY" ${selectedSpecialty == 'OPHTHALMOLOGY' ? "selected" : ""}>Ophthalmology</option>
                </select>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Check Doctors</button>
            </div>
        </form>
    </div>
</div>

<div>
    <c:if test="${not empty message}">
        <p class="text-danger fw-bold text-center">${message}</p>
    </c:if>
</div>
<div>
    <p class="text-danger fw-bold text-center">${saveMessageError}</p>
    <p class="text-success  fw-bold text-center">${saveMessage}</p>
</div>

<div>
    <c:if test="${not empty doctors}">
        <div class="d-flex justify-content-center mt-5">
            <div class="card shadow-lg p-4 rounded-4" style="width: 24rem;">
                <h5 class="text-center mb-4 fw-bold">Check Doctors</h5>

                <form action="doctorSlotAssign" method="post" onsubmit="return validateSlotForm()">

<!--                    <input type="number" class="form-control" id="docId" name="doctorID" value="${doctor.id}" oninput="validationId()" placeholder="Enter Doctor ID" hidden>-->
                    <div class="mb-4">
                        <label for="doctorID" class="form-label fw-semibold">Doctors</label>
                        <select id="doctorID" typeof="text" name="optionDoctorName" class="form-select" onchange="getDoctorEmailAndID()" required>
                            <option selected disabled>Choose Doctor...</option>
                            <c:forEach var = "doc" items="${doctors}">
                                <option value="${doc.doctorName}" data-id= "${doc.id}" data-email="${doc.doctorEmail}">
                                    ${doc.doctorName}
                                </option>

                            </c:forEach>
                        </select>
                        <input type="hidden" id="doctorIDHidden" name="doctorID">
                    </div>

                    <div class="mb-4">
                        <label for="timeID" class="form-label fw-semibold">Timings Slot</label>
                        <select id="timeID" name="timings" class="form-select" onchange="checkSlotsAssigned(); getSlotID()" required>
                            <option selected disabled>Choose Timings...</option>
                            <c:forEach var = "tim" items="${slots}">
                                <option value="${tim.startTime} to ${tim.endTime}" data-id="${tim.id}">${tim.startTime} to ${tim.endTime}</option>

                            </c:forEach>
                        </select>
                        <div  id="existSlotsError" class="input-text text-danger" style="min-height:25px;"></div>
                        <input type="hidden" id="slotIDHidden" name="slotID">
                    </div>

                    <div class="col-md-6">
                        <label for="gmailID" class="form-label">Email</label>
                        <input type="email" class="form-control" id="gmailID" name="doctorEmail" value="${doctorEmail}" readonly>
                        <div  id="optionDoctorNameError" class="input-text text-danger" style="min-height:25px;"></div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold" for="specialtyID">Specialty</label>
                        <input type="text" class="form-control " id="specialtyID" name="specialty" value="${specialtyy}"  readonly>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Assign Slot</button>
                    </div>
                </form>
            </div>
        </div>
    </c:if>
</div>
<div>
    <p class="text-danger fw-bold text-center">${messageAssign}</p>
    <p class="text-success  fw-bold text-center">${messageAssignSaved}</p>
</div>







<script src="resources/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
<footer>

</footer>
</html>
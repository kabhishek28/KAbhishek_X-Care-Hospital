<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

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
                <a type="button" class="btn btn-outline-dark me-4 shadow" href="admin" >Admin Login</a>
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
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="getDoctor">Doctors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white toggle" aria-current="page" href="getPatients">Patients Registration</a>
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
<div class="d-flex justify-content-center mt-5">
    <div class="card shadow-lg p-4 rounded-4" style="width: 40rem;">
        <h3 class="text-center mb-4">Patient Registration</h3>

        <form class="row g-3" action="patientsForm" method="post">


            <div class="col-md-6">
                <label for="inputName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="inputName" name="patientName" oninput="validationName()" placeholder="John Doe" required>
                <div  id="nameError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>


            <div class="col-md-6">
                <label for="ageID" class="form-label">Age</label>
                <input type="number" class="form-control" id="ageID" name="age" placeholder="30" oninput="validationAge()" min="0" max="120" required>
                <div  id="ageError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>


            <div class="col-md-6">
                <label for="gender" class="form-label">Gender</label>
                <select id="gender" name="gender" class="form-select" required>
                    <option selected disabled>Choose...</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                    <option value="prefer_not_say">Prefer not to say</option>
                </select>
            </div>


            <div class="col-md-6">
                <label for="inputPhone" class="form-label">Contact Number</label>
                <input type="tel" class="form-control" id="inputPhone" name="contact" oninput="validationPhoneNo()" placeholder="+91 9876543210" required>
                <div id="phoneNoError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>


            <div class="col-12">
                <label for="disease" class="form-label">Disease / Symptoms</label>
                <input type="text" class="form-control" id="disease" name="disease" placeholder="Fever, Chest Pain..." required>
            </div>

            <div class="col-md-6">
                <label for="bloodGroup" class="form-label">Blood Group</label>
                <select id="bloodGroup" name="bloodGroup" class="form-select" required>
                    <option selected disabled>Select blood group</option>
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                </select>
            </div>


            <div class="col-md-6">
                <label for="specialty" class="form-label" >Specialty</label>
                <select id="specialty" name="specialty" class="form-select" onchange="checkSpecialty()" required>
                    <option selected disabled>Choose specialty...</option>
                    <option value="CARDIOLOGY">Cardiology</option>
                    <option value="DERMATOLOGY">Dermatology</option>
                    <option value="NEUROLOGY">Neurology</option>
                    <option value="ORTHOPEDICS">Orthopedics</option>
                    <option value="PEDIATRICS">Pediatrics</option>
                    <option value="PSYCHIATRY">Psychiatry</option>
                    <option value="RADIOLOGY">Radiology</option>
                    <option value="GENERAL_MEDICINE">General medicine</option>
                    <option value="SURGERY">Surgery</option>
                    <option value="GYNECOLOGY">Gynecology</option>
                    <option value="ENT">Ent</option>   <!-- (problem: ENT becomes Ent) -->
                    <option value="OPHTHALMOLOGY">Ophthalmology</option>
                </select>
            </div>



            <div class="col-md-6">
                <label for="doctor" class="form-label">Doctor</label>
                <select id="doctor" name="doctor" class="form-select" required>
                    <option selected disabled>Select doctor</option>
                    <!-- Populate dynamically from DB -->
                    <option value="DR_KUMAR">Dr. A. Kumar</option>
                    <option value="DR_MEHTA">Dr. S. Mehta</option>
                    <option value="DR_SINGH">Dr. R. Singh</option>
                </select>
            </div>


            <div class="col-md-6">
                <label for="appointmentTime" class="form-label">Appointment Time</label>
                <select id="appointmentTime" name="appointmentTime" class="form-select" required>
                    <option selected disabled>Select time</option>
                    <!-- Populate dynamically -->
                    <option value="MORNING">09:00 AM - 11:00 AM</option>
                    <option value="NOON">11:00 AM - 01:00 PM</option>
                    <option value="AFTERNOON">02:00 PM - 04:00 PM</option>
                    <option value="EVENING">04:00 PM - 06:00 PM</option>
                </select>
            </div>

            <div class="col-12">
                <label for="address" class="form-label">Address</label>
                <textarea id="address" name="address" class="form-control" rows="2" placeholder="Enter full address"></textarea>
            </div>

            <div class="col-md-6">
                <label for="fees" class="form-label">Consultation Fees</label>
                <input type="number" class="form-control" id="fees" name="fees" placeholder="doctor fees"  min="0" required>
            </div>





            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary w-100">Book Appointment</button>
            </div>
        </form>
    </div>
</div>

<script src="resources/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
</body>

<footer>

</footer>
</html>
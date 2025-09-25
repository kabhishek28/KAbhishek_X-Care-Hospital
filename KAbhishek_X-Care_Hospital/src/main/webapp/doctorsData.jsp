<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>X-Care Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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

    .doctor-card:hover {
        transform: translateY(-8px);
        box-shadow: 0 12px 25px rgba(0, 0, 0, 0.15);
    }

    .doctor-card img:hover {
        transform: scale(1.05);
    }

</style>
<main>
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
                    <li><a class="dropdown-item text-white" href="getDoctor">Doctor Registration</a></li>
                    <li><a class="dropdown-item text-white" href="getUpDatePage">UpDate Doctors</a></li>
                </ul>
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
<div class="container mt-4 ">
    <div class="row justify-content-center align-items-center">
        <c:forEach var="doc" items="${doctorsList}">
            <div class=" col-md-3 card border-5 shadow-lg rounded-4  m-2 doctor-card text-center"
                 style="transition: transform 0.3s ease, box-shadow 0.3s ease; background: linear-gradient(135deg, #ffffff, #f8f9fa);">
                <div class="card-body text-center">
                    <!-- Profile Image -->
                    <img src="download?imagePath=${doc.imagePath}"
                         alt="doctor image"
                         class="rounded-circle shadow-sm mb-3 p-1 bg-light"
                         style="border: 4px solid #003366; width: 120px; height: 120px; object-fit: cover; transition: transform 0.3s ease;">

                    <!-- Name & Specialty -->
                    <h5 class="fw-bold mb-1" style="color: #003366;">${doc.doctorName}</h5>
                    <p class="text-muted mb-1">${doc.specialty}</p>

                    <!-- Contact Info -->
                    <div class="mb-2">
                        <i class="bi bi-envelope text-primary me-2"></i>
                        <span class="text-dark">${doc.doctorEmail}</span>
                    </div>
                    <div class="mb-3">
                        <i class="bi bi-phone text-success me-2"></i>
                        <span class="text-dark">${doc.doctorPhoneNo}</span>
                    </div>

                    <!-- Other Details -->
                    <div class="text-start ps-3">
                        <p class="mb-1"><strong>License:</strong> <span class="text-muted">${doc.license_number}</span></p>
                        <p class="mb-1"><strong>Qualification:</strong> <span class="text-muted">${doc.qualification}</span></p>
                        <p class="mb-1"><strong>Experience:</strong> <span class="text-muted">${doc.experience} years</span></p>
                    </div>


                    <div class="text-center mb-1 d-flex justify-content-center gap-2">
                        <form action="getDoctorUpdatePage" method="post">
                            <input type="hidden" name="email" value="${doc.doctorEmail}">
                            <button class="btn btn-primary btn-sm">Update</button>
                        </form>
                        <form action="DoctorDelete" method="post">
                            <input type="hidden" name="email" value="${doc.doctorEmail}">
                            <button class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="resources/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
<footer>

</footer>
</html>
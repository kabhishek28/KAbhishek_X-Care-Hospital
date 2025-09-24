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
<!--<table class="table table-bordered table-striped">-->
<!--    <thead class="table-dark">-->
<!--    <tr>-->
<!--        <th>ID</th>-->
<!--        <th>Name</th>-->
<!--        <th>Email</th>-->
<!--        <th>Phone No</th>-->
<!--        <th>License</th>-->
<!--        <th>Specialty</th>-->
<!--        <th>Gender</th>-->
<!--        <th>Qualification</th>-->
<!--        <th>Experience (Years)</th>-->
<!--        <th>Slot Timing</th>-->
<!--        <th>Image</th>-->
<!--    </tr>-->
<!--    </thead>-->
<!--    <tbody>-->
<!--    <c:forEach var="doc" items="${doctorsList}">-->
<!--        <tr>-->
<!--            <td>${doc.id}</td>-->
<!--            <td>${doc.doctorName}</td>-->
<!--            <td>${doc.doctorEmail}</td>-->
<!--            <td>${doc.doctorPhoneNo}</td>-->
<!--            <td>${doc.license_number}</td>-->
<!--            <td>${doc.specialty}</td>-->
<!--            <td>${doc.doctorGender}</td>-->
<!--            <td>${doc.qualification}</td>-->
<!--            <td>${doc.experience}</td>-->
<!--            <td>${doc.slotTiming}</td>-->
<!--            <td>-->

<!--                <img src="download?imagePath=${doc.imagePath}" alt="Doctor Image" width="80" height="80">-->
<!--            </td>-->
<!--        </tr>-->
<!--    </c:forEach>-->
<!--    </tbody>-->
<!--</table>-->

<!--<div class="table-responsive">-->
<!--    <table class="table align-middle">-->
<!--        <thead>-->
<!--        <tr>-->
<!--            ...-->
<!--        </tr>-->
<!--        </thead>-->
<!--        <tbody>-->
<!--        <tr>-->
<!--            ...-->
<!--        </tr>-->
<!--        <tr class="align-bottom">-->
<!--            ...-->
<!--        </tr>-->
<!--        <tr>-->
<!--            <td>...</td>-->
<!--            <td>...</td>-->
<!--            <td class="align-top">This cell is aligned to the top.</td>-->
<!--            <td>...</td>-->
<!--        </tr>-->
<!--        </tbody>-->
<!--    </table>-->
<!--</div>-->


<!--<div class="container mt-4">-->
<!--    <div class="row">-->
<!--        <c:forEach var="doc" items="${doctorsList}">-->
<!--            <div class="col-md-4 mb-4">-->
<!--                <div class="card shadow-lg border-0 rounded-4">-->
<!--                    <div class="card-body text-center">-->
<!--                        &lt;!&ndash; Round Photo &ndash;&gt;-->
<!--                        <img src="download?imagePath=${doc.imagePath}" alt="Doctor Image"-->
<!--                             class="rounded-circle mb-3 shadow"-->
<!--                             width="120" height="120"/>-->


<!--                        <h5 class="fw-bold">${doc.doctorName}</h5>-->
<!--                        <p class="text-muted mb-1">${doc.specialty}</p>-->
<!--                        <p class="mb-1"><i class="bi bi-envelope"></i> ${doc.doctorEmail}</p>-->
<!--                        <p class="mb-1"><i class="bi bi-phone"></i> ${doc.doctorPhoneNo}</p>-->
<!--                        <p class="mb-1"><strong>License:</strong> ${doc.license_number}</p>-->
<!--                        <p class="mb-1"><strong>Qualification:</strong> ${doc.qualification}</p>-->
<!--                        <p class="mb-1"><strong>Experience:</strong> ${doc.experience} years</p>-->
<!--                        <p class="mb-2"><strong>Slot:</strong> ${doc.slotTiming}</p>-->

<!--                        &lt;!&ndash; Buttons &ndash;&gt;-->
<!--                        <div class="d-flex justify-content-center gap-2 mt-3">-->
<!--                            <form action="updateDoctor" method="get">-->
<!--                                <input type="hidden" name="doctorId" value="${doc.id}" />-->
<!--                                <button type="submit" class="btn btn-primary btn-sm">-->
<!--                                    <i class="bi bi-pencil-square"></i> Update-->
<!--                                </button>-->
<!--                            </form>-->

<!--                            <form action="deleteDoctor" method="post"-->
<!--                                  onsubmit="return confirm('Are you sure you want to delete this doctor?');">-->
<!--                                <input type="hidden" name="doctorId" value="${doc.id}" />-->
<!--                                <button type="submit" class="btn btn-danger btn-sm">-->
<!--                                    <i class="bi bi-trash"></i> Delete-->
<!--                                </button>-->
<!--                            </form>-->
<!--                        </div>-->
<!--                    </div>-->
<!--                </div>-->
<!--            </div>-->
<!--        </c:forEach>-->
<!--    </div>-->
<!--</div>-->


<!--<div class="container mt-4 border">-->
<!--<div class="row">-->
<!--    <c:forEach var="doc" items="${doctorsList}">-->
<!--        <div class="col-md-4 mb-4">-->
<!--            <div class="card shadow-lg border-0 rounded-4">-->
<!--                <div class="card-body text-center ">-->
<!--                    <img src="download?imagePath=${doc.imagePath}" alt="Doctor Image" class="rounded-circle mb-3 shadow" width="120" height="120" >-->

<!--                    <h1>Page Title</h1>-->
<!--                    <p>This content is within a responsive, fixed-width container.</p>-->
<!--                </div>-->
<!--            </div>-->
<!--        </div>-->
<!--    </c:forEach>-->
<!--</div>-->
<!--</div>-->

<div class="container mt-4 ">
    <div class="row">
        <c:forEach var="doc" items="${doctorsList}">
            <div class="col-md-4 mb-4">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body text-center ">
                        <img src="download?imagePath=${doc.imagePath}" alt="doctor image" class="rounded-circle mb-3 shadow" width="120" height="120">
                        <h5 class="fw-bold">${doc.doctorName}</h5>
                        <p class="text-muted mb-1">${doc.specialty}</p>
                        <p class="mb-1"><i class="bi bi-envelope"></i> ${doc.doctorEmail}</p>
                        <p class="mb-1"><i class="bi bi-phone"></i> ${doc.doctorPhoneNo}</p>
                        <p class="mb-1"><strong>License:</strong> ${doc.license_number}</p>
                        <p class="mb-1"><strong>Qualification:</strong> ${doc.qualification}</p>
                        <p class="mb-1"><strong>Experience:</strong> ${doc.experience} years</p>
                    </div>
                    <div class="text-center mb-4">
                        <a href="" class="btn btn-primary btn-sm bt-3">UpDate</a>
                        <a href="" class="btn btn-danger btn-sm">Delete</a>
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
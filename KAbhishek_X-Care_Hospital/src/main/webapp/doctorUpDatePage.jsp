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
<div class="d-flex justify-content-center mt-5">
    <div class="card shadow-lg p-4 rounded-4" style="width: 40rem;">
        <h3 class="text-center fw-bold mb-4 " style="color: #003366;">
            Update <span class="text-muted">Dr ${dto.doctorName}</span> Details
        </h3>
        <hr class="mx-auto mb-4" style="width: 60px; border: 2px solid #0d6efd; border-radius: 5px;">
        <div class="text-center mb-4">
            <div class="text-center mb-4">
                <img src="download?imagePath=${dto.imagePath}" alt="doctor image" class="rounded-circle shadow-lg p-1 bg-light" style="border: 4px solid #003366;" width="160" height="160">
            </div>
        </div>
        <div>
            <p class="text-danger fw-bold text-center">${updatedDataNotSaved}</p>
            <p class="text-success  fw-bold text-center">${updatedDataSaved}</p>
        </div>
        <form class="row g-3" action="updateDoctorForm" method="post" enctype="multipart/form-data">
            <!--            <img src="download?imagePath=${dto.imagePath}"></img>-->


                <input type="hidden" class="form-control" id="inputId" name="id" value="${dto.id}" oninput="validationId()" placeholder="Enter Doctor ID" required>
                <div id="idError" class="input-text text-danger" style="min-height:25px;"></div>

            <div class="col-md-6">
                <label for="inputName" class="form-label">Doctor Name</label>
                <input type="text" class="form-control" id="inputName" name="doctorName" oninput="validationName()" placeholder="Dr. John Doe" required>
                <div  id="nameError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>

            <div class="col-md-6">
                <label for="gmailID" class="form-label">Email</label>
                <input type="email" class="form-control" id="gmailID" name="doctorEmail" value="${dto.doctorEmail}" oninput="validationGmail()" onchange="checkEmail()" placeholder="doctor@example.com" readonly>
                <div id="emailError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>

            <div class="col-md-6">
                <label for="inputPhone" class="form-label">Phone Number</label>
                <input type="number" class="form-control" id="inputPhone" name="doctorPhoneNo" oninput="validationPhoneNo()" placeholder="+91 9876543210" required>
                <div id="phoneNoError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>


            <div class="col-md-6">
                <label for="licenseNumber" class="form-label">License / Reg. No.</label>
                <input type="text" class="form-control" id="licenseNumber" name="license_number" oninput="validateLicenseNo()" placeholder="e.g., MC-123456" required>
                <div id="licenseError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>


            <div class="mb-4">
                <label class="form-label fw-semibold" for="specialtyID">Specialty</label>
                <input type="text" class="form-control " id="specialtyID" name="specialty" value="${dto.specialty}"  readonly>
            </div>


            <div class="col-md-6">
                <label for="gender" class="form-label">Gender</label>
                <select id="gender" class="form-select" name="doctorGender" required>
                    <option selected disabled>Choose...</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                    <option value="prefer_not_say">Prefer not to say</option>
                </select>
            </div>

            <div class="col-md-6">
                <label for="qualification" class="form-label">Qualification</label>
                <input type="text" class="form-control" id="qualification" name="qualification" placeholder="e.g., MBBS, MD" oninput="validateQualification()" required>
                <div id="qualificationError" class="input-text text-danger" style="min-height:25px;"></div>
            </div>

            <div class="col-md-6">
                <label for="experience" class="form-label">Experience (Years)</label>
                <input type="number" class="form-control" id="experience" name="experience" placeholder="e.g., 10" min="0" max="60" required>

            </div>

            <div class="col-12">
                <label for="photo" class="form-label">Upload Profile Photo(png only)</label>
                <input type="file" class="form-control" id="photo" name="photo" accept="image/png" required>
            </div>


            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary w-100">Update Details</button>
            </div>
        </form>
    </div>
</div>
<script src="resources/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
<footer>

</footer>
</html>.

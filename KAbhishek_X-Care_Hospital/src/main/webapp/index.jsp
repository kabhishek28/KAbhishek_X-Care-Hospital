<!doctype html>
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
                <button type="button" class="btn btn-success btn-blink shadow-sm  me-2">Access Lab Reports</button>
                <button type="button" class="btn btn-primary btn-blink shadow-sm  me-2">Appointment</button>
                <button type="button" class="btn  btn-danger btn-blink me-2 shadow-sm ">Emergency Ambulance</button>
                <button type="button" class="btn btn-outline-dark me-4 shadow" >Admin Login</button>
            </div>
        </div>
    </nav>

    <nav class="navbar" style="background-color: #003366;">
        <ul class="nav nav-underline">
            <li class="nav-item">
                <a class="nav-link toggle" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link toggle" aria-current="page" href="#">About Us</a>
            </li>
            <li class="nav-item">
                <a class="nav-link toggle" aria-current="page" href="#">Doctors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link toggle" aria-current="page" href="#">Health Package</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Hospitals</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Bangalore</a></li>
                    <li><a class="dropdown-item" href="#">Mangalore</a></li>
                    <li><a class="dropdown-item" href="#">Udupi</a></li>
                    <li><a class="dropdown-item" href="#">Pune</a></li>
                    <li><a class="dropdown-item" href="#">Delhi</a></li>
                    <li><a class="dropdown-item" href="#">Goa</a></li>
                </ul>
            </li>
            <li class="nav-item dropdown">

                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Specialities</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Anaesthesiology</a></li>
                    <li><a class="dropdown-item" href="#">Bone Marrow</a></li>
                    <li><a class="dropdown-item" href="#">Cardiac Sciences</a></li>
                    <li><a class="dropdown-item" href="#">Critical Care Medicine</a></li>
                    <li><a class="dropdown-item" href="#">Maxillofacial and Dental Surgery</a></li>
                    <li><a class="dropdown-item" href="#">Dentistry</a></li>
                    <li><a class="dropdown-item" href="#">Dermatology</a></li>
                    <li><a class="dropdown-item" href="#">Emergency Medicine</a></li>
                    <li><a class="dropdown-item" href="#">Endocrinology and Diabetology</a></li>



                </ul>
            </li>
        </ul>
    <span class="navbar-brand mb-0 h1" style="color: #ffffff;">



</main>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
<footer>

</footer>
</html>
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
                <a type="button" class="btn btn-outline-dark me-4 shadow" href="admin" >Admin Login</a>
            </div>
        </div>
    </nav>




</main>
<script>
    function startTimer(seconds) {
            let timer = seconds;
            const display = document.getElementById("timer");

            const interval = setInterval(() => {
                let minutes = Math.floor(timer / 60);
                let secs = timer % 60;
                display.innerHTML = minutes + ":" + (secs < 10 ? "0" : "") + secs;

                if (--timer < 0) {
                    clearInterval(interval);
                    display.innerHTML = "OTP expired!";
                }
            }, 1000);
        }
</script>
<body onload="startTimer(${remainingTime})" >
<div class="d-flex justify-content-center mt-5">
    <div class="card shadow-lg p-4 rounded-4  " style="width: 22rem;">
        <h5 class="text-center mb-3">Admin Login</h5>
        <form action="login" method="post">
            <div class="mb-3">
                <label for="gmailID" class="form-label">Email address</label>
                <input type="email" class="form-control" id="gmailID" name="gmailName" placeholder="name@example.com" oninput="validationGmail()" onchange="checkEmail()" value="${gmail}" required>
<!--                <div  id="emailError" class="input-text text-danger" style="min-height:25px;"></div>-->

            </div>
            <p id="timer"></p>
            <div class="mb-3">

                <label for="OtplID" class="form-label">Enter Otp</label>
                <input type="text" class="form-control" id="OtplID" name="otp" placeholder="Enter the OTP"   required>
                <div  id="otpError" class="input-text text-danger" style="min-height:25px;"></div>
                <c:out value="${otpError}" />
                <c:out value="${time}"/>
            </div>

            <button type="submit" class="btn btn-primary w-100">Log in</button>
        </form>
    </div>
</div>


<script src="resources/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
<footer>

</footer>
</html>
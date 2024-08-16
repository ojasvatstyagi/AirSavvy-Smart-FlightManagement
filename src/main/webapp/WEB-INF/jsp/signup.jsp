<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>SIGN UP</title>
  <link rel="shortcut icon" type="image/png" href="../assets/images/logos/favicon.png" />
  <link rel="stylesheet" href="../assets/css/styles.min.css" />

  <style>
  .error {
          color: red;
          font-size: 14px;
          margin-bottom: 10px;
      }
  </style>
</head>

<body>
  <!--  Body Wrapper -->
  <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
    data-sidebar-position="fixed" data-header-position="fixed">
    <div
      class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center">
      <div class="d-flex align-items-center justify-content-center w-100">
        <div class="row justify-content-center w-100">
          <div class="col-md-8 col-lg-6 col-xxl-3">
            <div class="card mb-0">
              <div class="card-body">
                <a href="./index.html" class="text-nowrap logo-img text-center d-block py-3 w-100">
                  <img src="../assets/images/logos/dark-logo.svg" width="180" alt="">
                </a>
                <h3 class="text-center">Flight Management System</h3>
                <p class="text-center">Registration form</p>
                <div id="error" class="error">${error}</div>

                <form id="registrationForm" action="/register" method="post" onsubmit="return validateForm()">
                  <div class="mb-3">
                    <label for="username" class="form-label">User Name</label>
                    <input type="text" class="form-control" id="username" name="username">
                  </div>
                  <div class="mb-3">
                    <label for="type" class="form-label">User Type</label>
                    <div class="form-check form-check-inline mb-0 me-4">
                      <input class="form-check-input" type="radio" id="customer" value="customer" name="role" />
                      <label class="form-check-label" for="customer">Customer</label>
                    </div>
                    <div class="form-check form-check-inline mb-0 me-4">
                      <input class="form-check-input" type="radio" id="admin" value="admin" name="role" />
                      <label class="form-check-label" for="admin">Admin</label>
                    </div>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email">
                  </div>
                  <div class="mb-4">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                  </div>
                  <div class="mb-4">
                    <label for="confirmPassword" class="form-label">Rewrite Your Password</label>
                    <input type="password" class="form-control" id="confirmPassword">
                  </div>
                  <button type="submit" class="btn btn-primary w-100 py-8 fs-4 mb-4 rounded-2">Sign Up</button>
                  <div class="d-flex align-items-center justify-content-center">
                    <p class="fs-4 mb-0 fw-bold">Already have an Account?</p>
                    <a class="text-primary fw-bold ms-2" href="/loginpage">Sign In</a>
                  </div>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
    function validateForm() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        // Check if passwords match
        if (password !== confirmPassword) {
            alert("Passwords do not match. Please re-enter your password.");
            return false;
        }

        // Check password length
        if (password.length < 8) {
            alert("Password must be at least 8 characters long.");
            return false;
        }

        // Check for at least one number, lowercase letter, and special character
        const regex = /(?=.*\d)(?=.*[a-z])(?=.*[\W_])/;
        if (!regex.test(password)) {
            alert("Password must contain at least one number, lowercase letter, and one special character.");
            return false;
        }

        // If all checks pass, allow the form to be submitted
        return true;
    }
</script>

  <script src="../assets/libs/jquery/dist/jquery.min.js"></script>
  <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
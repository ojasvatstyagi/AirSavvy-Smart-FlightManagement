<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>SIGN IN</title>
  <link rel="shortcut icon" type="image/png" href="../assets/images/logos/favicon.png" />
  <link rel="stylesheet" href="../assets/css/styles.min.css" />
  <!-- Bootstrap Icons CDN for professional eye icons -->
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
  />
  <style>
    .error-message {
      color: red;
      font-size: 16px;
      margin-bottom: 10px;
    }
    /* Positioning for the eye toggle inside password input */
    .password-wrapper {
      position: relative;
    }
    .password-wrapper input[type="password"],
    .password-wrapper input[type="text"] {
      padding-right: 2.5rem; /* room for eye icon */
    }
    .toggle-password {
      position: absolute;
      top: 75%;
      right: 0.75rem;
      transform: translateY(-50%);
      background: transparent;
      border: none;
      cursor: pointer;
      color: #6c757d; /* grayish */
      font-size: 1.25rem;
      padding: 0;
      line-height: 1;
    }
    .toggle-password:focus {
      outline: none;
      color: #0d6efd; /* bootstrap primary color on focus */
    }
  </style>
</head>

<body>
  <!--  Body Wrapper -->
  <div
    class="page-wrapper"
    id="main-wrapper"
    data-layout="vertical"
    data-navbarbg="skin6"
    data-sidebartype="full"
    data-sidebar-position="fixed"
    data-header-position="fixed"
  >
    <div
      class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center"
    >
      <div class="d-flex align-items-center justify-content-center w-100">
        <div class="row justify-content-center w-100">
          <div class="col-md-8 col-lg-6 col-xxl-3">
            <div class="card mb-0">
              <div class="card-body">
                <h3 class="text-center">Flight Management System</h3>
                <p class="text-center">Sign In to your account</p>
                <div class="error-message">${error}</div>
                <form action="/login" method="post">
                  <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" />
                  </div>
                  <div class="mb-4 password-wrapper">
                    <label for="password" class="form-label">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="password"
                      name="password"
                      aria-describedby="togglePasswordLabel"
                    />
                    <button
                      type="button"
                      class="toggle-password"
                      id="togglePassword"
                      aria-label="Toggle password visibility"
                    >
                      <i class="bi bi-eye-slash"></i>
                    </button>
                  </div>
                  <div class="d-flex align-items-center justify-content-between mb-4">
                    <div class="form-check">
                      <input
                        class="form-check-input primary"
                        type="checkbox"
                        value=""
                        id="flexCheckChecked"
                        checked
                      />
                      <label class="form-check-label text-dark" for="flexCheckChecked">
                        Remember this Device
                      </label>
                    </div>
                    <!-- Removed Forgot Password link -->
                  </div>
                  <button
                    type="submit"
                    class="btn btn-primary w-100 py-8 fs-4 mb-4 rounded-2"
                  >
                    Sign In
                  </button>
                  <div class="d-flex align-items-center justify-content-center">
                    <p class="fs-4 mb-0 fw-bold">Don't have Account?</p>
                    <a class="text-primary fw-bold ms-2" href="/register">Create an account</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="../assets/libs/jquery/dist/jquery.min.js"></script>
  <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Password toggle script -->
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const togglePassword = document.querySelector("#togglePassword");
      const passwordField = document.querySelector("#password");
      const icon = togglePassword.querySelector("i");

      togglePassword.addEventListener("click", function () {
        const type =
          passwordField.getAttribute("type") === "password" ? "text" : "password";
        passwordField.setAttribute("type", type);
        // Toggle the icon class for eye / eye-slash
        icon.classList.toggle("bi-eye");
        icon.classList.toggle("bi-eye-slash");
      });
    });
  </script>
</body>

</html>

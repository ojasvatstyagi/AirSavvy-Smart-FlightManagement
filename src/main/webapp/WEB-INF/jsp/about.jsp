<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Fligth Management System</title>
  <link rel="shortcut icon" type="image/png" href="../assets/images/logos/favicon.png" />
  <link rel="stylesheet" href="../assets/css/styles.min.css" />
</head>

<body>
      <div class="unlimited-access hide-menu bg-light-primary position-relative mb-7 mt-5 rounded">
        <div class="d-flex">
          <div class="unlimited-access-title me-3">
          <div class="unlimited-access-img">
            <img src="../assets/images/backgrounds/rocket.png" alt="" class="img-fluid">
          </div>
        </div>
      </div>
    </aside>
    <!--  Sidebar End -->
    <!--  Main wrapper -->
    <div class="body-wrapper">
      <!--  Header Start -->
      <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
          <ul class="navbar-nav">
            <li class="nav-item d-block d-xl-none">
              <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="javascript:void(0)">
                <i class="ti ti-menu-2"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-icon-hover" href="javascript:void(0)">
                <i class="ti ti-bell-ringing"></i>
                <div class="notification bg-primary rounded-circle"></div>
              </a>
            </li>
          </ul>
          <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
              <li class="nav-item dropdown">
                <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  <img src="../assets/images/profile/user-1.jpg" alt="" width="35" height="35" class="rounded-circle">
                </a>
                <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                  <div class="message-body">

                    <a href="/index" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-mood-happy"></i>
                      <p class="mb-0 fs-3">Home</p>
                    </a>
                    <a href="./authentication-login.html" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!--  Header End -->


      <div class="container-fluid">
        <div class="container-fluid">
            <!-- Container (About Section) -->
            <div class="mb-3">
                <div class="row">
                <div>
                    <h2>About Company Page</h2><br>
                    <p>Our company has been at the forefront of the industry for over a decade, delivering top-notch services and products to our clients worldwide. We pride ourselves on our commitment to excellence, innovation, and customer satisfaction. Our dedicated team of professionals works tirelessly to ensure that we meet and exceed the expectations of our clients.</p>
                    <p>We believe in fostering a culture of continuous improvement and innovation. By staying ahead of industry trends and embracing new technologies, we are able to provide our clients with cutting-edge solutions that drive their success. Our core values of integrity, respect, and excellence guide everything we do, ensuring that we build strong, lasting relationships with our clients and partners.</p>
                </div>
                </div>
            </div>

            <div class="mb-3">
                <div class="row">
                <div>
                    <h2>Our Values</h2><br>
                    <p><strong>MISSION:</strong> Our mission is to deliver exceptional value to our customers through innovative solutions and unparalleled service. We strive to achieve excellence in every aspect of our business, from product development to customer support, ensuring that we meet and exceed our clients' expectations.</p><br>
                    <p><strong>VISION:</strong> Our vision is to be a global leader in our industry, recognized for our commitment to quality, innovation, and customer satisfaction. We aim to create a sustainable future by continuously improving our processes, embracing new technologies, and fostering a culture of excellence and collaboration. By doing so, we aspire to make a positive impact on our clients, our employees, and the communities we serve.</p>
                </div>
                </div>
            </div>
            <!-- Container (Portfolio Section) -->
            <div class="container-fluid text-center">
                <h2>Portfolio</h2>
                <div class="row text-center">
                    <div >
                    <img src="../assets/images/profile/profile.png" alt="Profile" width="400" height="400">
                    <p><strong>Ojas Tyagi</strong></p>
                    <p>Aspiring Java Developer</p>
                    <i class="ti ti-brand-github" st></i><a href="https://github.com/ojasvatstyagi"> Github Account</a><br>
                    <i class="ti ti-brand-linkedin"></i><a href="https://www.linkedin.com/in/ojas-tyagi"> My LinkedIn</a><br>
                    <i class="ti ti-user"></i><a href="https://ojasvatstyagi.github.io/Portfolio/">Visit To Know More About Me</a>
                    </div>
                </div>
            </div>
            <br>
            <br>
                <!-- Container (Contact Section) -->
            <div class="container-fluid text-center">
                <h2>CONTACT</h2>
                <div class="text-center">
                    <p>Contact us and we'll get back to you within 24 hours.</p>
                    <i class="ti ti-current-location"></i> Bengaluru, INDIA</p>
                    <i class="ti ti-phone"></i> +00 1010101010</p>
                    <i class="ti ti-mail"></i> myemail@something.com</p>
                </div>
                <form action="/about" method="post">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                    </div>
                    <div class="mb-3">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                    </div>
                    <div class="mb-3">
                        <textarea rows="5" class="form-control" id="comment" name="comment" placeholder="Comment"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <br>
                    <div style="text-align: center;">
                        <a class="text-primary fw-bold ms-2" href="/index">Back Home</a>
                    </div>
                </div>
            </div>
        </div>
      </div>
    </div>
</div>

  <script src="../assets/libs/jquery/dist/jquery.min.js"></script>
  <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../assets/js/sidebarmenu.js"></script>
  <script src="../assets/js/app.min.js"></script>
  <script src="../assets/libs/simplebar/dist/simplebar.js"></script>
</body>
</html>
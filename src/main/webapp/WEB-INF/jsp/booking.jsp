<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  <!--  Body Wrapper -->
  <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
    data-sidebar-position="fixed" data-header-position="fixed">
    <!-- Sidebar Start -->
    <aside class="left-sidebar">
      <!-- Sidebar scroll-->
      <div>
        <div class="brand-logo d-flex align-items-center justify-content-between">
          <a href="./index.html" class="text-nowrap logo-img">
            <img src="../assets/images/logos/bird_2.jpg" width="180" alt="" />
          </a>
          <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
            <i class="ti ti-x fs-8"></i>
          </div>
        </div>

        <!-- Sidebar navigation-->
        <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
          <ul id="sidebarnav">


            <li class="nav-small-cap">
                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                <span class="hide-menu">Home</span>
              </li>
              <li class="sidebar-item">
                <a class="sidebar-link" href="/index" aria-expanded="false">
                  <span>
                    <i class="ti ti-layout-dashboard"></i>
                  </span>
                  <span class="hide-menu">Dashboard</span>
                </a>
              </li>
              <li class="nav-small-cap">
                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                <span class="hide-menu">SERVICES</span>
              </li>

              <li class="sidebar-item">
                <a class="sidebar-link" href="/viewAirports">
                  <span>
                    <i class="ti ti-location"></i>
                  </span>
                  <span class="hide-menu">Enquire Airport</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a class="sidebar-link" href="/viewFlights">
                  <span>
                    <i class="ti ti-plane"></i>
                  </span>
                  <span class="hide-menu">Enquire Flight</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a class="sidebar-link" href="/viewRoutes" >
                  <span>
                    <i class="ti ti-route"></i>
                  </span>
                  <span class="hide-menu">Enquire Route</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a class="sidebar-link" href="/searchFlight" aria-expanded="false">
                  <span>
                    <i class="ti ti-brand-booking"></i>
                  </span>
                  <span class="hide-menu">Book Flight</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a class="sidebar-link" href="/viewBooking" arias-expanded="false">
                  <span>
                    <i class="ti ti-ticket"></i>
                  </span>
                  <span class="hide-menu">Your Bookings</span>
                </a>
              </li>
            </ul>


          <div class="unlimited-access hide-menu bg-light-primary position-relative mb-7 mt-5 rounded">
            <div class="d-flex">
              <div class="unlimited-access-title me-3">
              <div class="unlimited-access-img">
                <img src="../assets/images/backgrounds/rocket.png" alt="" class="img-fluid">
              </div>
            </div>
          </div>
        </nav>
        <!-- End Sidebar navigation -->
      </div>
      <!-- End Sidebar scroll-->
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
                    <a href="/profile" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-user fs-6"></i>
                      <p class="mb-0 fs-3">My Profile</p>
                    </a>
                    <a href="/about" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-mood-happy"></i>
                      <p class="mb-0 fs-3">About Us</p>
                    </a>
                    <a href="/loginpage" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
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
          <div class="card">
            <div class="card-body">
              <h5 class="card-title fw-semibold mb-4">Booking Panel</h5>
              <div class="card mb-0">
                <div class="card-body p-4">
                  <div class="container">
                    <h2>Book Flight</h2>
                      <div class="mb-3">
                        <label for="flightNumber" class="form-label">Flight Id</label>
                        <input type="text" class="form-control" id="flightNumber" name="flightNumber" value="${flightNumber}" placeholder="xxx" disabled>
                      </div>
                      <div class="mb-3">
                        <label for="flightName" class="form-label">Flight Name</label>
                        <input type="text" class="form-control" id="flightName" name="flightName" value="${flightName}" disabled>
                      </div>
                      <div class="mb-3">
                        <label for="routeId" class="form-label">Route ID</label>
                        <input type="text" class="form-control" id="routeId" name="routeId" value="${routeId}" disabled>
                      </div>
                      <div class="mb-3">
                          <label for="price" class="form-label">Price</label>
                          <input type="text" class="form-control" id="price" name="price" value="${price}" disabled>
                      </div>

                      <form action="/bookFlight" method="post">
                        <div id="passenger-container">
                        <h3>Passenger</h3>
                        <div class="mb-3">
                          <label for="passengerName1" class="form-label">Passenger Name</label>
                          <input type="text" class="form-control" id="passengerName1" name="passengerName">
                        </div>
                        <div class="mb-3">
                          <label for="passengerDob1" class="form-label">Passenger Date of Birth</label>
                          <input type="date" class="form-control" id="passengerDob1" name="passengerDob">
                        </div>
                      </div>
                      <div style="display: flex; justify-content: space-evenly;">
                          <button type="button" onclick="addPassenger()" class="btn btn-danger">Add Passenger</button>
                          <button type="submit" class="btn btn-primary">Book Flight</button>
                          <button type="button" onclick="removePassenger()" class="btn btn-danger">Remove Passenger</button>
                        </div>
                          <input type="hidden" name="routeId" value="${routeId}">
                          <input type="hidden" name="flightNumber" value="${flightNumber}">
                          <input type="hidden" name="flightName" value="${flightName}">
                          <input type="hidden" name="price" value="${price}">
                      </form>
                      <br>
                        <div style="text-align: center;">
                            <a class="text-primary fw-bold ms-2" href="/searchFlight">Back</a>
                        </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <script>
    let passengerCount = 1;

    function addPassenger() {
        passengerCount++;
        const container = document.getElementById('passenger-container');
        const passengerDiv = document.createElement('div');
        passengerDiv.className = 'mb-3';
        passengerDiv.id = 'passenger' + passengerCount;

        passengerDiv.innerHTML = `
            <h3>Passenger ${passengerCount}</h3>
            <div class="mb-3">
                <label for="passengerName${passengerCount}" class="form-label">Passenger Name</label>
                <input type="text" class="form-control" id="passengerName${passengerCount}" name="passengerName" >
            </div>
            <div class="mb-3">
                <label for="passengerDob${passengerCount}" class="form-label">Passenger Date of Birth</label>
                <input type="date" class="form-control" id="passengerDob${passengerCount}" name="passengerDob">
            </div>
        `;

        container.appendChild(passengerDiv);
    }

    function removePassenger() {
        if (passengerCount > 1) {
            const container = document.getElementById('passenger-container');
            const lastPassenger = document.getElementById('passenger' + passengerCount);
            container.removeChild(lastPassenger);
            passengerCount--;
        }
    }
</script>

  <script src="../assets/libs/jquery/dist/jquery.min.js"></script>
  <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../assets/js/sidebarmenu.js"></script>
  <script src="../assets/js/app.min.js"></script>
  <script src="../assets/libs/simplebar/dist/simplebar.js"></script>

</body>
</html>
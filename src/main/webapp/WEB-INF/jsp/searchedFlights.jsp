<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>SEARCHED FLIGHTS</title>
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
              <a class="sidebar-link" href="/addAirport">
                <span>
                  <i class="ti ti-square-plus"></i>
                </span>
                <span class="hide-menu">Add Airports</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="/modifyairport">
                <span>
                  <i class="ti ti-current-location"></i>
                </span>
                <span class="hide-menu">Modify Airports</span>
              </a>
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
              <a class="sidebar-link" href="/addFlight">
                <span>
                  <i class="ti ti-square-plus"></i>
                </span>
                <span class="hide-menu">Add Flights</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="/modifyFlight">
                <span>
                  <i class="ti ti-plane-tilt"></i>
                </span>
                <span class="hide-menu">Modify Flights</span>
              </a>
            </li><li class="sidebar-item">
              <a class="sidebar-link" href="/viewFlights">
                <span>
                  <i class="ti ti-plane"></i>
                </span>
                <span class="hide-menu">Enquire Flight</span>
              </a>
            </li>


            <li class="sidebar-item">
              <a class="sidebar-link" href="/route">
                <span>
                  <i class="ti ti-square-plus"></i>
                </span>
                <span class="hide-menu">Add Route</span>
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
                    <a href="/viewBooking" class="d-flex align-items-center gap-2 dropdown-item">
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
              <h5 class="card-title fw-semibold mb-4">Airport Panel</h5>
              <div class="card mb-0">
                <div class="card-body p-4">
                  <div class="container">
                    <h2>Search Flight</h2>
                    <h5 class="card-title fw-semibold mb-4">Available Flights</h5>
                        <c:forEach var="flight" items="${flights}">
                        <div class="card">
                            <div class="card-header">
                                <p><strong>Flight ID:</strong> ${flight.flightNumber}</p>
                                <p><strong>Flight Name:</strong> ${flight.flightName}</p>
                                <p><strong>Arrival Time:</strong> ${flight.arrival}</p>
                                <p><strong>Departure Time:</strong> ${flight.departure}</p>
                                <p><strong>Route ID:</strong> ${flight.routeId}</p>
                                <p><strong>Available Seats:</strong> ${flight.seatCapacity - flight.seatsBooked}</p>
                                <p><strong>Price:</strong> ${price}</p>
                            </div>
                            <div class="card-body">
                                <form action="/bookFlight" method="get">
                                    <input type="hidden" name="flightNumber" value="${flight.flightNumber}">
                                    <input type="hidden" name="flightName" value="${flight.flightName}">
                                    <input type="hidden" name="routeId" value="${flight.routeId}">
                                    <input type="hidden" name="price" value="${price}">
                                    <button type="submit" class="btn btn-primary">Book</button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                        <div style="text-align: center;">
                            <a class="text-primary fw-bold ms-2" href="/searchFlight">Back to search</a>
                        </div>
                </div>
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
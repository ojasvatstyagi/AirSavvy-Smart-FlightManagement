<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>MY PROFILE</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/fontawesome.min.css" integrity="sha384-BY+fdrpOd3gfeRvTSMT+VUZmA728cfF9Z2G42xpaRkUGu2i3DyzpTURDo5A6CaLK" crossorigin="anonymous">
   <link rel="stylesheet" href="../assets/css/styles.min.css" />
  <style>
    .gradient-custom {
      background: #f6d365;
      background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));
      background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));
    }
    a {
      color: #007bff;
      text-decoration: none;
      display: block;
      text-align: center;
      margin-top: 20px;
    }
    a:hover {
      text-decoration: underline;
    }
    .profile-edit {
      cursor: pointer;
    }
    .form-control:disabled {
      background-color: #e9ecef;
      cursor: not-allowed;
    }
  </style>
</head>

<body>

    <section class="vh-100">
        <div class="container py-5 h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-6 mb-4 mb-lg-4">
              <div class="card mb-3" style="border-radius: .5rem;">
                <div class="row g-0">
                  <div class="col-md-8 gradient-custom text-center text-white"
                    style="border-top-left-radius: .5rem; border-top-right-radius: .5rem; width: 100%;">
                    <form method="post" action="/uploadProfileImage" enctype="multipart/form-data">
                      <!-- Display profile image or a placeholder if no image is available -->
                      <img src="${profile.photo != null ? '/images/' + profile.photo : '/images/default-profile.png'}" alt="Avatar" class="img-fluid my-5" style="width: 80px;" />
                      <input type="file" name="profileImage" class="form-control mt-3" />
                      <button type="submit" class="btn btn-primary mt-2">Upload Image</button>
                    </form>
                    <h5>${profile.firstName + " " + profile.lastName}</h5>
                    <p>${flightuser.username}</p>
                  </div>
                  <div class="col-md-14">
                    <div class="card-body p-4">
                      <h6>Information</h6>
                      <hr class="mt-0 mb-4">
                      <form method="post" action="/updateProfile">
                        <div class="row pt-1">
                          <div class="col-6 mb-3">
                            <h6>Email</h6>
                            <p class="text-muted">
                              <input type="text" name="email" value="${flightuser.email}" class="form-control" placeholder="Email" disabled />
                            </p>
                          </div>
                          <div class="col-6 mb-3">
                            <h6>Phone</h6>
                            <p class="text-muted">
                              <input type="text" name="phone" value="${profile.phone}" class="form-control" placeholder="Phone" disabled />
                            </p>
                          </div>
                        </div>
                        <hr class="mt-0 mb-4">
                        <div class="row pt-1">
                          <div class="col-6 mb-3">
                            <h6>Address</h6>
                            <p class="text-muted">
                              <input type="text" name="address" value="${profile.address}" class="form-control" placeholder="Address" disabled />
                            </p>
                          </div>
                          <div class="col-6 mb-3">
                            <h6>Aadhare Number</h6>
                            <p class="text-muted">
                              <input type="text" name="aadhareNumber" value="${profile.aadhareNumber}" class="form-control" placeholder="Aadhare Number" disabled />
                            </p>
                          </div>
                        </div>
                        <hr class="mt-0 mb-4">
                        <div class="text-center">
                          <button type="button" class="btn btn-info profile-edit" onclick="enableEditing()">Edit</button>
                          <button type="submit" class="btn btn-success ms-2 d-none" id="updateBtn">Update Profile</button>
                          <a class="text-primary fw-bold ms-2" href="/index">Back Home</a>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
      <script>
        function enableEditing() {
          const inputs = document.querySelectorAll('input.form-control');
          inputs.forEach(input => input.disabled = false);
          document.querySelector('.profile-edit').classList.add('d-none');
          document.getElementById('updateBtn').classList.remove('d-none');
        }
      </script>
</body>

</html>
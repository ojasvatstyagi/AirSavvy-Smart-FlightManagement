<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/profile-css.css">
</head>

<body>
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-lg-6 mb-4 mb-lg-0">
                    <div class="card mb-3" style="border-radius: .5rem;">
                        <div class="row g-0">
                            <div class="col-md-12 gradient-custom text-center text-white">
                                <form method="post" action="/uploadProfileImage" enctype="multipart/form-data">
                                    <div class="profile-header">
                                        <div class="profile-image-container">
                                            <c:choose>
                                                <c:when test="${not empty flightuser.username}">
                                                    <img src="/profileImage/${flightuser.username}" alt="Profile Image" onerror="this.onerror=null; this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png';" />
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="dummy-avatar">
                                                        <i class="fas fa-user"></i>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="file" name="profileImage" onchange="previewFileName(this)" accept="image/*" />
                                            <div class="upload-icon"><i class="fas fa-camera"></i></div>
                                        </div>
                                        <button type="submit" class="btn upload-button">Upload Image</button>
                                    </div>
                                </form>
                                <h5 class="mt-3">${profile.firstName} ${profile.lastName}</h5>
                                <p class="username">${flightuser.username}</p>
                            </div>
                            <div class="col-md-12">
                                <div class="card-body profile-info">
                                    <h6 class="mb-3">Information</h6>
                                    <hr class="section-divider">
                                    <form method="post" action="/updateProfile">
                                        <div class="row pt-1">
                                            <div class="col-md-6 mb-3">
                                                <h6>Email</h6>
                                                <input type="email" name="email" value="${flightuser.email}"
                                                    class="form-control" placeholder="Email" disabled />
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <h6>Phone</h6>
                                                <input type="tel" name="phone" value="${profile.phone}"
                                                    class="form-control" placeholder="Phone" disabled />
                                            </div>
                                        </div>
                                        <hr class="section-divider">
                                        <div class="row pt-1">
                                            <div class="col-md-6 mb-3">
                                                <h6>Address</h6>
                                                <input type="text" name="address" value="${profile.address}"
                                                    class="form-control" placeholder="Address" disabled />
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <h6>Aadhar Number</h6>
                                                <input type="text" name="aadharNumber"
                                                    value="${profile.aadharNumber}" class="form-control"
                                                    placeholder="Aadhar Number" disabled />
                                            </div>
                                        </div>
                                        <hr class="section-divider">
                                        <div class="profile-actions">
                                            <div class="action-buttons">
                                                <button type="button" class="btn edit-button profile-edit"
                                                    onclick="enableEditing()">Edit</button>
                                                <button type="submit" class="btn btn-success ms-2 d-none"
                                                    id="updateBtn">Update Profile</button>
                                            </div>
                                            <a href="/index" class="back-home">
                                                <i class="fas fa-home me-1"></i> Back Home
                                            </a>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function enableEditing() {
            const inputs = document.querySelectorAll('input.form-control');
            inputs.forEach(input => {
                if (input.name !== 'email') { // Keep email disabled
                    input.disabled = false;
                }
            });
            document.querySelector('.profile-edit').classList.add('d-none');
            document.getElementById('updateBtn').classList.remove('d-none');
        }

        function previewFileName(input) {
            // Preview image if possible
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const imgElement = input.parentElement.querySelector('img');
                    if (imgElement) {
                        imgElement.src = e.target.result;
                    } else {
                        const dummyAvatar = input.parentElement.querySelector('.dummy-avatar');
                        if (dummyAvatar) {
                            // Replace dummy avatar with actual image
                            const newImg = document.createElement('img');
                            newImg.src = e.target.result;
                            newImg.alt = "Profile Image";
                            dummyAvatar.parentNode.replaceChild(newImg, dummyAvatar);
                        }
                    }
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</body>
</html>
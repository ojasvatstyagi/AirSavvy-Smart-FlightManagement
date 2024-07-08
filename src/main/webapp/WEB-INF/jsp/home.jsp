<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Management System</title>
 <link rel="stylesheet" href="../assets/css/styles.min.css" />

<style>
    body {
        font-family: 'Helvetica Neue', Arial, sans-serif;
        background: url('../assets/images/backgrounds/mainPage.png') no-repeat center center fixed;
        background-size: cover;
        margin: 0;
        height: 65vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    #container {
        text-align: center;
    }
    #container img {
        width: 150px; /* Adjust as needed */
        cursor: pointer;
        margin-top: 500px;
        transition: transform 0.3s ease;
    }
    #container img:hover {
        transform: scale(1.1);
    }
    @media (max-width: 768px) {
        body {
            background-size: auto 100vh;
        }
    }
</style>
</head>
<body>

<!-- Hero Section -->
    <section id="hero" class="hero section">
      <div class="container text-center">
        <div class="d-flex flex-column justify-content-center align-items-center">
          <div class="d-flex" data-aos="fade-up" data-aos-delay="200">
           <a href="/loginpage"><button type="button" class="btn btn-primary">Get Started</button></a>
          </div>
        </div>
      </div>
     </section>
</body>
</html>
    
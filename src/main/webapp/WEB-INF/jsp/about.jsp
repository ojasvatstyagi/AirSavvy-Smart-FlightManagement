<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Flight Management System - About</title>
<link rel="stylesheet" href="../assets/css/styles.min.css" />

<style>
  /* Typography and readability */
  body {
    font-family: 'Helvetica Neue', Arial, sans-serif;
    background: #f8f9fa; /* light gray background */
    color: #212529;
  }
  h2 {
    font-weight: 700;
    margin-bottom: 1rem;
    color: #0d6efd; /* Bootstrap primary blue */
  }
  p {
    font-size: 1.1rem;
    line-height: 1.6;
    margin-bottom: 1rem;
  }
  /* Icon + text alignment */
  .contact-info i {
    margin-right: 0.75rem;
    font-size: 1.2rem;
    vertical-align: middle;
    color: #0d6efd;
  }
  .contact-info p {
    margin-bottom: 0.5rem;
    display: flex;
    align-items: center;
  }
  /* Portfolio links */
  .portfolio-links a {
    color: #0d6efd;
    margin-left: 0.3rem;
    display: inline-block;
    margin-bottom: 0.5rem;
    text-decoration: none;
  }
  .portfolio-links a:hover {
    text-decoration: underline;
  }
  /* Form */
  form {
    max-width: 480px;
    margin: 1.5rem auto 2rem auto;
  }
  /* Container spacing */
  .section {
    padding: 2rem 1rem;
  }
</style>
</head>
<body>

<div class="container section">
  <!-- About Company -->
  <div class="mb-5">
    <h2>About Company</h2>
    <p>Our company has been at the forefront of the industry for over a decade, delivering top-notch services and products to our clients worldwide. We pride ourselves on our commitment to excellence, innovation, and customer satisfaction.</p>
    <p>We believe in fostering a culture of continuous improvement and innovation. By staying ahead of industry trends and embracing new technologies, we provide clients with cutting-edge solutions that drive their success. Our core values of integrity, respect, and excellence guide everything we do.</p>
  </div>

  <!-- Our Values -->
  <div class="mb-5">
    <h2>Our Values</h2>
    <p><strong>MISSION:</strong> To deliver exceptional value through innovative solutions and unparalleled service. We strive to achieve excellence in all aspects of our business, ensuring we meet and exceed client expectations.</p>
    <p><strong>VISION:</strong> To be a global leader recognized for our commitment to quality, innovation, and customer satisfaction. We aim to create a sustainable future by continuously improving processes, embracing technologies, and fostering collaboration.</p>
  </div>

  <!-- Portfolio -->
  <div class="mb-5 text-center">
    <h2>Portfolio</h2>
    <img src="../assets/images/profile/profile.png" alt="Profile photo of Ojas Tyagi" class="img-fluid rounded-circle mb-3" style="max-width: 200px; height:auto;">
    <p><strong>Ojas Tyagi</strong></p>
    <p>Aspiring Java Developer</p>
    <div class="portfolio-links">
      <p><i class="ti ti-brand-github"></i><a href="https://github.com/ojasvatstyagi" target="_blank" rel="noopener noreferrer">GitHub Account</a></p>
      <p><i class="ti ti-brand-linkedin"></i><a href="https://www.linkedin.com/in/ojas-tyagi" target="_blank" rel="noopener noreferrer">My LinkedIn</a></p>
      <p><i class="ti ti-user"></i><a href="https://ojasvatstyagi.github.io/Portfolio/" target="_blank" rel="noopener noreferrer">Visit My Portfolio</a></p>
    </div>
  </div>

  <!-- Contact Section -->
  <div class="mb-5 text-center">
    <h2>Contact</h2>

    <form action="/about" method="post">
      <div class="mb-3">
        <input type="text" class="form-control" id="name" name="name" placeholder="Name" required />
      </div>
      <div class="mb-3">
        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required />
      </div>
      <div class="mb-3">
        <textarea rows="5" class="form-control" id="comment" name="comment" placeholder="Comment"></textarea>
      </div>
      <button type="submit" class="btn btn-primary px-5">Submit</button>
    </form>

    <a class="text-primary fw-bold" href="/index">Back Home</a>
  </div>
</div>

<script src="../assets/libs/jquery/dist/jquery.min.js"></script>
<script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="../assets/js/sidebarmenu.js"></script>
<script src="../assets/js/app.min.js"></script>
<script src="../assets/libs/simplebar/dist/simplebar.js"></script>
</body>
</html>
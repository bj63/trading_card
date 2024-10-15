<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Side Navigation Bar</title>

    <!-- Google Fonts and Stylesheets -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@100..900&display=swap" rel="stylesheet">

    <link href="/pub/css/global.css" rel="stylesheet">

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <style>
        /* Custom side navbar styling */
        .side-nav {
            height: 100%;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
            z-index: 1000; /* Ensure sidebar is above other content */
        }

        .side-nav a {
            width: 200px; /* Adjust as needed */
            flex-shrink: 0;
            padding: 10px 15px;
            text-decoration: none;
            font-size: 22px;
            color: #818181;
            display: block;
            transition: 0.3s;
        }

        .side-nav a:hover {
            color: #f1f1f1;
        }

        .side-nav .close-btn {
            position: absolute;
            top: 10px;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        /* Button to open the side navbar */
        .open-btn {
            font-size: 20px;
            cursor: pointer;
            background-color: #111;
            color: white;
            padding: 10px 15px;
            border: none;
            position: fixed;
            top: 15px;
            left: 15px;
            z-index: 1001; /* Ensure button is above sidebar */
        }

        /* Main content styling */
        .main-content {
            flex-grow: 1;
            padding-left: 20px; /* Add space between side menu and content */
            box-sizing: border-box;

        }

        .side-nav.closed + .main-content {
            margin-left: 0;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .side-nav {
                width: 100%;
                height: auto;
                position: relative;
            }

            .side-nav.closed {
                width: 0;
            }

            .main-content {
                flex-grow: 1;
                padding-left: 20px; /* Add space between side menu and content */
                box-sizing: border-box;            }
        }
    </style>
</head>
<body>

<!-- Button to open the side navbar -->
<button class="open-btn" onclick="openNav()">☰ Open Side Menu</button>

<!-- Side Navbar -->
<div id="mySidenav" class="side-nav">
    <a href="javascript:void(0)" class="close-btn" onclick="closeNav()">&times;</a>

    <a href="/">Home</a>
    <sec:authorize access="!isAuthenticated()">
        <a href="/user/loginPageUrl">Log In</a>
        <a href="/user/create-user">Create Account</a>
    </sec:authorize>

    <a href="/card/search">Search Cards</a>

    <sec:authorize access="hasAnyAuthority('ADMIN')">
        <a href="/admin/editCardSearch">Edit Cards</a>
        <a href="/admin/userSearch">Edit Users</a>
        <a href="/admin/dashboard">Admin Dashboard</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="/user/collections">My Collections</a>
        <a href="/user/detail">My Account</a>
        <a href="/login/logout">Log Out</a>
    </sec:authorize>
</div>

<!-- Main Content Area -->
<div class="main-content">
    <!-- Page-specific content goes here -->
    <!-- Include page-specific JSP content here -->
</div>

<!-- JavaScript to toggle the navbar -->
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
</script>

</body>
</html>

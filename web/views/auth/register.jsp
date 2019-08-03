<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="/css/reg/main.css">

    <!-- Title Page-->
    <title>Register</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;

        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }
    </style>
    <!-- Icons font CSS-->

    <!-- Main CSS-->
<%--    <link href="../../css/reg/main.css" rel="stylesheet" media="all">--%>
</head>

<body style="">
<form action="/register" method="post">
    <div class="container justify-content-center shadow bg-light p-3 rounded-lg mt-5" style="border-bottom: 2px solid #f06060;;">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

                <label for="first_name"><b>First name</b></label>
                <input type="text" placeholder="Enter your name" id="first_name" name="first_name" >

                    <label for="last_name"><b>Last name</b></label>
                    <input type="text" placeholder="Enter your surname" id="last_name" name="last_name" >




                <label for="address"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" id="address" name="address" >

                    <label for="birthday"><b>Birthday</b></label>
                    <input type="text" placeholder="Enter your birthday" id="birthday" name="birthday" >

                <label for="email"><b>Email</b></label>
                <input type="email" placeholder="Enter Email" id="email" name="email" required>

                    <label for="email"><b>Phone number</b></label>
                    <input type="text" placeholder="Enter Phone number" id="phone" name="phone" >




                    <label for="login"><b>Login</b></label>
                    <input type="text" placeholder="Enter Login" id="login" name="username" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" id="psw" name="password" required>



        <hr>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

        <button type="submit" class="registerbtn" style="background: #f06060; color: white;">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="/">Sign in</a>.</p>
    </div>
</form>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
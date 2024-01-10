document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("login-status-message").innerHTML = "";
    function checkPass() {    
        var password = document.getElementById("signup-password");
        var confirmPass = document.getElementById("signup-confirm-password");
        var message = document.getElementById("password-match-message");
        var createAccountBtn = document.getElementById("create-account");

        if (password.value !== confirmPass.value) {
            message.innerHTML = "Please enter the same password!";
            createAccountBtn.disabled = true;
            createAccountBtn.style.backgroundColor = "darkred";
            createAccountBtn.style.cursor = "not-allowed";
        } else {
            message.innerHTML = ""; // Clear message
            createAccountBtn.disabled = false;
            createAccountBtn.style.backgroundColor = "#C00000";
            createAccountBtn.style.cursor = "pointer";
        }
    }
    
    var passwordField = document.getElementById("signup-password");
    var confirmPassField = document.getElementById("signup-confirm-password");
    
    // Add event listeners to the password fields
    passwordField.addEventListener("input", checkPass);
    confirmPassField.addEventListener("input", checkPass);
    
});

function sendLoginData(event) {
    event.preventDefault(); // Prevents form from submitting traditionally

    var xhr = new XMLHttpRequest();
    
    // FOR OTHER USERS: update relative path below
    // "/(name of root folder)/processLogIn?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/processLogIn?" + new URLSearchParams(new FormData(event.target)).toString(), true);
    xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			var status = xhr.status;
			if (status == 0 || (status >= 200 && status < 400)) {
	            var response = xhr.responseText
	            if (response == "Invalid username or password!") {
					displayResult(response, false);
				}
	            else {
					window.location.href = "home.html";
				}
	        } else {
				 console.log("Error: " + status + " code.")
			}
		}
    };
    xhr.send();
    return false;
}

function sendFormData(event) {
    event.preventDefault(); // Prevents form from submitting traditionally

    var xhr = new XMLHttpRequest();
    
    // FOR OTHER USERS: update relative path below
    // "/(name of root folder)/processSignUp?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/processSignUp?" + new URLSearchParams(new FormData(event.target)).toString(), true);
    xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			var status = xhr.status;
			if (status == 0 || (status >= 200 && status < 400)) {
	            var response = xhr.responseText
	            if (response == "Username or email already exists.") {
					displayResult(response, true);
				}
	            else {
					window.location.href = "home.html";
				}
	        } else {
				 console.log("Error: " + status + " code.");
			}
		}
    };
    xhr.send();
    return false;
}

function displayResult(data, signUp) {
	var status = "";
	if (signUp) {
		status = "password-match-message";
	} else {
		status = "login-status-message";
	}
    var errorMess = document.getElementById(status);
    errorMess.innerHTML = ""; // Clear previous results
    errorMess.innerHTML = data;
}
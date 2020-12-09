const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    fetch('http://localhost:8080/api/v1/user') // We make a get fetch    // request where users are stored
        .then(res=>res.json())
        .then(usersArray => {
            let user = usersArray.find(function(user){
                return user.username === username.value // Then we        // check if there is a user with a value given
            })
            if (user){
                if (user.password === password.value){
                    localStorage.id = user.id // If there is so, we then store
                    alert("You have successfully logged in.");
                    window.location.href = "/home";
                }
                }
            loginErrorMsg.style.opacity = 1;
            })

})
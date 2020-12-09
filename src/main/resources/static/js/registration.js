function postFetchForSignUp() {
    let nameInput = document.querySelector("#name")
    let usernameInput = document.querySelector("#username")
    fetch('http://localhost:3000/users', { // First, we make a Post fetch request where we want to store our users.
        method: 'POST',
        headers: {
            'Content-Type':'application/json',
            'Accept':'application/json'
        },
        body: JSON.stringify({
            name: nameInput.value,
            username: usernameInput.value
        })
    })
        .then(res=>res.json())
        .then(user => {
            localStorage.clear() // If there was a user signed in, this will                                                            // clear it up
            localStorage.id = user.id  // Then we can store the id we got
           // slapUser(user)
            logOutButton()
        })
}
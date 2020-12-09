function logOutButton(){
    let logOutButton = document.createElement("button")
    logOutButton.className = "log-out-button"
    logOutButton.innerText = "Log Out"
    signDiv.append(logOutButton)
    logOutButton.addEventListener('click', e=>{
        localStorage.clear() // We clear localStorage like so
    })
}
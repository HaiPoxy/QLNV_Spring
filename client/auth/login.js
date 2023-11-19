addListeners();

async function addListeners() {
    document.getElementById("login-form").onsubmit = onLoginFormSubmit;
}

async function onLoginFormSubmit(e) {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/api/v1/auth/login", {
        method: "POST",
        body: new FormData(this),
    });
    if (response.ok) {
        localStorage.setItem("bearer_token", response.headers.get("Authorization"));
        setTimeout(() => location.replace("/pages/departments.html"), 500);
    }
}

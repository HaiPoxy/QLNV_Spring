addListeners();

async function addListeners() {
    document.getElementById("register-form").onsubmit = onRegisterFormSubmit;
}

async function onRegisterFormSubmit(e) {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/api/v1/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            firstName: document.getElementById("first-name").value,
            lastName: document.getElementById("last-name").value,
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            role: document.getElementById("role").value,
        }),
    });
    if (response.ok) {
        setTimeout(() => location.replace("/auth/login.html"), 500);
    }
}

function addListeners() {
    document.getElementById("register-form").onsubmit = async function (e) {
        e.preventDefault();
        const response = await fetch("http://localhost:8080/api/v1/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                firstName: document.getElementById("first-name").value,
                lastName: document.getElementById("last-name").value,
                username: document.getElementById("username").value,
                password: document.getElementById("password").value,
                role: document.getElementById("role").value,
            }),
        });
        if (response.ok) {
            setTimeout(() => location.replace("/auth/login.html"), 1000);
        }
    };
}

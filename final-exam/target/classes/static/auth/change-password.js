addListeners();

async function addListeners() {
    document.getElementById("change-password-form").onsubmit = onChangePasswordFormSubmit;
}

async function onChangePasswordFormSubmit(e) {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/api/v1/auth/update", {
        method: "PUT",
        headers: {
            Authorization: localStorage.getItem("bearer_token"),
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            oldPassword: document.getElementById("old-password").value,
            newPassowrd: document.getElementById("new-password").value,
        }),
    });
    if (response.ok) {
        setTimeout(() => location.replace("/pages/departments.html"), 1000);
    }
}

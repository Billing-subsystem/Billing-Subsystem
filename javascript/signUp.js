document.getElementById('submit-btn').addEventListener('click', () => {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (!username || !email || !password) {
        alert('Please fill in all fields.');
        return;
    }

    if (password !== confirmPassword) {
        alert('Passwords do not match.');
        return;
    }

    fetch(`/api/register?username=${encodeURIComponent(username)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`)
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            window.location.href = 'login.html';
        } else {
            alert('Registration failed. Email may already be in use.');
        }
    });
});

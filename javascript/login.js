document.getElementById('login-btn').addEventListener("click", ()=>{
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(fetch(`/api/login?email=${email}&password=${password}`))
    .then(res => res.json())
    .then(data => {
        if(data.accountId){
            localStorage.setItem('accountId', data.accountId);
            localStorage.setItem('subscriptionPrice', data.subscriptionPrice);
            window.location.href = 'home.html';
        } else {
            document.getElementById('popup-modal').classList.remove('hidden');
            document.getElementById('popup-modal').classList.add('flex');
            document.getElementById('error-text').textContent = 'Invalid email or password';
        }
    });
});
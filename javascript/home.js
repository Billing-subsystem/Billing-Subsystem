const accountId = localStorage.getItem('accountId');

if(accountId){
    fetch(`/api/account?id=${accountId}`)
    .then(res => res.json())
    .then(data => {
        const amount = `$${parseFloat(data.balance).toFixed(2)}`;
        document.getElementById('balance').textContent = amount;
        document.getElementById('last-paid').textContent = amount;
        if (data.lastPaymentDate) {
            document.getElementById('last-date').textContent = data.lastPaymentDate;
        }
        localStorage.setItem('balance', data.balance);
    });
}

document.getElementById('pay-btn').addEventListener("click", ()=>{
    if(!accountId){
        window.location.href = "login.html";
    } else {
        window.location.href = "payment.html";
    }
})
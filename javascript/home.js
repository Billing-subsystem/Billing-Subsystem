const accountId = localStorage.getItem('accountId');

if(accountId){
    fetch(`http://localhost:8080/account?id=${accountId}`)
    .then(res => res.json())
    .then(data => {
      document.getElementById('balance').textContent = `$${data.balance.toFixed(2)}`;
      document.getElementById('last-paid').textContent = `$${data.balance.toFixed(2)}`;
      document.getElementById('last-date').textContent = data.lastPaymentDate;
    });
}

document.getElementById('pay-btn').addEventListener("click", ()=>{
    if(!accountId){
        window.location.href = "login.html";
    } else {
        window.location.href = "payment.html";
    }
})
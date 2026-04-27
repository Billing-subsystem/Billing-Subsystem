const accountId = localStorage.getItem('accountId');
const subscriptionPrice = localStorage.getItem('subscriptionPrice') || 0;

document.getElementById('original-price').textContent = `$${parseFloat(subscriptionPrice).toFixed(2)}`;
document.getElementById('total').textContent = `$${parseFloat(subscriptionPrice).toFixed(2)}`;

document.getElementById('pay-now-btn').addEventListener('click', () => {
    const cardNumber = document.getElementById('card-number-input').value.replace(/\D/g, '');
    const expDate = document.getElementById('card-expiration-input').value;
    const cvv = document.getElementById('cvv-input').value;

    if (!cardNumber || !expDate || !cvv) {
        alert('Please fill in all card details.');
        return;
    }

    if (accountId) {
        fetch(`/api/saveCreditCard?accountId=${accountId}&cardNumber=${cardNumber}&expDate=${encodeURIComponent(expDate)}&cvv=${cvv}`)
            .then(() => { window.location.href = 'reciept.html'; });
    } else {
        window.location.href = 'reciept.html';
    }
});
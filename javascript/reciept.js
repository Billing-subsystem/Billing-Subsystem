const subscriptionPrice = parseFloat(localStorage.getItem('subscriptionPrice') || localStorage.getItem('balance') || '0');
const formatted = `$${subscriptionPrice.toFixed(2)}`;

document.getElementById('receipt-price').textContent = formatted;
document.getElementById('receipt-total').textContent = formatted;
document.getElementById('receipt-message').textContent =
    `Your Payment has been approved. Your credit card has been charged ${formatted}.`;

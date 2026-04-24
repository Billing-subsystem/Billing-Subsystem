const accountId = localStorage.getItem('accountId');
const subscriptionPrice = localStorage.getItem('subscriptionPrice') || 0;

// Update the price summary panel
document.getElementById('original-price').textContent = `$${parseFloat(subscriptionPrice).toFixed(2)}`;
document.getElementById('total').textContent = `$${parseFloat(subscriptionPrice).toFixed(2)}`;
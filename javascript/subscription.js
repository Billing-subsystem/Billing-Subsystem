document.querySelectorAll('[data-price]').forEach(btn => {
    btn.addEventListener('click', () => {
        const newPrice = btn.getAttribute('data-price');
        localStorage.setItem('subscriptionPrice', newPrice);

        const accountId = localStorage.getItem('accountId');
        if (accountId) {
            fetch(`/api/updateSubscription?id=${accountId}&price=${newPrice}`)
            .then(res => res.json())
            .then(() => {
                window.location.href = 'payment.html';
            });
        } else {
            window.location.href = 'payment.html';
        }
    });
});
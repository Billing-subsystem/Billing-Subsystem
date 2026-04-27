document.querySelectorAll('[data-price]').forEach(btn => {
    btn.addEventListener('click', () => {
      const newPrice = btn.getAttribute('data-price');
      localStorage.setItem('subscriptionPrice', newPrice);
      window.location.href = 'payment.html'; 
    });
  });
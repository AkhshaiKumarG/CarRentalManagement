const tbody = document.getElementById("myBookingsBody");

    fetch("http://localhost:8080/current-user")
      .then(res => res.json())
      .then(user => {
        fetch(`http://localhost:8080/my-bookings?email=${user.email}`)
          .then(res => res.json())
          .then(bookings => {
            if (bookings.length === 0) {
              tbody.innerHTML = `<tr><td colspan="7" class="text-center">No bookings found</td></tr>`;
              return;
            }

            bookings.forEach(b => {
              tbody.innerHTML += `
                <tr>
                  <td>${b.id}</td>
                  <td>${b.car.carName}</td>
                  <td>${b.car.brand}</td>
                  <td>${b.pickupDate}</td>
                  <td>${b.returnDate}</td>
                  <td>₹${b.totalAmount}</td>
                  <td><span class="badge ${getStatusClass(b.status)}">${b.status}</span></td>
                </tr>`;
            });
          });
      });

    function getStatusClass(status) {
      status = status.toLowerCase();
      if (status.includes("cancelled")) return "bg-danger";
      if (status.includes("completed")) return "bg-success";
      if (status.includes("in-progress")) return "bg-warning text-dark";
      return "bg-info";
    }

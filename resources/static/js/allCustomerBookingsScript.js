let currentUserRole = "";

fetch("http://localhost:8080/all-Customer-Bookings")
  .then((response) => response.json())
  .then((customers) => {
    let customerAccordion = document.getElementById("customerAccordion");
    customerAccordion.innerHTML = "";

    customers.forEach((customer, index) => {
      const collapseId = `collapse${index}`;
      const headingId = `heading${index}`;

      let bookingsHTML = "";
      customer.carBookings.forEach(booking => {
        const carName = booking.car?.carName || "N/A";
        let badgeClass = "bg-secondary";
        const status = booking.status?.toLowerCase();

        if (status === "completed") badgeClass = "bg-success";
        else if (status === "upcoming") badgeClass = "bg-warning text-dark";
        else if (status === "cancelled") badgeClass = "bg-danger";

        bookingsHTML += `
          <tr>
            <td>#${booking.id}</td>
            <td>${carName}</td>
            <td>${booking.numOfDays} Days</td>
            <td><span class="badge ${badgeClass}">${booking.status}</span></td>
          </tr>
        `;
      });

      customerAccordion.innerHTML += `
        <div class="accordion-item animate__animated animate__fadeInUp">
          <h2 class="accordion-header" id="${headingId}">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#${collapseId}" aria-expanded="false" aria-controls="${collapseId}">
              ${customer.name} &mdash; Total Bookings: ${customer.carBookings.length}
            </button>
          </h2>
          <div id="${collapseId}" class="accordion-collapse collapse" aria-labelledby="${headingId}" data-bs-parent="#customerAccordion">
            <div class="accordion-body">
              <div class="table-responsive card-table">
                <table class="table table-bordered table-hover">
                  <thead class="table-light">
                    <tr>
                      <th>Booking ID</th>
                      <th>Car</th>
                      <th>Days</th>
                      <th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    ${bookingsHTML}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      `;
    });
  })


window.addEventListener("DOMContentLoaded", () => {


  fetch("http://localhost:8080/current-user")
    .then(response => response.json())
    .then(user => {
      currentUserRole = user.role;
      sessionStorage.setItem("userRole", currentUserRole)
      if (currentUserRole === "ADMIN") {
        document.getElementById("cancelBookingSection").style.display = "block";
      } else {
        document.getElementById("cancelBookingSection").style.display = "none";
      }
    })
    .catch(error => {
      console.error("Error fetching user:", error);
    });
});

function cancelBooking() {
  const bookingId = document.getElementById("cancelBookingIdInput").value;

  if (!bookingId || isNaN(bookingId)) {
    document.getElementById("cancelStatusMsg").textContent = "Please enter a valid booking ID.";
    return;
  }

  const userRole = sessionStorage.getItem("userRole");

  if (userRole !== "ADMIN") {
    const modal = new bootstrap.Modal(document.getElementById('unauthorizedCancelModal'));
    modal.show();
    return;
  }

  fetch(`http://localhost:8080/bookings/cancel/${bookingId}`, {
    method: "POST"
  })
    .then(res => res.text())
    .then(msg => {
      document.getElementById("cancelStatusMsg").textContent = msg;
    })
    .catch(err => {
      console.error("Cancel Error:", err);
      document.getElementById("cancelStatusMsg").textContent = "An error occurred while cancelling.";
    });
}

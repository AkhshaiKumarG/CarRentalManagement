let viewAllCars = document.getElementById("viewAllCars")


fetch("http://localhost:8080/dashboard")
.then((response) => response.json())
.then((cars) => {
    let html = ""
    viewAllCars.innerHTML = "";
    cars.forEach(car => {
        let availabilityClass = car.availability === "Yes" ? "text-success" : "text-danger";
        let disabledAttr = car.availability === "Yes" ? "" : "disabled";

        viewAllCars.innerHTML += `
        <div class="col-md-4">
          <div class="car-card shadow-sm mb-4">
            <img src="${car.imageUrl}" class="card-img-top" alt="Car Image">
            <div class="card-body  m-3">
              <h5 class="card-title">${car.carName}</h5>
              <p class="card-text mb-1"><strong>Car Id:</strong> ${car.id}</p>
              <p class="card-text mb-1"><strong>Brand:</strong> ${car.brand}</p>
              <p class="card-text mb-1"><strong>Price/Day:</strong> ₹${car.pricePerDay}</p>
              <p class="card-text mb-2"><strong>Availability:</strong> <span class="${availabilityClass}">${car.availability}</span></p>
            </div>
          </div>
        </div>
        `
    });
})
.catch(e => {
    console.log("Error occured")
})

function searchCars() {
  const input = document.getElementById("searchInput").value.toLowerCase();
  const carCards = document.querySelectorAll("#viewAllCars .col-md-4")
  carCards.forEach(car => {
    const text = car.innerText.toLowerCase();
    car.style.display = text.includes(input) ? "block" : "none"
  })
}

let addCarBtn = document.getElementById("addCarBtn");
window.addEventListener("DOMContentLoaded", () => {
fetch("http://localhost:8080/current-user")
.then((response) => response.json())
.then(user => {
  if (user.role !== "ADMIN") {
    addCarBtn.style.display = "none";
  }
})
});

let allCustomerBookingsBtn = document.getElementById("allCustomerBookingsBtn")
fetch("http://localhost:8080/current-user")
.then((response) => response.json())
.then(user => {
  if (user.role !== "ADMIN") {
    allCustomerBookingsBtn.style.display = "none";
  }
})

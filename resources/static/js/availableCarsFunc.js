let availableCars = document.getElementById("availableCars");

fetch("http://localhost:8080/dashboard/available")
.then((response) => response.json())
.then((cars) => {
    availableCars.innerHTML = "";
    cars.forEach(car => {
        availableCars.innerHTML += `
        <div class="col-md-4 mb-4">
        <div class="card car-card">
          <img src= ${car.imageUrl} class="card-img-top" alt=${car.carName}>
          <div class="card-body">
            <h5 class="card-title">${car.carName}</h5>
            <p class="card-text mb-1"><strong>Car Id:</strong> ${car.id}</p>
            <p class="car-info mb-1"><strong>Brand:</strong> ${car.brand}</p>
            <p class="car-info mb-1"><strong>Price/Day:</strong> ${car.pricePerDay}</p>
            <a href="bookCar.html"><button class="btn btn-primary w-100" id=${car.car_id} >Book Now</button></a>
          </div>
        </div>
      </div>
        `
    });
})


function searchCars() {
  const input = document.getElementById("searchInput").value.toLowerCase();
  const carCards = document.querySelectorAll("#availableCars .col-md-4")
  carCards.forEach(car => {
    const text = car.innerText.toLowerCase();
    car.style.display = text.includes(input) ? "block" : "none"
  })
}

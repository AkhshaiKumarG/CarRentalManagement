function validateDates() {
        const pickupDateValue = document.getElementById("pickupDate").value;
        const returnDateValue = document.getElementById("returnDate").value;
        const errorMessage = document.getElementById("errorMessage");

        if (!pickupDateValue || !returnDateValue) {
          errorMessage.textContent = "Enter both pickup and return dates!";
          return false;
        }
        const pickupDate = new Date(pickupDateValue);
        const returnDate = new Date(returnDateValue);
        const today = new Date();
        today.setHours(0,0,0,0);

        if (pickupDate < today) {
          errorMessage.textContent = "Pickup date cannot be in the past";
          return false;
        }
        if (returnDate <= pickupDate) {
          errorMessage.textContent = "Return date must be after pickup date";
          return false;
        }
        errorMessage.textContent = "";
        return true;
      }

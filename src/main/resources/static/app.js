// Fetch the client ID from backend and load the PayPal SDK dynamically
fetch("http://localhost:8080/api/client-id")
  .then((res) => res.json())
  .then((data) => {
    const script = document.createElement("script");
    script.src = `https://www.paypal.com/sdk/js?client-id=${data.clientId}&buyer-country=US&currency=USD&components=buttons&enable-funding=venmo`;
    script.onload = () => {
      setupPaypalButtons();
    };
    document.head.appendChild(script);
  })
  .catch((err) => {
    console.error("Error fetching PayPal Client ID:", err);
    resultMessage("Failed to load PayPal SDK");
  });

// All the logic for buttons goes here
function setupPaypalButtons() {
  window.paypal
    .Buttons({
      style: {
        shape: "rect",
        layout: "vertical",
        color: "gold",
        label: "paypal",
      },

      async createOrder() {
        try {
          const response = await fetch("http://localhost:8080/api/orders", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              cart: [
                {
                  id: "1",
                  quantity: "1",
                },
              ],
            }),
          });

          const orderData = await response.json();

          if (orderData.id) {
            return orderData.id;
          }

          const errorDetail = orderData?.details?.[0];
          const errorMessage = errorDetail
            ? `${errorDetail.issue} ${errorDetail.description} (${orderData.debug_id})`
            : JSON.stringify(orderData);

          throw new Error(errorMessage);
        } catch (error) {
          console.error(error);
          resultMessage(`Could not initiate PayPal Checkout...<br><br>${error}`);
        }
      },

      async onApprove(data, actions) {
        try {
          const response = await fetch(
            `http://localhost:8080/api/orders/${data.orderID}/capture`,
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );

          const orderData = await response.json();
          const errorDetail = orderData?.details?.[0];

          if (errorDetail?.issue === "INSTRUMENT_DECLINED") {
            return actions.restart();
          } else if (errorDetail) {
            throw new Error(`${errorDetail.description} (${orderData.debug_id})`);
          } else if (!orderData.purchase_units) {
            throw new Error(JSON.stringify(orderData));
          } else {
            const transaction =
              orderData?.purchase_units?.[0]?.payments?.captures?.[0] ||
              orderData?.purchase_units?.[0]?.payments?.authorizations?.[0];
            resultMessage(
              `Transaction ${transaction.status}: ${transaction.id}<br>
            <br>See console for all available details`
            );
            console.log(
              "Capture result",
              orderData,
              JSON.stringify(orderData, null, 2)
            );
          }
        } catch (error) {
          console.error(error);
          resultMessage(
            `Sorry, your transaction could not be processed...<br><br>${error}`
          );
        }
      },
    })
    .render("#paypal-button-container");
}

function resultMessage(message) {
  const container = document.querySelector("#result-message");
  container.innerHTML = message;
}

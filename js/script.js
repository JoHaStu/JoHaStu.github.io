let emailElement = document.querySelector("#email"); //pass in any CSS selector here to grab element (class, HTML element, id)
let messageElement = document.querySelector("#message");
let submitButton = document.querySelector("#submit-button");
submitButton.addEventListener("click", function (e) {
  e.preventDefault();

  let emailValue = emailElement.value;
  let messageValue = messageElement.value;

  if (emailValue.includes("@")) {
    alert("thanks for message");
  } else {
    alert("oh no, that doesn't look like a valid email address");
  }
});

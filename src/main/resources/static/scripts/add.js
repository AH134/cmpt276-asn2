const colorPicker = document.getElementById("color");
colorPicker.addEventListener("change", (e) => {
console.log(e.target.value);
    colorPicker.value = e.target.value;
})

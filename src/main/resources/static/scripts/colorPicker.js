const colorPicker = document.getElementById("color");
const colorElement = document.getElementById("color-hexcode");
const editRectangleElement = document.getElementById("edit-rectangle");
colorPicker.addEventListener("change", (e) => {
    colorPicker.value = e.target.value;
    colorElement.textContent = colorPicker.value;
    editRectangleElement.style.backgroundColor = colorPicker.value;
})
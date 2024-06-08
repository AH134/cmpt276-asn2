let isEdit = false;
const editButton = document.getElementById("edit-button");
editButton.addEventListener("click", (e) => {
    const editContainter = document.getElementById("edit-container");
    const formButton = document.getElementById("form-button")

    isEdit = !isEdit;
    if (isEdit) {
        editContainter.style.display = "block";
        formButton.style.display = "block";
        editButton.className = "w-20 h-10 rounded-md text-slate-100 bg-rose-600 hover:bg-rose-700 flex justify-center items-center";
        editButton.textContent = "Cancel";

    } else {
        editContainter.style.display = "none";
        formButton.style.display = "none";
        editButton.className = "w-20 h-10 rounded-md text-slate-100 bg-blue-700 hover:bg-blue-800 flex justify-center items-center";
        editButton.textContent = "Edit";
    }
});
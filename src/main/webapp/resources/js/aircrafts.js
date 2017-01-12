/**
 * @author Ruslan Lazin
 */

function enable(aircraftId) {
    document.getElementById("reg_" + aircraftId).removeAttribute("disabled");
    document.getElementById("model_" + aircraftId).removeAttribute("disabled");
    document.getElementById("submit_" + aircraftId).style.visibility = "visible";
    document.getElementById("edit_" + aircraftId).style.visibility = "hidden";
}

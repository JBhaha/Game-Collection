/**
 * view-controller for gameEdit.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readGame();

    document.getElementById("gameEditForm").addEventListener("submit", saveGame);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a game
 */
function saveGame(event) {
    event.preventDefault();
    showMessage("", "info");

    const gameForm = document.getElementById("gameEditForm");
    const formData = new FormData(gameForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/game/";
    const gameUUID = getQueryParam("uuid");
    if (gameUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": "Bearer " + readStorage("token")
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                showMessage("Error while saving", "error");
                console.log(response);
            } else {
                showMessage("Game saved", "info");
                return response;}
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a game
 */
function readGame() {
    const gameUUID = getQueryParam("uuid");
    fetch("./resource/game/read?uuid=" + gameUUID, {
        headers: { "Authorization": "Bearer " + readStorage("token")}
    })
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showGame(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a game
 * @param data  the game-data
 */
function showGame(data) {
    const userRole = getCookie("userRole");
    document.getElementById("gameUUID").value = data.gameUUID;
    document.getElementById("title").value = data.title;
    document.getElementById("release").value = data.release;

    const locked =  !(userRole === "user" || userRole === "admin");
    lockForm("gameEditForm", locked);
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./bookshelf.html";
}
/**
 * view-controller for gameList.html
 * @author Jakub Korona
 */
document.addEventListener("DOMContentLoaded", () => {
    readGames();

    document.getElementById("gameEditForm").addEventListener("submit", saveGame);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * reads all games
 */
function readGames() {
    fetch("./resource/game/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showGameList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the gameList as a table
 * @param data the games
 */
function showGameList(data){
    let tBody = document.getElementById("gameList");
    tBody.innerHTML = "";
    data.forEach(game => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = game.title;
        row.insertCell(-1).innerHTML = game.release;
    });
}
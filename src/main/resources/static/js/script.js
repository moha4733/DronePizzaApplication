const API_BASE = "http://localhost:8080/api"


// Når siden loader, hent leveringer

document.addEventListener("DOMContentLoaded" , () =>{
    hentLeveringer();
});


// Opdater leveringer hvert 60. sekund
setInterval(hentLeveringer, 60000);
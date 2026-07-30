document.addEventListener("DOMContentLoaded", async () => {


});

async function init(){
    const users = await (await fetch("http://localhost:8080/usuarios/userlist")).json();
}
function logout() {
    //debugger;
    console.log("Invoked logout");
    let url = "/users/logout";
    fetch(url, {
        method: "POST"
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            Cookies.remove("Token", response.Token);    //UserName and Token are removed
            Cookies.remove("UserName", response.Username);
            window.open("index.html", "_self");       //open index.html in same tab
        }
    });
}

function UsersLogin() {
    //debugger;
    console.log("Invoked UsersLogin() ");
    let url = "/users/login";
    let formData = new FormData(document.getElementById('LoginForm'));

    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            Cookies.set("Token", response.Token);
            Cookies.set("Username", response.Username);
            window.open("index__logged-in.html", "_self");       //open index.html in same tab
        }
    });
}

function GetUser() {
    //debugger;
    console.log("Invoked GetUser()");
    let url = "/users/get-user";

    fetch(url, {
        method: "GET",
    }).then(response => {
        return response.json();
    }).then(response => {
        var str = JSON.stringify(response);
        var pos = str.slice(13, str.length-2);
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
        } else {
            document.getElementById("user").innerHTML = 'Hello, ' + pos;
        }
    })
}

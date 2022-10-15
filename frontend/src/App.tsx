import React, {useState} from 'react';
import './App.css';
import axios from "axios";

function App() {

    // Say Helo Button
    const [welcomeMessage, setWelcomeMessage] = useState("")

    //LOG IN
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [me, setMe] = useState("")

    // SIGN UP
    const [newUsername, setnewUsername] = useState("")
    const [newPassword, setnewPassword] = useState("")


    function fetchWelcomeMessage() {
        axios.get("/api/hello")
            .then(response => response.data)
            .then(data => setWelcomeMessage(data))
    }

    function handleLogin (){
        // Log in (get session) with username and password
        axios.get("api/user/login", {auth: {username, password}})
            .then(response => response.data)

            .then((data) => setMe(data))
            .then(() => setUsername(""))
            .then(() => setPassword(""))
            .catch(() => alert("Username or Password is wrong!"))
    }

    function handleLogout(){
        axios.get("api/user/logout")
            .then(() => setMe(""))
    }

    // In Register in axios 2 Sachen muss eingegeben werden: 1: URL:api/user/register
    // wie in Postman: In POST wir geben erstmal URL ein und dann in body raw JSON die die 2: data >> { username: ... und password: ... }
    function handleRegister(){
        axios.post("api/user/register", {
            username: newUsername,
            password: newPassword
        })
        .then(response => response.data)

        .then((data) => setMe(data))
        .then(() => setnewUsername(""))
        .then(() => setnewPassword(""))
    }

    return (
        <div className="App">
            <header className="App-header">

                {/*{wenn ? dann : sonst}*/}

                {!me ?
                    <>
                    <h3>Login</h3>
                    <input value={username} onChange={event => setUsername(event.target.value)}/>
                    <input type={"password"} value={password} onChange={event => setPassword(event.target.value)}/>
                    <button onClick={handleLogin}>Login</button>


                    <h3>Sign Up</h3>
                    <input value={newUsername} onChange={event => setnewUsername(event.target.value)} />
                    <input type={"password"} value={newPassword} onChange={event => setnewPassword(event.target.value)} />
                    <button onClick={handleRegister}>Sign Up</button>

                    </>

                    :
                    <>
                    <p>Angemeldet als: {me}</p>
                    <button onClick={handleLogout}>Logout</button>
                    {/*<p>{welcomeMessage}</p>
                    <button onClick={fetchWelcomeMessage}>Say Hello!</button>*/}
                    </>

                }



            </header>
        </div>
    );
}

export default App;

import {BrowserRouter as Router, Link, Switch, Route} from 'react-router-dom';
import Home from "./pages/Home/Home";
import {useState} from "react";
import {UserContext} from "./contexts/user/UserContext";
import {TokenContext} from "./contexts/token/TokenContext";
import {StateContext} from "./contexts/state/StateContext";
function App() {
    const [user, setUser] = useState({});
    const [token,setToken] = useState("");
    if(localStorage.getItem('signIn') === null){
        localStorage.setItem('signIn',0) //0: dont sign in, 1: sign in
    }
  return (
          <TokenContext.Provider value={{token,setToken}}>
              <UserContext.Provider value={{user,setUser}}>
                  <div className="App">
                      <Router>
                          <Switch>
                              <Route path={"/"}>
                                  <Home/>
                              </Route>
                          </Switch>
                      </Router>
                  </div>
              </UserContext.Provider>
          </TokenContext.Provider>
  );
}

export default App;

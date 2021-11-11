import {BrowserRouter as Router, Link, Switch, Route} from 'react-router-dom';
import Home from "./pages/Client/Home";
import Admin from "./pages/Admin/Admin";
import {useEffect, useState} from "react";
import axios from "axios";
import {ProductContext} from "./context/ProductContext";
import ProductDetail from "./pages/Client/ProductDetail";
import ProductPage from "./pages/Client/ProductPage";

function App() {
    const [loading, setLoading] = useState(true)
    const [products, setProducts] = useState([]);
    useEffect(()=>{
        axios({
            method: "get",
            url: "http://localhost:10399/product/all",
        })
            .then(res => {
                setProducts(res.data.data)
                setLoading(false)
            })
            .catch(err => {
                alert(err)
            })
    },[])

    if(localStorage.getItem('login_user') === null){
        localStorage.setItem('login_user',0) //0: dont sign in, 1: sign in
    }
    if(localStorage.getItem('login') === null){
        localStorage.setItem('login',0) //0: dont sign in, 1: sign in
    }
    return (
        <ProductContext.Provider value={{products}}>
            {loading ? <div> </div>:
                <div>
                    <Router>
                        <Switch>
                            <Route path={"/admin"} component={Admin}/>
                            <Route path={"/product/:id"} component={ProductDetail}/>
                            <Route path={"/product"} component={ProductPage}/>
                            <Route path={"/"} component={Home}/>
                            <Route path={"/home"} component={Home}/>
                        </Switch>
                    </Router>
                </div>
            }
        </ProductContext.Provider>
    );
}

export default App;

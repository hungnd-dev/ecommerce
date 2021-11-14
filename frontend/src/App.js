import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Home from "./pages/Client/Home";
import Admin from "./pages/Admin/Admin";
import {useContext, useEffect, useState} from "react";
import axios from "axios";
import {ProductContext} from "./context/ProductContext";
import ProductDetail from "./pages/Client/ProductDetail";
import ProductPage from "./pages/Client/ProductPage";
import Toast from "./components/Toast/Toast";
import {ToastCustomContext} from "./context/ToastContext";
import Development from "./pages/Client/Development";

export default function App() {
    const toast = useContext(ToastCustomContext)
    const [loading, setLoading] = useState(true)
    const [products, setProducts] = useState([]);
    const tempProductList = []
    useEffect(() => {
        axios({
            method: "get",
            url: "http://localhost:10399/product/all",
        })
            .then(res => {
                for (let i in res.data.data) {
                    tempProductList.push(res.data.data[i])
                }
                setProducts([...tempProductList])
                setLoading(false)
            })
            .catch(err => {
                toast.showToast(err.message,"error")
            })
    }, [])
    if (localStorage.getItem('login_user') === null) {
        localStorage.setItem('login_user', 0) //0: dont sign in, 1: sign in
    }
    if (localStorage.getItem('login_admin') === null) {
        localStorage.setItem('login_admin', 0) //0: dont sign in, 1: sign in
    }
    return (
        <ProductContext.Provider value={{products}}>
            {loading ? <div></div> :
                <div>
                    <Toast/>
                    <Router>
                        <Switch>
                            <Route path={"/admin"} component={Admin}/>
                            <Route path={"/product/:id"} component={ProductDetail}/>
                            <Route path={"/product"} component={ProductPage}/>
                            <Route path={"/contact"} component={Development}/>
                            <Route path={"/about"} component={Development}/>
                            <Route path={"/"} component={Home}/>
                            <Route path={"/home"} component={Home}/>
                        </Switch>
                    </Router>
                </div>
            }
        </ProductContext.Provider>
    );
}

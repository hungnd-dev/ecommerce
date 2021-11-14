import {createContext, useContext, useState} from "react";
import axios from "axios";
import {ToastCustomContext} from "./ToastContext";

const CartContext = createContext();
//this context provide all of state, func for cart
function CartProvider({children}) {
    const toast = useContext(ToastCustomContext)
    const token = "Bearer ".concat(localStorage.getItem("token_user"))
    const [cartOpen,setCartOpen] = useState(false)
    const [accountOpen,setAccountOpen] = useState(false)
    const [cart,setCart] = useState({
        amount:0.0,
        detail:[]
    })
    function addToCart(productId){
        axios.get("http://localhost:10399/user/cart/add/?product_id=" + productId, {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Add to cart successfully", "success")
                    setCart(res.data.data)
                    setCartOpen(true)
                }
            })
            .catch(err => {
                toast.showToast("You can login for this action", "error")
                setAccountOpen(true)
            })
    }

    function reduceCart(productId){
        axios.get("http://localhost:10399/user/cart/reduce/?product_id=" + productId, {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Reduce successfully", "success")
                    setCart(res.data.data)
                }
            })
            .catch(err => toast.showToast(err.message, "error"))
    }

    const func = {
        addToCart,
        reduceCart,
        cartOpen,
        setCartOpen,
        cart,
        setCart,
        accountOpen,
        setAccountOpen
    }
    return(
        <CartContext.Provider value={func}>
            {children}
        </CartContext.Provider>
    )
}

export {CartContext, CartProvider}
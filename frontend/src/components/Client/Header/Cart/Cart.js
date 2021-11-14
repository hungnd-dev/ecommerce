import React, {useContext, useEffect, useLayoutEffect, useState} from 'react'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import './Cart.css'
import '../../../../App.css'
import axios from "axios";
import {CartContext} from "../../../../context/CartContext";
import { faMinus, faPlus, faTrash,faTimes } from '@fortawesome/free-solid-svg-icons';
import CartDetail from "./CartDetail";
import Order from "../Order/Order";
import {GlobalContext} from "../../../../context/GlobalContext";

export default function Cart(props) {
    const cartContext = useContext(CartContext)
    const global = useContext(GlobalContext)
    const [orderOpen, setOrderOpen] = useState(false)
    useEffect(()=>{
        axios.get("http://localhost:10399/user/cart/view/",{headers:{Authorization:"Bearer ".concat(localStorage.getItem("token_user"))}})
            .then(res=>{
                cartContext.setCart(res.data.data)
            })
    },[])
    return (
        <div className={props.cartOpen === false ? "Cart displayNone" : "Cart"}>
            <div className="cart-header">
                <div className="cart-header__title">
                    Your Cart
                </div>
                <div className="cart-header__close"
                     onClick={()=> {
                         props.clickToClose()
                         setOrderOpen(false)
                     }}>
                    <FontAwesomeIcon icon={faTimes} className="icon"/>
                </div>
            </div>
            <div>
                <CartDetail detail={cartContext.cart.detail} cart = {cartContext.cart} convert = {global.convert}/>
            </div>
            <Order orderOpen={orderOpen} amount={cartContext.cart.amount}/>
            <div className={orderOpen===true?"cart-checkout-box flex-center displayNone":"cart-checkout-box flex-center"}>
                <div className="cart-checkout-text flex">
                    <p>Total: </p>
                    {global.convert(cartContext.cart.amount)}
                </div>
                <div
                    className="cart-checkout-btn btn"
                    onClick={()=>{
                        if (cartContext.cart.amount > 0) {
                            setOrderOpen(true)
                        }
                    }}
                >
                    Checkout
                </div>
            </div>

        </div>
    )
}
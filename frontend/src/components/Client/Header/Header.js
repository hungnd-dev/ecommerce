import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCartPlus, faClipboardList, faSearch} from "@fortawesome/free-solid-svg-icons";
import React, {useContext, useEffect, useState} from "react";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";
import Search from "./Search/Search";
import Cart from "./Cart/Cart";
import './Header.css'
import '../../../App.css'
import Account from "./Account/Account";
import axios from "axios";
import {CartContext} from "../../../context/CartContext";
import OrderDetail from "./Order/OrderDetail";

export default function Header(props) {
    const cartContext = useContext(CartContext)
    const [searchOpen, setSearchOpen] = useState(false)
    const [orderOpen, setOrderOpen] = useState(false)
    const [loginUser, setLoginUser] = useState(localStorage.getItem("login_user"))
    const clickToClose = () => {
        setSearchOpen(false)
        cartContext.setCartOpen(false)
        cartContext.setAccountOpen(false)
        setOrderOpen(false)
        document.body.style.overflow = 'unset';
    }

    if (searchOpen || cartContext.cartOpen || cartContext.accountOpen || orderOpen) {
        document.body.style.overflow = 'hidden';
    }

    const token = "Bearer ".concat(localStorage.getItem("token_user"))
    useEffect(() => {
        localStorage.setItem('login_user', loginUser)
    }, [loginUser])

    useEffect(() => {
        axios.get("http://localhost:10399/user/authen/validate/token", {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    const userInfo = JSON.stringify(res.data.data)
                    localStorage.setItem('user_info', userInfo)
                    setLoginUser(1);
                }
            })
            .catch(err => {
                setLoginUser(0);
            })
    }, [loginUser])

    return (
        <div className="header">
            <div className="flex-center">
                <ul className="menu">
                    <li><a href="/home">Home</a></li>
                    <li><a href="/product">Product</a></li>
                    <li><a href="/contact">Contact</a></li>
                    <li><a href="/about">About</a></li>
                </ul>
            </div>
            <Account accountOpen={cartContext.accountOpen} clickToClose={clickToClose} loginUser={loginUser}
                     setLoginUser={setLoginUser}/>
            <Search searchOpen={searchOpen} clickToClose={clickToClose}/>
            <Cart cartOpen={cartContext.cartOpen} clickToClose={clickToClose}/>
            <OrderDetail orderOpen={orderOpen} clickToClose={clickToClose}/>
            <div className="flex-center">
                <ul className="menu">
                    <li className="search-icon">
                        <div>
                            <FontAwesomeIcon onClick={() => {
                                setSearchOpen(true)
                            }} icon={faSearch} style={{marginLeft: '10px'}}/>
                        </div>
                    </li>
                    <li className="cart-icon">
                        <div>
                            <FontAwesomeIcon icon={faCartPlus} onClick={() => {
                                cartContext.setCartOpen(true)
                            }}/>
                        </div>
                    </li>
                    <li className="order-icon">
                        <div>
                            <FontAwesomeIcon icon={faClipboardList} onClick={() => {
                                setOrderOpen(true)
                            }}/>
                        </div>
                    </li>

                    {/*icon personal*/}
                    <li className="personal-icon">
                        <div>
                            <FontAwesomeIcon icon={faUser} onClick={() => {
                                cartContext.setAccountOpen(true)
                            }}/>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
    )
}
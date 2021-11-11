import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCartPlus, faSearch, faHeart} from "@fortawesome/free-solid-svg-icons";
import React, {useEffect, useState} from "react";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";
import Search from "./Search/Search";
import Cart from "./Cart/Cart";
import './Header.css'
import Account from "./Account/Account";
import axios from "axios";
export default function Header(props){
    const [searchOpen, setSearchOpen] = useState(false)
    const [cartOpen, setCartOpen] = useState(false)
    const [accountOpen, setAccountOpen] = useState(false)
    const [wishlistOpen, setWishlistOpen] = useState(false)
    const [loginUser,setLoginUser] = useState(localStorage.getItem("login_user"))
    const clickToClose = () => {
        setSearchOpen(false)
        setCartOpen(false)
        setAccountOpen(false)
        setWishlistOpen(false)
        document.body.style.overflow = 'unset';
    }

    if (searchOpen || cartOpen || accountOpen || wishlistOpen) {
        document.body.style.overflow = 'hidden';
    }

    const token = "Bearer ".concat(localStorage.getItem("token_user"))
    useEffect(()=>{
        localStorage.setItem('login_user',loginUser)
    },[loginUser])

    useEffect(()=>{
        axios.get("http://localhost:10399/user/authen/validate/token", {headers:{Authorization: token}})
            .then(res => {
                if(res.data.code === 200){
                    const userInfo = JSON.stringify(res.data.data)
                    localStorage.setItem('userInfo',userInfo)
                    setLoginUser(1);
                }
            })
            .catch(err =>{
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
            <Account accountOpen = {accountOpen} clickToClose={clickToClose} loginUser = {loginUser} setLoginUser = {setLoginUser}/>
            <Search searchOpen={searchOpen} clickToClose={clickToClose}/>
            <Cart cartOpen={cartOpen} clickToClose={clickToClose}/>
            <div className="flex-center">
                <ul className="menu">
                    <li className="search-icon">
                        <div>
                            <FontAwesomeIcon onClick={()=>{setSearchOpen(true)}} icon={faSearch} style={{marginLeft: '10px'}}/>
                        </div>
                    </li>
                    <li className="cart-icon">
                        <div>
                            <FontAwesomeIcon icon={faCartPlus} onClick={() => { setCartOpen(true) }}/>
                        </div>
                    </li>
                    {/*icon wishlist*/}
                    <li className="wishlist-icon">
                        <div>
                            <FontAwesomeIcon icon={faHeart} onClick={() => { setWishlistOpen(true) }}/>
                        </div>
                    </li>

                    {/*icon personal*/}
                    <li className="personal-icon">
                        <div>
                            <FontAwesomeIcon icon={faUser} onClick={()=>{setAccountOpen(true)}}/>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
    )
}
import React, {useContext} from 'react'
import { faTimes } from "@fortawesome/free-solid-svg-icons"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import './Cart.css'
export default function Cart(props){
    const total = 0
    return(
        <div className={props.cartOpen === false ? "Cart displayNone" : "Cart"}>
            <div className="cart-header">
                <div className="cart-header__title">
                    Cart
                </div>
                <div className="cart-header__close"
                     onClick={props.clickToClose}>
                    <FontAwesomeIcon icon={faTimes} className="icon"/>
                </div>
            </div>
            <div>
            </div>
            <div className="cart-checkout-box flex-center">
                <div className="cart-checkout-text flex">
                    <p>Total: </p>
                    {
                        total &&
                        <p>{total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")}</p>
                    }
                </div>
                {/*<div*/}
                {/*    className="cart-checkout-btn btn"*/}
                {/*    onClick={()=>{*/}
                {/*        if (total > 0) {*/}
                {/*            props.history.push(`/checkout`);*/}
                {/*        }*/}
                {/*    }}*/}
                {/*>*/}
                {/*    Checkout*/}
                {/*</div>*/}
            </div>
        </div>
    )
}
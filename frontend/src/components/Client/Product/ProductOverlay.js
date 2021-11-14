import React, {useContext, useState} from 'react'
import './Product.css'
import '../../../App.css'
import '../../../assets/css/index.css'
import '../../../assets/css/grid.css'
import '../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCartPlus, faEye} from "@fortawesome/free-solid-svg-icons";
import {CartContext} from "../../../context/CartContext";
import {useHistory} from "react-router-dom";

export default function ProductOverlay(props) {
    const history = useHistory()
    const cartContext = useContext(CartContext)
    const [loading, setLoading] = useState(0)

    // const {addToCart, addToWishList} = useContext(CartContext)
    const addToCart = (product) => {
        cartContext.addToCart(product.id)
        // cartContext.setCartOpen(true)
    }

    const cartClick = () => {
        setLoading(1)
        setTimeout(() => {
            setLoading(0)
            addToCart(props.product)
        }, 500)
    }

    const redirect = (event) => {
        if (event.target.id === "overlay") {
            history.push(`/product/${props.product.id}`);
        }
    }

    return (
        <div className="product-overlay" id="overlay" onClick={redirect}>
            <div className="product-icon-box flex-center icon-cart btn-product" onClick={cartClick}>
                {
                    loading === 1 &&
                    <div className="loading-icon">
                    </div>
                }
                {loading !== 1 && <FontAwesomeIcon icon={faCartPlus} style={{marginRight: '3px'}}/>}
            </div>
        </div>
    )
}
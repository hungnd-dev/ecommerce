import React, {useState, useContext} from 'react'
import './Product.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartPlus, faEye, faHeart } from "@fortawesome/free-solid-svg-icons";

export default function ProductOverlay(props) {
    const [loading, setLoading] = useState(0)

    // const {addToCart, addToWishList} = useContext(CartContext)
    const addToCart = ()=>{

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
            props.history.push(`/product/${props.product.productID}`);
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
                { loading !== 1 &&  <FontAwesomeIcon icon={faCartPlus} style={{marginRight: '3px'}}/> }
            </div>
            <div className="product-icon-box flex-center icon-view btn-product" onClick={props.openView}>
                <FontAwesomeIcon icon={faEye}/>
            </div>
        </div>
    )
}
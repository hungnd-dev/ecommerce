import React, {useContext, useState} from 'react';
import './ProductBody.css'
import '../../../App.css'
import '../../../assets/css/index.css'
import '../../../assets/css/grid.css'
import '../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faAngleRight, faCartPlus, faMinus, faPlus} from "@fortawesome/free-solid-svg-icons";
import {CartContext} from "../../../context/CartContext";
import {GlobalContext} from "../../../context/GlobalContext";

export default function ProductBody(props) {
    const global = useContext(GlobalContext)
    const cartContext = useContext(CartContext)
    const addToCart = (product) => {
        cartContext.addToCart(product.id)
        // cartContext.setCartOpen(true)
    }
    const product = props.product

    const [loading, setLoading] = useState(0)

    const cartClick = () => {
        setLoading(1)
        setTimeout(() => {
            setLoading(0)
            addToCart(product)
        }, 500)
    }

    return (
        <div className='ProductBody'>

            {/* breadcrumb */}
            <div className="product-breadcrumb flex">
                <a className="breadcrumb-item breadcrumb-link" href='/'>Home</a>
                <FontAwesomeIcon icon={faAngleRight} className="breadcrumb-arrow"/>
                <div className="breadcrumb-item breadcrumb-product">{product.name}</div>
            </div>

            <div className='product-detail flex'>

                <div className="product-gallery flex">
                    <div className='product-big'>
                        <div className='product-big-item'>
                            <img src={product.images}/>
                        </div>
                    </div>
                </div>

                <div className="product-info-detail">

                    <div className="product-info-title">
                        {product.name}
                    </div>

                    <div className="product-info-des">
                        <p>{product.des}</p>
                    </div>

                    <div className="product-info-price">
                        {global.convert(product.price)}
                    </div>

                    <div className="product-info-cart flex">
                        {loading === 1 &&
                        <div className="product-info-addtocart flex-center " onClick={cartClick}>
                            <div className="loading-icon"></div>
                        </div>
                        }
                        {loading !== 1 &&
                        <div className="product-info-addtocart flex-center  " onClick={cartClick}>
                            <FontAwesomeIcon icon={faCartPlus}/>
                            <p>Add to cart</p>
                        </div>
                        }

                    </div>
                    <div className="product-info-line"></div>


                </div>

            </div>
            <div className="product-info-line"></div>

        </div>
    );
};

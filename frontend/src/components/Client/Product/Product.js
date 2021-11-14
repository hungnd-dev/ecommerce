import React, {useContext, useState} from 'react'
import './Product.css'
import '../../../App.css'
import '../../../assets/css/index.css'
import '../../../assets/css/grid.css'
import '../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import ProductOverlay from './ProductOverlay'
import {useHistory} from "react-router-dom";
import {GlobalContext} from "../../../context/GlobalContext";

export default function Product(props) {
    const [hover, setHover] = useState(false)   //để css
    const global = useContext(GlobalContext)
    const product = props.product
    const history = useHistory()

    const redirect = () => {
        window.scrollTo(0, 200)
        history.push(`/product/${product.id}`);
    }
    return (
        <div className="Product" style={{width:'cal(33.33%)'}}>
            <div className="product-img" onMouseOver={() => setHover(true)} onMouseOut={() => setHover(false)}>
                <div className="product-img-bg" onClick={redirect}>
                    <img src={product.images} alt={product.name}/>
                </div>
                <ProductOverlay product={product}/>
                <div className="product-tag">
                    {
                        product.sale > 0 && <div className="product-tag-item sale">
                            {product.sale}%
                        </div>
                    }
                    {
                        product.sold > 40 && <div className="product-tag-item new">
                            HOT
                        </div>
                    }
                </div>
            </div>
            <div className="product-title">
                {product.name}
            </div>
            {
                product.sale > 0 &&
                <div className="product-price flex-center">
                    <p>{global.convert(product.price - product.sale / 100 * product.price)}</p>
                    <p style={{textDecoration: 'line-through', color: '#777', marginLeft: '10px'}}>
                        {global.convert(product.price)}
                    </p>
                </div>
            }
            {
                product.sale === 0 &&
                <div className='product-price'>
                    <p>
                        {global.convert(product.price)}
                    </p>
                </div>
            }

        </div>
    )

}
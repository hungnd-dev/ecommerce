import React, {useState} from 'react'
import './Product.css'
import ProductOverlay from './ProductOverlay'

export default function Product(props) {
    const [hover, setHover] = useState(false)   //để css
    const [view, setView] = useState(false) //
    const product = props.product
    let id = product.id

    const openView = () => {
        setView(true)
    }
    if (view) {
        document.body.style.overflow = 'hidden' // disable scroll
    }

    const redirect = () => {    // sự kiện chuột, chuyển hướng trang đến /products/:id
        window.scrollTo(0, 0)
        props.history.push(`/product/${id}`);
        if (props.check)
            props.check()
    }

    let productDate = new Date(product.date)
    let today = new Date()

    return (
        <div className="Product">
            <div className="product-img" onMouseOver={() => setHover(true)} onMouseOut={() => setHover(false)}>
                <div className="product-img-bg" onClick={redirect}>
                    <img src={product.images} alt={product.name}/>
                </div>
                <ProductOverlay product={product} openView={openView}/>
            </div>
            <div className="product-title">
                {product.name}
            </div>
            {
                product.sale > 0 &&
                <div className="product-price flex-center">
                    <p>{product.price-product.sale / 100 * product.price}Đ</p>
                    <p style={{textDecoration: 'line-through', color: '#777', marginLeft: '10px'}}>
                        {product.price} Đ
                    </p>
                </div>
            }
            {
                product.sale === 0 &&
                <div className='product-price'>
                    <p>
                        {product.price} Đ
                    </p>
                </div>
            }

        </div>
    )

}
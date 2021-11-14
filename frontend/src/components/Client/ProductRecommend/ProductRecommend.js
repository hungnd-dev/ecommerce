import React, {useContext} from 'react';
import Product from '../Product/Product'
import './ProductRecommend.css';
import '../../../App.css'
import '../../../assets/css/index.css'
import '../../../assets/css/grid.css'
import '../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import {ProductContext} from "../../../context/ProductContext";

export default function ProductRecommend(props) {
    const {products} = useContext(ProductContext)
    let productInfo = []
    if (props.product != 0) {
        productInfo = props.product;
    }
    const recommendProducts = [];
    products.filter((item) => {
        if (item.id !== productInfo.id) {
            if (Math.abs(item.price - productInfo.price) < 2000000) {
                recommendProducts.push(item)
            }
        }
        return null;
    })

    //push cho du 5 phan tu
    if (recommendProducts.length < 6) {
        products.filter((item) => {
            if (recommendProducts.length < 6) {
                recommendProducts.push(item)
            } else return
        })
    }

    const limitProducts = products.slice(0, 5)

    return (
        <div className="ProductRecommend">

            <div className="newsletter-container flex-center">
                <div className="newsletter-title">Related products</div>
                <div className="RecommendProduct">
                    {limitProducts.map(function (item, index) {
                        return (
                            <Product
                                check={props.check}
                                key={index}
                                product={item}
                                index={index}
                            />
                        )
                    })}
                </div>
            </div>
            <div className="product-info-line"></div>
        </div>
    );
};

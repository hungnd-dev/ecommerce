import React, {useState} from 'react';
import Product from "../Product/Product";
import './HomeTabContent.css'
import '../../../App.css'
import '../../../assets/css/index.css'
import '../../../assets/css/grid.css'
import '../../../assets/boxicons-2.0.7/css/boxicons.min.css'

export default function HomeTabContent(props) {
    const [limit, setLimit] = useState(12)
    const products = props.products
    const height = props.height
    const [limitProducts, setLimitProducts] = useState(products.slice(0, limit))
    const handleClick = () => {
        setLimit(limit + 4)
        setLimitProducts(products.slice(0, limit))
    }
    return (
        <div>
            <div className="BestSeller" style={{minHeight: `${height}px`}}>
                {
                    limitProducts.map((item, index) => {
                        return (
                            <Product key={index} product={item} index={index}/>
                        )
                    })
                }
                {
                    limitProducts.length === 0 &&
                    <div className="home-tab-content-nothing">
                        there's nothing here yet
                    </div>
                }
            </div>
            {
                (products.length > 12 && products.length >= limit) &&
                <div className="tab-loadmore flex-center">
                    <button className="tab-loadmore-btn btn" onClick={function (e) {
                        handleClick()
                    }
                    }>
                        <p>More</p>
                    </button>
                </div>
            }
        </div>
    )
}
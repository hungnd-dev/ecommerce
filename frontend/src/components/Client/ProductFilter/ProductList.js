import React, {useState} from 'react';
import './ProductList.css';
import Product from "../Product/Product";

export default function ProductList(props) {
    const [limit, setLimit] = useState(8)//10 san pham toi da
    const [loading, setLoading] = useState(false)
    const products = props.products
    const height = props.height

    const handleClick = () => {
        setLoading(true)
        setTimeout(() => {
            setLoading(false)
            setLimit(limit + 4);
        }, 1500);
    }

    const limitProducts = products.slice(0, limit)

    return(
        <div>
            <div className="best-seller" style= {{minHeight: `${height}px`}}>
                {
                    limitProducts.map((item, index) => {
                        return (
                            <Product
                                key={index}
                                product={item}
                                index={index}
                            />
                        )
                    })
                }
                {
                    limitProducts.length === 0 &&
                    <div style={{
                        textAlign: 'center',
                        width: '100%',
                        textTransform: 'capitalize',
                        marginTop: '150px'
                    }}>
                        There's nothing here yet
                    </div>
                }
            </div>
            {
                (products.length > 10 && products.length >= limit) &&
                <div className="tab-filter flex-center">
                    <div
                        className="tab-filter-btn btn"
                        onClick={handleClick}
                    >
                        Load More
                    </div>
                    {
                        loading === true &&
                        <div className="tab-filter-btn tab-filter-loading btn-not-hover">
                            <div className="loading-icon"></div>
                        </div>
                    }
                </div>
            }
        </div>
    )

}
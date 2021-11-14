import Header from "../../components/Client/Header/Header";
import Banner from "../../components/Client/Banner/Banner";
import RecommendBanner from "../../components/Client/RecommendBanner/RecommendBanner";
import Newsletter from "../../components/Client/NewLetter/NewsLetter";
import Footer from "../../components/Client/Footer/Footer";
import ProductBody from "../../components/Client/ProductBody/ProductBody";
import ProductRecommend from "../../components/Client/ProductRecommend/ProductRecommend";
import {useContext, useEffect, useState} from "react";
import {ProductContext} from "../../context/ProductContext";

export default function ProductDetail(props) {
    const [id, setId] = useState(false);

    const [product, setProduct] = useState([])

    const {products} = useContext(ProductContext)

    useEffect(() => {
        document.body.style.overflow = 'unset';
    })

    // get id from path to get product
    useEffect(() => {
        let id = parseInt(props.match.params.id)
        setProduct(products.find(e => e.id === id))
        console.log(product)
    }, [id])

    return (
        <div>
            <Header/>
            <Banner/>
            <RecommendBanner/>
            <ProductBody product={product}/>
            <ProductRecommend product={product}/>
            <Newsletter/>
            <Footer/>
        </div>
    )
}
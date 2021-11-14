import Header from "../../components/Client/Header/Header";
import Banner from "../../components/Client/Banner/Banner";
import RecommendBanner from "../../components/Client/RecommendBanner/RecommendBanner";
import Newsletter from "../../components/Client/NewLetter/NewsLetter";
import Footer from "../../components/Client/Footer/Footer";
import ProductFilter from "../../components/Client/ProductFilter/ProductFilter";

export default function ProductPage() {
    return (
        <div>
            <Header/>
            <Banner/>
            <RecommendBanner/>
            <ProductFilter/>
            <Newsletter/>
            <Footer/>
        </div>
    )
}
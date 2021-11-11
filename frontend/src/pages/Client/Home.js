import Header from "../../components/Client/Header/Header";
import Banner from "../../components/Client/Banner/Banner";
import RecommendBanner from "../../components/Client/RecommendBanner/RecommendBanner";
import Footer from "../../components/Client/Footer/Footer";
import Newsletter from "../../components/Client/NewLetter/NewsLetter";
import HomeTab from "../../components/Client/HomeTab/HomeTab";

export default function Home(){
    return(
        <div>
            <Header/>
            <Banner/>
            <RecommendBanner/>
            <HomeTab/>
            <Newsletter/>
            <Footer/>
        </div>
    )
}
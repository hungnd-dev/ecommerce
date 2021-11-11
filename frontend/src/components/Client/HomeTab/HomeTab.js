import React, {useContext, useEffect, useState} from "react";
import './HomeTab.css'
import HomeTabContent from "./HomeTabContent";
import axios from "axios";
import {ProductContext} from "../../../context/ProductContext";

export default function HomeTab() {

    const [currentTab, setCurrentTab] = useState(2);
    const {products} = useContext(ProductContext)
    const [saleProduct, setSaleProduct] = useState([]);
    const [dateProduct, setDateProduct] = useState([]);
    const [sellProduct, setSellProduct] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    let sellHeight = 550;
    let dateHeight = 550;
    let saleHeight = 550;
    ///get product

    if(isLoading === true){
        if (products.length) {
            products.sort((a, b) => (a.sale > b.sale) ? 1 : -1)
            setSaleProduct([...products])
            if (saleProduct.length <= 5) {
                saleHeight = 260;
            }
        }
        //new product within 10 days
        const today = new Date();
        if (products.length) {
            products.sort((a, b) => (new Date(a.date) > new Date(b.date)) ? 1 : -1)
            setDateProduct([...products])
            dateProduct.filter((s) => {
                if ((today - new Date(s.date)) / (1000 * 3600 * 24) < 10) return true;
                else return false;
            })
            if (dateProduct.length <= 5) {
                dateHeight = 260;
            }
        }
        if (products.length) {
            products.sort((a, b) => (a.sold > b.sold) ? 1 : -1)
            setSellProduct([...products])
            if (sellProduct.length <= 5) {
                sellHeight = 260;
            }
        }
        console.log("product list get in HomeTab")
        console.log(sellProduct)
        setIsLoading(false)
    }
    return (
        <div className="HomeTab">
            <div className="home-tab flex-center">
                <p onClick={() => setCurrentTab(1)} className={currentTab === 1 ? "home-tab-active" : ""}>
                    Best Sellers
                </p>
                <p onClick={() => setCurrentTab(2)} className={currentTab === 2 ? "home-tab-active" : ""}>
                    New Products
                </p>
                <p onClick={() => setCurrentTab(3)} className={currentTab === 3 ? "home-tab-active" : ""}>
                    Sales Products
                </p>
            </div>
            <div className="tab-content">
                {
                    currentTab === 1 && <HomeTabContent products={sellProduct} height={sellHeight}/>
                }
                {
                    currentTab === 2 && <HomeTabContent products={dateProduct} height={dateHeight}/>
                }
                {
                    currentTab === 3 && <HomeTabContent products={saleProduct} height={saleHeight}/>
                }
            </div>
        </div>
    );
}
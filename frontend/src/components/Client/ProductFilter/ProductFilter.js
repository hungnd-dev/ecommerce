import {useContext, useEffect, useState} from "react";
import "./ProductFilter.css";
import ProductList from "./ProductList";
import {ProductContext} from "../../../context/ProductContext";

export default function ProductFilter() {
    const {products} = useContext(ProductContext)
    const filterBrandList = [...new Set(products.map(e => e.brandName))]
    const filterRamList = [...new Set(products.map(e => e.ram))]
    const filterCpuList = [...new Set(products.map(e => e.cpu))]
    const filterSsdList = [...new Set(products.map(e => e.ssd))]

    const [filteredList, setFilteredList] = useState(products)
    const [filterflow, setFilterflow] = useState([])
    const [activeFilter, setActiveFilter] = useState([])

    const onFilterChange = (filterIndex) => {
        let newActiveFilter
        if (activeFilter.includes(filterIndex)) {
            let index = activeFilter.indexOf(filterIndex);
            console.log(index)
            newActiveFilter = [...activeFilter]
            newActiveFilter.splice(index, 1)
        } else {
            newActiveFilter = [...activeFilter]
            newActiveFilter.push(filterIndex)
        }
        if (typeof newActiveFilter === 'number') {
            // setActiveFilter(new Array(newActiveFilter))
        } else {
            setActiveFilter(newActiveFilter)
        }

    }

    const checkFilter = (filterIndex) => {
        if (activeFilter.includes(filterIndex)) {
            return true;
        } else {
            return false
        }
    }

    useEffect(() => {
        let newFilteredList = []
        let allProductList = products
        let tempFilterList = []
        if(activeFilter.length > 0 ) {
            activeFilter.forEach(activeFilter => {
                if (activeFilter >= 0 && activeFilter < 100) {
                    tempFilterList = allProductList.filter(e => (
                        e.brandName === filterBrandList[activeFilter]
                    ))
                }
                if (activeFilter >= 100 && activeFilter < 200) {
                    tempFilterList = allProductList.filter(e => (
                        e.ram === filterRamList[activeFilter-100]
                    ))
                }
                if (activeFilter >= 200 && activeFilter < 300) {
                    tempFilterList = allProductList.filter(e => (
                        e.ssd === filterSsdList[activeFilter-200]
                    ))
                }
                if (activeFilter >= 300 && activeFilter < 400) {
                    tempFilterList = allProductList.filter(e => (
                        e.cpu === filterCpuList[activeFilter-300]
                    ))
                }
                tempFilterList.forEach(e=>{
                    if(!newFilteredList.includes(e)){
                        newFilteredList.push(e)
                    }
                })
            })
            setFilteredList(newFilteredList)
        }else{
            setFilteredList(allProductList)
        }

    }, [activeFilter])
    return (
        <div className="Product-Filter">
            <div className="col-3 p-0 p-r-30">
                <div className="filter-title">Hãng sản xuất</div>
                <div className="filter-list">
                    {filterBrandList.map((filter, index) => (
                        <div className="filter-items">
                            <input
                                className="input-filter"
                                id={index}
                                type="checkbox"
                                // defaultChecked={checkFilter(index)}
                                onClick={() => onFilterChange(index)}
                            />
                            <label htmlFor={index} className="label-filter">{filter}</label>
                        </div>
                    ))}
                </div>
                <br/>
                <br/>
                <div className="filter-title">Dung lượng RAM</div>
                <div className="filter-list">
                    {filterRamList.map((filter, index) => (
                        <div className="filter-items">
                            <input
                                className="input-filter"
                                id={index + 100}
                                type="checkbox"
                                // defaultChecked={checkFilter(index + 100)}
                                onClick={() => onFilterChange(index + 100)}
                            />
                            <label htmlFor={index + 100} className="label-filter">{filter}</label>
                        </div>
                    ))}
                </div>
                <br/>
                <br/>
                <div className="filter-title">Dung lượng SSD</div>
                <div className="filter-list">
                    {filterSsdList.map((filter, index) => (
                        <div className="filter-items">
                            <input
                                className="input-filter"
                                id={index + 200}
                                type="checkbox"
                                // defaultChecked={checkFilter(index + 200)}
                                onClick={() => onFilterChange(index + 200)}
                            />
                            <label htmlFor={index + 200} className="label-filter">{filter}</label>
                        </div>
                    ))}
                </div>
                <br/>
                <br/>
                <div className="filter-title">Vi xử lí</div>
                <div className="filter-list">
                    {filterCpuList.map((filter, index) => (
                        <div className="filter-items-cpu">
                            <input
                                className="input-filter"
                                id={index + 300}
                                type="checkbox"
                                // defaultChecked={checkFilter(index + 300)}
                                onClick={() => onFilterChange(index + 300)}
                            />
                            <label htmlFor={index + 300} className="label-filter">{filter}</label>
                        </div>
                    ))}
                </div>
            </div>
            <div className="col-9">
                <div className="product-filter-header">
                    <div className="product-filter-header-title">
                        <div className="product-filter-header-setflex">
                            <h1 className="product-filter-header-title-setfont">Laptop</h1>
                            <span className="span-title">:   ({filteredList.length} sản phẩm) </span>
                        </div>
                    </div>

                    <div className="product-filter-header-tag">
                        <div className="product-filter-header-setflex">
                            <span className="span-title">Lọc theo:</span>
                            {
                                filterflow.map((filter, index) => (
                                    <span className="span-title span-title-filterflow" key={index}>
                                        {filter.brandName}{filter.ramName}{filter.ssdName}{filter.priceName}{filter.cpuName}
                                    </span>
                                ))
                            }
                        </div>
                    </div>
                </div>
                <div className="product-filter-header">
                    <ProductList
                        products={filteredList}
                    />
                </div>
            </div>
        </div>
    );
}
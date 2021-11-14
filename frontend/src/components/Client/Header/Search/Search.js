import React, {useContext, useEffect, useState} from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faSearch, faTimes} from '@fortawesome/free-solid-svg-icons';
import './Search.css'
import '../../../../App.css'
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import '../../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import axios from "axios";
import {useHistory} from "react-router-dom";
import {ToastCustomContext} from "../../../../context/ToastContext";

export default function Search(props) {
    const toast = useContext(ToastCustomContext)
    const history = useHistory()
    const [products, setProducts] = useState([])
    const [searchInput, setSearchInput] = useState("")
    const [constProducts, setConstProducts] = useState([])
    useEffect(() => {
        axios({
            method: "get",
            url: "http://localhost:10399/product/all",
        }).then(res => {
            setProducts(res.data.data)
            setConstProducts(res.data.data)
        })
            .catch(err => {
                toast.showToast(err.message,"error")
            })

    }, [])

    const search = (event) => {
        const value = event.target.value
        const search = []
        for (let i in constProducts) {
            if ((constProducts[i].des).toLowerCase().includes(value.toLowerCase())) {
                search.push(constProducts[i])
            }
        }
        setSearchInput(value)
        setProducts(search)
    }
    const handleClick = (id) => {
        history.push(`/product/${id}`)
    }

    return (
        <div class={props.searchOpen === true ? 'Search' : 'Search displayNone'}>

            <div className="search-header">
                <div className="search-title">Search</div>
                <div
                    className="search-close"
                    onClick={props.clickToClose}
                >
                    <FontAwesomeIcon icon={faTimes} className="icon"/>
                </div>
            </div>

            <div>
                <div className={props.searchOpen === false ? '' : 'fadeIn'}>
                    <div className="search-form">
                        <form className="flex">
                            <FontAwesomeIcon icon={faSearch} className="icon"/>
                            <input placeholder="Search" onChange={search}/>
                        </form>
                    </div>

                    {
                        (products.length > 0 && searchInput !== "") &&
                        products.map((item, index) => {
                            return (
                                <div className="search-item" key={index}>
                                    <div className="search-item-img">
                                        <img src={item.images} height="100%" alt=""/>
                                    </div>
                                    <div className="search-item-body">
                                        <div className="search-item-name">
                                            <p>{item.name}</p>
                                        </div>
                                    </div>
                                    <div className="item-view">
                                        <div className="button" key={item.id} id={item.id}
                                             onClick={() => handleClick(item.id)}>
                                            VIEW
                                        </div>
                                    </div>

                                </div>
                            )
                        })
                    }
                </div>
            </div>
        </div>
    )
}
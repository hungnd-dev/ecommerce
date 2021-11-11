import './Product.css'
import {useHistory} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import CreateProduct from "./CreateProduct";
export default function Product(){
    const [create,setCreate] = useState(false);
    const history = useHistory();
    let token = "Bearer ".concat(localStorage.getItem("tokenAdmin"))
    const [isLoading,setIsLoading] = useState(true);
    const productTableHead = [
        '',
        'name',
        'price',
        'quantity',
        'images',
        'brand',
        'ram',
        'ssd'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.name}</td>
            <td>{item.price}</td>
            <td>{item.quantity}</td>
            <td><img src={item.images} alt={item.name} className="img-product"/></td>
            <td>{item.brandName}</td>
            <td>{item.ram}</td>
            <td>{item.ssd}</td>
        </tr>
    )
    const [product,setProduct] = useState([]);
//get customer
    useEffect(()=>{
        axios({
            method:"get",
            url:"http://localhost:10399/product/all",
        }).then(res =>{
            setProduct(res.data.data)
            setIsLoading(false)
        })
            .catch(err =>{
                alert(err)
            })

    },[])

    if(isLoading){
        return <div></div>
    }

    const openCreateProduct = ()=>{
        setCreate(true);
    }
    return(
        <div>
            <h1 className="page-header">
                Products
            </h1>
            <div>
                <button className="button-default" onClick={openCreateProduct}>
                    Create
                </button>
            </div>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            {
                                create ?
                                    <CreateProduct create={create} setCreate={setCreate} /> :
                                    <Table
                                        limit='10'
                                        headData={productTableHead}
                                        renderHead={(item, index) => renderHead(item, index)}
                                        bodyData={product}
                                        renderBody={(item, index) => renderBody(item, index)}
                                    />
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
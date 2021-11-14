import React, {useContext, useEffect, useState} from 'react'
import './CartDetail.css'
import Table from "../../../Admin/Table/Table";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMinus, faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import {CartContext} from "../../../../context/CartContext";

export default function CartDetail(props) {
    const cart = useContext(CartContext)
    const addToCart = (product)=>{
        cart.addToCart(product.id)
    }
    const reduceCart = (product)=>{
        cart.reduceCart(product.id)
    }
    const detailTableHead = [
        '',
        'name',
        'price',
        'quantity',
        'images',
        'total'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.name}</td>
            <td>{props.convert(item.price)}</td>
            <td>
                <i onClick={()=> reduceCart(item)}><FontAwesomeIcon  style={{pointerEvents: 'none', marginRight:'10px'}} icon={faMinus} /></i>
                {item.quantity}
                <i onClick={()=>addToCart(item)}><FontAwesomeIcon  style={{pointerEvents: 'none',marginLeft:'10px'}} icon={faPlus} /></i>
            </td>
            <td><img src={item.image} alt={item.name} className="img-product"/></td>
            <td>{props.convert(item.total)}</td>
        </tr>
    )
    return (
        <div>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            {
                                <Table
                                    limit='10'
                                    headData={detailTableHead}
                                    renderHead={(item, index) => renderHead(item, index)}
                                    bodyData={props.detail}
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
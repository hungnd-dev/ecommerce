import React, {useContext, useEffect, useState} from "react";
import {ToastCustomContext} from "../../../../context/ToastContext";
import axios from "axios";
import Table from "../../../Admin/Table/Table";
import './OrderDetail.css'
import '../Cart/Cart.css'
import '../../../../App.css'
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import '../../../../assets/boxicons-2.0.7/css/boxicons.min.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
export default function OrderDetail(props){
    const toast = useContext(ToastCustomContext)
    let token = "Bearer ".concat(localStorage.getItem("token_user"))
    const handleReject = (orderId) => {
        axios.get("http://localhost:10399/user/order/reject?order_id=" + orderId, {
            headers: {Authorization: token}
        })
            .then(res => {
                if (res.data.code = 200) {
                    toast.showToast("Rejected", "success")
                    window.location.reload()
                }
            })
            .catch(err => {
                toast.showToast(err, "error")
            })
    }
    const orderTableHead = [
        '',
        'name',
        'address',
        'phone',
        'delivery',
        'amount',
        'create',
        'state',
        ''
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.name}</td>
            <td>{item.address}</td>
            <td>{item.phoneReceive}</td>
            <td>{item.deliveryType}</td>
            <td>{item.amount}</td>
            <td>{item.createAt}</td>
            {
                item.state == 0 && <td>Unpaid</td> ||
                item.state == 1 && <td>Confirmed</td> ||
                item.state == -1 && <td>Rejected</td>
            }
            {
                item.state == 0 && <td className={"reject"} onClick={() => handleReject(item.id)}>Reject</td>
            }
        </tr>
    )
    const [order, setOrder] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:10399/user/order/view", {headers: {Authorization: token}})
            .then(res => {
                setOrder(res.data.data)
            })
            .catch(err => {
                // toast.showToast(err.message, "error")
            })

    }, [])
    return (
        <div className={props.orderOpen === true? "Order":"Order displayNone"}>
            <div className="cart-header">
                <div className="cart-header__title">
                    Your Orders
                </div>
                <div className="cart-header__close"
                     onClick={()=> {
                         props.clickToClose()
                     }}>
                    <FontAwesomeIcon icon={faTimes} className="icon"/>
                </div>
            </div>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            {
                                <Table
                                    limit='10'
                                    headData={orderTableHead}
                                    renderHead={(item, index) => renderHead(item, index)}
                                    bodyData={order}
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
import React, {useContext, useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import './Order.css'
import {ToastCustomContext} from "../../../../context/ToastContext";
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import {GlobalContext} from "../../../../context/GlobalContext";
export default function Order() {
    //toast to display popup notification
    const toast = useContext(ToastCustomContext)
    const global = useContext(GlobalContext)
    let token = "Bearer ".concat(localStorage.getItem("token_admin"))
    //call api for confirm order completed
    const handleConfirm = (orderId) => {
        axios.get("http://localhost:10399/admin/manage/order/confirm?order_id=" + orderId, {
            headers: {Authorization: token}
        })
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Confirmed", "success")
                    window.location.reload()
                }
            })
            .catch(err => {
                toast.showToast(err.message, "error")
            })
    }
    //call api for reject order: dont apply this order
    const handleReject = (orderId) => {
        axios.get("http://localhost:10399/admin/manage/order/reject?order_id=" + orderId, {
            headers: {Authorization: token}
        })
            .then(res => {
                if (res.data.code = 200) {
                    toast.showToast("Rejected", "success")
                    window.location.reload()
                }
            })
            .catch(err => {
                toast.showToast(err.message, "error")
            })
    }
    const orderTableHead = [
        '',
        'username',
        'address',
        'phone',
        'delivery',
        'amount',
        'create',
        'state',
        '', ''
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.user.username}</td>
            <td>{item.address}</td>
            <td>{item.phoneReceive}</td>
            <td>{item.deliveryType}</td>
            <td>{global.convert(item.amount)}</td>
            <td>{item.createAt}</td>
            {
                item.state == 0 && <td>Unpaid</td> ||
                item.state == 1 && <td>Confirmed</td> ||
                item.state == -1 && <td>Rejected</td>
            }
            {
                item.state == 0 && <td className={"confirm"} onClick={() => handleConfirm(item.id)}>Confirm</td>
            }
            {
                item.state == 0 && <td className={"reject"} onClick={() => handleReject(item.id)}>Reject</td>
            }
        </tr>
    )
    const [order, setOrder] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:10399/admin/manage/order/view/all", {headers: {Authorization: token}})
            .then(res => {
                setOrder(res.data.data)
            })
            .catch(err => {
                toast.showToast(err.message, "error")
            })

    }, [])
    return (
        <div>
            <h1 className="page-header">
                Orders
            </h1>
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
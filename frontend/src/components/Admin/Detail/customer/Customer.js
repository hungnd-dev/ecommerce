import React, {useContext, useEffect, useState} from "react";
import axios from "axios";
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import Table from "../../Table/Table";
import {useHistory} from "react-router-dom";
import './Customer.css'
import {ToastCustomContext} from "../../../../context/ToastContext";
export default function Customer() {
    const toast = useContext(ToastCustomContext)
    const history = useHistory();
    let token = "Bearer ".concat(localStorage.getItem("token_admin"))
    const handleOnBlock = (e) => {
        axios.get("http://localhost:10399/admin/manage/user/block?username=" + e, {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Block successfully","success")
                    window.location.reload()
                }
            })
            .catch(err => {
                toast.showToast(err.message,"error")
                history.push("/admin")
            })

    }
    const handleOnUnblock = (e) => {
        axios.get("http://localhost:10399/admin/manage/user/unblock?username=" + e, {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Unblock successfully","success")
                    window.location.reload()
                }
            })
            .catch(err => {
                toast.showToast(err.message,"error")
                history.push("/admin")
            })

    }
    //table head -> fields to display
    const customerTableHead = [
        '',
        'username',
        'fullname',
        'phone',
        'created',
        'block'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    //how to render body?? expose it here
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.username}</td>
            <td>{item.fullname}</td>
            <td>{item.telephone}</td>
            <td>{item.createdAt}</td>
            {/*<td>{item.state}</td>*/}
            <td className="column-block">
                {
                    item.state ?
                        <div className="column-block" onClick={() => handleOnBlock(item.username)}>Block</div> :
                        <div className="column-block" onClick={() => handleOnUnblock(item.username)}>Unblock</div>
                }
            </td>
        </tr>
    )
    const [customer, setCustomer] = useState([]);
    //get customer from api
    useEffect(() => {
        axios({
            method: "get",
            url: "http://localhost:10399/admin/manage/user/view/",
            headers: {
                "Authorization": token
            }
        }).then(res => {
            setCustomer(res.data.data)
        })
            .catch(err => {
                toast.showToast(err.message,"error")
            })

    }, [])

    return (
        <div>
            <h2 className="page-header">
                customers
            </h2>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            <Table
                                limit='10'
                                headData={customerTableHead}
                                renderHead={(item, index) => renderHead(item, index)}
                                bodyData={customer}
                                renderBody={(item, index) => renderBody(item, index)}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
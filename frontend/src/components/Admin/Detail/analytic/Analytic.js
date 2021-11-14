import React, {useContext, useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import {ToastCustomContext} from "../../../../context/ToastContext";
import './Analytic.css'
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import {GlobalContext} from "../../../../context/GlobalContext";
export default function Analytic() {
    const toast = useContext(ToastCustomContext)
    const global = useContext(GlobalContext)
    const [month, setMonth] = useState(1);
    const [analytic, setAnalytic] = useState([]);
    let token = "Bearer ".concat(localStorage.getItem("token_admin"))
    const analyticTableHead = [
        'order',
        'order unpaid',
        'order confirm',
        'order reject',
        'revenue'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{item.totalOrder}</td>
            <td>{item.totalOrderUnpaid}</td>
            <td>{item.totalOrderPaid}</td>
            <td>{item.totalOrderReject}</td>
            <td>{global.convert(item.money)}</td>
        </tr>
    )
    useEffect(() => {
        axios({
            method: "get",
            url: "http://localhost:10399/admin/statistical?month=" + month,
            headers: {
                Authorization: token
            }
        }).then(res => {
            let r = [];
            r.push(res.data.data)
            setAnalytic(r)
        })
            .catch(err => {
                toast.showToast(err.message,"error")
            })

    }, [month])
    const handleOnChange = () => {
        let x = document.getElementById("cars").value
        setMonth(x);
    }
    return (
        <div>
            <h2 className="page-header">
                Analytics
            </h2>
            <label htmlFor="cars">Choose time:</label>

            <select id="cars" onChange={handleOnChange}>
                <option value="1" selected>1 month</option>
                <option value="3">3 month</option>
                <option value="6">6 month</option>
                <option value="12">12 month</option>
            </select>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            <Table
                                limit='10'
                                headData={analyticTableHead}
                                renderHead={(item, index) => renderHead(item, index)}
                                bodyData={analytic}
                                renderBody={(item, index) => renderBody(item, index)}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
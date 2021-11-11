import {useHistory} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";

export default function Analytic(){
    const [month,setMonth] = useState(1);
    const [analytic,setAnalytic] = useState([]);
    let token = "Bearer ".concat(localStorage.getItem("tokenAdmin"))
    const [isLoading,setIsLoading] = useState(true);
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
            <td>{item.money}</td>
        </tr>
    )
    useEffect(()=>{
        axios({
            method:"get",
            url:"http://localhost:10399/admin/statistical?month="+month,
            headers:{
                Authorization: token
            }
        }).then(res =>{
            let r = [];
            r.push(res.data.data)
            setAnalytic(r)
            setIsLoading(false)
        })
            .catch(err =>{
                alert(err)
            })

    },[month])
    const handleOnChange = ()=>{
        let x = document.getElementById("cars").value
        setMonth(x);
    }
    if(isLoading){
        return <div></div>
    }
    return(
        <div>
            <h2 className="page-header">
                Analytics
            </h2>
            <label htmlFor="cars">Choose time:</label>

            <select id="cars" onChange={handleOnChange}>
                <option value="1" selected >1 month</option>
                <option value="3" >3 month</option>
                <option value="6" >6 month</option>
                <option value="12" >12 month</option>
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
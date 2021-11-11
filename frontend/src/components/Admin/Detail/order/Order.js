import React, {useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import './Order.css'

export default function Order(){
    let token = "Bearer ".concat(localStorage.getItem("tokenAdmin"))
    const [isLoading,setIsLoading] = useState(true);
    const handleConfirm = (orderId)=>{
        axios.get("http://localhost:10399/admin/manage/order/confirm?order_id="+orderId, {
            headers: {Authorization:token}
        })
            .then(res=>{
                if(res.data.code===200){
                    alert("confirm successfully")
                    window.location.reload()
                }
            })
            .catch(err=>{
                alert(err)
            })
    }
    const handleReject = (orderId)=>{
        axios.get("http://localhost:10399/admin/manage/order/reject?order_id="+orderId, {
            headers: {Authorization:token}
        })
            .then(res=>{
                if(res.data.code=200){
                    alert("reject successfully")
                    window.location.reload()
                }
            })
            .catch(err=>{
                alert(err)
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
        '',''
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.user.username}</td>
            {/*<td>{item.user.fullname}</td>*/}
            <td>{item.address}</td>
            <td>{item.phoneReceive}</td>
            <td>{item.deliveryType}</td>
            <td>{item.amount}</td>
            <td>{item.createAt}</td>
            {
                item.state== 0 &&<td>Unpaid</td>||
                item.state== 1 &&<td>Confirmed</td>||
                item.state==-1 &&<td>Rejected</td>
            }
            {
                item.state == 0 && <td className={"confirm"} onClick={()=>handleConfirm(item.id)}>Confirm</td>
            }
            {
                item.state == 0 && <td className={"reject"} onClick={()=>handleReject(item.id)}>Reject</td>
            }
        </tr>
    )
    const [order,setOrder] = useState([]);
    useEffect(()=>{
        axios.get("http://localhost:10399/admin/manage/order/view/all",{headers:{Authorization:token}})
            .then(res =>{
            setOrder(res.data.data)
            setIsLoading(false)
        })
            .catch(err =>{
                alert(err)
            })

    },[])

    if(isLoading){
        return <div></div>
    }
    return(
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
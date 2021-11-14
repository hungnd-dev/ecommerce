import React, {useContext, useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import './Category.css'
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import CreateCategory from "./CreateCategory";
import {ToastCustomContext} from "../../../../context/ToastContext";

export default function Categories() {
    const toast = useContext(ToastCustomContext)
    const [create, setCreate] = useState(false);
    const categoriesTableHead = [
        '',
        'name',
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>
    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{index}</td>
            <td>{item.name}</td>
        </tr>
    )
    const [category, setCategory] = useState([]);
    useEffect(() => {
        axios({
            method: "get",
            url: "http://localhost:10399/brand/all",
        }).then(res => {
            setCategory(res.data.data)
        })
            .catch(err => {
                toast.showToast(err.message,"error")
            })

    }, [])

    const openCreateProduct = () => {
        setCreate(true);
    }
    return (
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
                                    <CreateCategory create={create} setCreate={setCreate}/> :
                                    <Table
                                        limit='10'
                                        headData={categoriesTableHead}
                                        renderHead={(item, index) => renderHead(item, index)}
                                        bodyData={category}
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
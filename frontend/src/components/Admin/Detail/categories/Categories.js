import React, {useEffect, useState} from "react";
import axios from "axios";
import Table from "../../Table/Table";
import CreateCategory from "./CreateCategory";

export default function Categories(){
    const [create,setCreate] = useState(false);
    const [isLoading,setIsLoading] = useState(true);
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
    const [category,setCategory] = useState([]);
    useEffect(()=>{
        axios({
            method:"get",
            url:"http://localhost:10399/brand/all",
        }).then(res =>{
            setCategory(res.data.data)
            setIsLoading(false)
        })
            .catch(err =>{
                alert(err)
            })

    },[])

    if(isLoading){
        return <div>

        </div>
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
                                    <CreateCategory create={create} setCreate={setCreate} /> :
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
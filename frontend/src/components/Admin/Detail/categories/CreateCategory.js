import {useState} from "react";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import './CreateCategory.css'
export default function CreateCategory(props){
    const [name,setName] = useState("");
    const handleOnChange = (e)=>{
        setName(e.target.value)
    }
    const handleOnSubmit = (e)=>{
        e.preventDefault()
        axios.get("http://localhost:10399/admin/manage/brand/add?brand_name="+name, {headers:{Authorization: "Bearer ".concat(localStorage.getItem("tokenAdmin"))}})
            .then(res =>{
                if(res.data.code === 200){
                    alert("create successfully")
                    // props.setCreate(false);
                    window.location.reload()
                }else{
                    alert(res.data.message)
                }
            })
            .catch(err => {
                alert(err.message)
                console.log(err)
            })

    }
    return(
        <div>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">Create new category</div>
                    <div className="sign-close" onClick={()=>props.setCreate(false)}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div >

                <div>
                    <form onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Brand name" name="name" onChange={handleOnChange}/>
                        <button className="btn" type="submit" >Create</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
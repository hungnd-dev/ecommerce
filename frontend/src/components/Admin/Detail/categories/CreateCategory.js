import {useContext, useState} from "react";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import './CreateCategory.css'
import '../../../../assets/css/index.css'
import '../../../../assets/css/grid.css'
import {ToastCustomContext} from "../../../../context/ToastContext";

export default function CreateCategory(props) {
    const toast = useContext(ToastCustomContext)
    const [name, setName] = useState("");
    const handleOnChange = (e) => {
        setName(e.target.value)
    }
    const handleOnSubmit = (e) => {
        e.preventDefault()
        axios.get("http://localhost:10399/admin/manage/brand/add?brand_name=" + name, {headers: {Authorization: "Bearer ".concat(localStorage.getItem("token_admin"))}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast(res.data.message,"success")
                    // props.setCreate(false);
                    window.location.reload()
                } else {
                    toast.showToast(res.data.message,"warn")
                }
            })
            .catch(err => {
                toast.showToast(err.message,"error")
            })

    }
    return (
        <div>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">Create new category</div>
                    <div className="sign-close" onClick={() => props.setCreate(false)}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div>

                <div>
                    <form onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Brand name" name="name"
                               onChange={handleOnChange}/>
                        <button className="btn" type="submit">Create</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
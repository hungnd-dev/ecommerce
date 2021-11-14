import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import './Create.css'
import {useContext, useState} from "react";
import axios from "axios";
import {ToastCustomContext} from "../../../../context/ToastContext";

export default function CreateProduct(props) {
    const toast = useContext(ToastCustomContext)
    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [des, setDes] = useState("");
    const [brand, setBrand] = useState("");
    const [ram, setRam] = useState("");
    const [ssd, setSsd] = useState("");
    const [display, setDisplay] = useState("");
    const [weight, setWeight] = useState("");
    const [cpu, setCpu] = useState("");
    const [sale, setSale] = useState("");
    const [sold, setSold] = useState("");
    const [date, setDate] = useState("");
    //get data after modify
    const handleOnChange = (e) => {
        let funcName = e.target.name
        let valueSet = e.target.value
        if (funcName === "name") {
            setName(valueSet)
        } else if (funcName === "price") {
            setPrice(valueSet)
        } else if (funcName === "quantity") {
            setQuantity(valueSet)
        } else if (funcName === "description") {
            setDes(valueSet)
        } else if (funcName === "brandName") {
            setBrand(valueSet)
        } else if (funcName === "ram") {
            setRam(valueSet)
        } else if (funcName === "ssd") {
            setSsd(valueSet)
        } else if (funcName === "display") {
            setDisplay(valueSet)
        } else if (funcName === "weight") {
            setWeight(valueSet)
        } else if (funcName === "cpu") {
            setCpu(valueSet)
        } else if (funcName === "sale") {
            setSale(valueSet)
        } else if (funcName === "sold") {
            setSold(valueSet)
        } else if (funcName === "date") {
            setDate(valueSet)
        }
    }
    //submit data by call api
    const handleOnSubmit = (e) => {
        e.preventDefault()
        axios.post("http://localhost:10399/admin/manage/product/create", {
            name: name,
            price: price,
            quantity: quantity,
            images: "",
            description: des,
            brandName: brand,
            ram: ram,
            ssd: ssd,
            display: display,
            weight: weight,
            cpu: cpu,
            sale: sale,
            sold: sold,
            date: date
        }, {headers: {Authorization: "Bearer ".concat(localStorage.getItem("token_admin"))}})
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Create successfully","success")
                    props.setCreate(false);
                } else {
                    toast.showToast(res.data.message,"warn")
                }
            })
            .catch(err => {
                toast.showToast(err.message,"success")
            })

    }
    return (
        <div>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">Create new product</div>
                    <div className="sign-close" onClick={() => props.setCreate(false)}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div>

                <div>
                    <form onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Product name" name="name"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product price" name="price"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product quantity" name="quantity"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product description" name="description"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product brand" name="brandName"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product ram" name="ram"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product ssd" name="ssd"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product display" name="display"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product weight" name="weight"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product cpu" name="cpu"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product sale" name="sale"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product sold" name="sold"
                               onChange={handleOnChange}/>
                        <input className="input-css" type="text" placeholder="Product date" name="date"
                               onChange={handleOnChange}/>
                        <button className="btn" type="submit">Create</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
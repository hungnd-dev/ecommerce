import './Order.css'
import '../Account/Account.css'
import '../../../../App.css'
import React, {useContext, useState} from "react";
import axios from "axios";
import {ToastCustomContext} from "../../../../context/ToastContext";

export default function Order(props) {
    const toast = useContext(ToastCustomContext)
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [phone, setPhone] = useState("");
    const [delivery, setDelivery] = useState("");
    const convert = (number) => {
        return number.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        })
    }
    const handleOnChange = (e) => {
        let nameFunc = e.target.name
        let value = e.target.value
        console.log(value)
        if (nameFunc === "name") {
            setName(value)
        } else if (nameFunc === "address") {
            setAddress(value)
        } else if (nameFunc === "phone") {
            setPhone(value)
        } else if (nameFunc === "delivery") {
            setDelivery(value)
        }
    }

    const handleOnSubmit = (event) => {
        event.preventDefault()
        let url = "http://localhost:10399/user/order/create?" +
            "name=" + name +
            "&phone=" + phone +
            "&address=" + address +
            "&delivery=" + delivery
        axios.get(url,
            {
                headers: {Authorization: "Bearer ".concat(localStorage.getItem("token_user"))}
            }
        )
            .then(res => {
                if (res.data.code === 200) {
                    toast.showToast("Order successfully. Please wait and rate for product wit us")
                    window.location.reload()
                }
            })
            .catch(err => toast.showToast(err.message,"error"))

    }

    return (
        <div className={props.orderOpen === false ? "Order displayNone" : "Order"}>
            <div className="search-form login-form fadeToLeft">
                <form className="flex-col" onSubmit={handleOnSubmit}>
                    <input type="text" placeholder="Name" name="name" onChange={handleOnChange}/>
                    <input type="text" placeholder="Address" name="address" onChange={handleOnChange}/>
                    <input type="text" placeholder="Phone" name="phone" onChange={handleOnChange}/>
                    <input list="deliveryList" placeholder="Delivery" name="delivery" onChange={handleOnChange}/>
                    <datalist id="deliveryList">
                        <option value="Standard"/>
                        <option value="Express"/>
                        <option value="Super Express"/>
                    </datalist>
                    <p>Total: </p>
                    {convert(props.amount)}
                    <button className="btn">Order</button>
                </form>
            </div>
        </div>
    )
}
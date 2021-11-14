import {useContext, useState} from "react";
import axios from "axios";
import {ToastCustomContext} from "../../../context/ToastContext";
import './Login.css'
import '../../../App.css'
export default function Login(props) {
    const toast = useContext(ToastCustomContext)
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const handleOnSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:10399/sign_in/admin", {
            username: username,
            password: password
        })
            .then(res => {
                if (res.data.code === 200) {
                    const adminInfo = JSON.stringify(res.data.data.userResponse)
                    localStorage.setItem('admin_info', adminInfo)
                    localStorage.setItem('token_admin', res.data.data.accessToken)
                    props.setLogin(1)
                    toast.showToast("Login successfully","success")
                } else {
                    // alert(res.data.message)
                    toast.showToast(res.data.message,"error")
                }
            })
            .catch(err => toast.showToast(err.message,"error"))
    }

    const handleChangeUserName = (event) => {
        setUsername(event.target.value)
    }

    const handleChangePassWord = (event) => {
        setPassword(event.target.value)
    }
    return (
        <div className={'Login'}>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">Admin Account</div>
                </div>

                <div className="sign-form">
                    <form className="flex-col" onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Username" name="username"
                               onChange={handleChangeUserName}/>
                        <input className="input-css" type="password" placeholder="Password" name="password"
                               onChange={handleChangePassWord}/>
                        <button className="btn" type="submit">Sign in</button>
                    </form>

                    <div className="forgot-password">
                        <a href="">FORGOT PASSWORD</a>
                    </div>
                </div>
            </div>
        </div>
    )
}

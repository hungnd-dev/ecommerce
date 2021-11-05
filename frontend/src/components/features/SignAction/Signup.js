import {Link} from "react-router-dom";
import "../../../App.css"
import "./SignAction.css"

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import {useState} from "react";
import axios from "axios";

export default function Signup(props){
    const [fullname, setFullname] = useState("");
    const [phone, setPhone] = useState("");
    const [username, setUsername] = useState("");
    const [password,setPassword] = useState("");
    const handleOnSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:10399/sign_up",{
            username:username,
            password:password,
            fullName:fullname,
            phone:phone
        })
            .then(res => {
                if(res.data.code == 200){
                    alert(res.data.message)
                    props.closeSignPage()
                }
                else{
                    alert(res.data.message)
                }
            })
            .catch(err => alert(err))
    }

    const handleChangeFullname = (event) =>{
        setFullname(event.target.value)
    }
    const handleChangePhone = (event) =>{
        setPhone(event.target.value)
    }
    const handleChangeUserName = (event) =>{
        setUsername(event.target.value)
    }

    const handleChangePassWord = (event) =>{
        setPassword(event.target.value)
    }

    return(
        <div className={props.openSignUp === false ? 'Sign displayNone' : 'Sign'}>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">My Account</div>
                    <div className="sign-close" onClick={props.closeSignPage}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div >

                <div className="sign-form">
                    <form className="flex-col" onSubmit={handleOnSubmit}>
                        <input className="input-css"
                               type="text"
                               placeholder="Your fullname"
                               name="fullname" onChange={handleChangeFullname}/>
                        <input className="input-css"
                               type="text"
                               placeholder="Your phone"
                               name="phone" onChange={handleChangePhone}/>
                        <input className="input-css"
                               type="text"
                               placeholder="Username"
                               name="username" onChange={handleChangeUserName}/>
                        <input className="input-css" type="password" placeholder="Password" name="password" onChange={handleChangePassWord}/>
                        <br/>
                        <button className="btn" type="submit">Register</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
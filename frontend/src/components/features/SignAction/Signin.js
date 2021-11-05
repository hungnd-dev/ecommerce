import {Link} from "react-router-dom";
import "./SignAction.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes, faCheck} from "@fortawesome/free-solid-svg-icons";
import {useContext, useState} from "react";
import axios from 'axios'
import {StateContext} from "../../../contexts/state/StateContext";
export default function Signin(props){
    const [checkRememberMe, setCheckRememberMe] = useState(false);
    const [username, setUsername] = useState("");
    const [password,setPassword] = useState("");
    const handleOnSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:10399/sign_in",{
            username:username,
            password:password
        })
            .then(res => {
                if(res.data.code == 200){
                    const userInfo = JSON.stringify(res.data.data.user)
                    console.log(userInfo)
                    localStorage.setItem('userInfo',userInfo)
                    localStorage.setItem('token',res.data.data.accessToken)
                    props.setSignIn(1)
                    props.closeSignPage();
                }
                else{
                    alert(res.data.message)
                }
            })
            .catch(err => alert(err))
    }

    const handleChangeUserName = (event) =>{
        setUsername(event.target.value)
    }

    const handleChangePassWord = (event) =>{
        setPassword(event.target.value)
    }

    const handleCheckRemember = () => {
        if (checkRememberMe) {
            setCheckRememberMe(false)
        } else {
            setCheckRememberMe(true)
        }
    }
    return(
        <div className={props.openSignIn === false ? 'Sign displayNone' : 'Sign'}>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">My Account</div>
                    <div className="sign-close" onClick={props.closeSignPage}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div >

                <div className="sign-form">
                    <form className="flex-col" onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Username" name="username" onChange={handleChangeUserName}/>
                        <input className="input-css" type="password" placeholder="Password" name="password" onChange={handleChangePassWord}/>
                        <div className="remember-me">
                            <div className="checkbox">
                                <input className="checkbox-css" type="checkbox" name="checkbox" onClick={handleCheckRemember}/>
                            </div>
                            <div className="content-remember">Remember me</div>
                        </div>
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
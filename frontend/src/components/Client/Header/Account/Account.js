import React, { useContext, useEffect, useState } from 'react';
import './Account.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTimes , faCheck } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios'
import AccountInfo from "./AccountInfo";
export default function Account(props){
    const [loginErr, setLoginErr] = useState(false)
    const [errMes, setErrMes] = useState("");
    const [tabID, setTabID] = useState(0);
    const [check,setCheck] = useState(false);
    const [username,setUsername] = useState("");
    const [password,setPassword]= useState("");

    const [username_re, setUsername_re] = useState("");
    const [password_re, setPassword_re] = useState("");
    const [fullname_re, setFullname_re] = useState("");
    const [phone_re, setPhone_re] = useState("");

    //function
    const handleOnChange = (event)=>{
        if(event.target.name === "username"){
            setUsername(event.target.value)
        }else if(event.target.name === "password"){
            setPassword(event.target.value)
        }else if(event.target.name === "username_re"){
            setUsername_re(event.target.value)
        }else if(event.target.name === "password_re"){
            setPassword_re(event.target.value)
        }else if(event.target.name === "phone_re"){
            setPhone_re(event.target.value)
        }else if(event.target.name === "fullname_re"){
            setFullname_re(event.target.value)
        }
    }

    const handleOnSubmitLogin = (event)=>{
        event.preventDefault()
        axios.post("http://localhost:10399/sign_in",{
            username:username,
            password:password
        })
            .then(res => {
                if(res.data.code === 200){
                    const userInfo = JSON.stringify(res.data.data.userResponse)
                    console.log(userInfo)
                    localStorage.setItem('userInfo',userInfo)
                    localStorage.setItem('token_user',res.data.data.accessToken)
                    props.setLoginUser(1)
                }
                else{
                    setLoginErr(true)
                    setErrMes(res.data.message)
                }
            })
            .catch(err => alert(err.message))

    }
    const handleOnSubmitRegister = (event)=>{
        event.preventDefault()
        axios.post("http://localhost:10399/sign_up",{
            username:username_re,
            password:password_re,
            fullname:fullname_re,
            telephone:phone_re
        })
            .then(res => {
                if(res.data.code === 200){
                    alert("Create Successfully")
                }
                else{
                    alert(res.data.message)
                }
            })
            .catch(err => alert(err.message))
    }
    return(
        <div className={props.accountOpen === false ? 'Account displayNone' : 'Account'}>
            <div className="account-container">
                <div className="search-header flex">
                    <div className="search-title">My Account</div>
                    <div className="search-close" onClick={props.clickToClose}>
                        <FontAwesomeIcon icon={faTimes} className="icon"/>
                    </div>
                </div >

                {props.loginUser === 1 &&
                    <AccountInfo/>
                }

                {props.loginUser === 0 &&
                <div className={props.accountOpen === false ? '' : 'fadeIn'}>
                    <div
                        className='search-tab login-tab flex'>
                        <div className={tabID === 0 ? 'search-tab-cate search-tab-active' : 'search-tab-cate'}
                             onClick={() => {setTabID(0)}}>
                            Login
                        </div>
                        <div className={tabID === 1 ? 'search-tab-cate search-tab-active' : 'search-tab-cate'}
                            onClick={() => {setTabID(1)}}>
                            Register
                        </div>
                    </div>
                    <div className="login-err flex-center flex-col">
                        <div>
                            {
                                loginErr &&
                                <div>
                                    <FontAwesomeIcon icon={faTimes} style={{ marginRight: '10px', color: 'red'}}/>
                                    {errMes}
                                </div>
                            }
                        </div>
                    </div>
                    { tabID === 0 &&
                    <div className="search-form login-form fadeToRight">
                        <form className="flex-col" onSubmit={handleOnSubmitLogin}>
                            <input className="input-class" type="text" placeholder="Username" name="username" onChange={handleOnChange}/>
                            <input className="input-class" type="password" placeholder="Password" name="password" onChange={handleOnChange}/>
                            <div className="remember-login flex noselect"
                                 onClick={() => {
                                     if (check) {
                                         setCheck(false)
                                     } else {
                                         setCheck(true)
                                     }
                                 }}
                            >
                                <div className="check-box"></div>
                                {check &&
                                <div className="check-box-active flex-center" onClick={()=> setCheck(false)}>
                                    <FontAwesomeIcon className="check-box-active" icon={faCheck}></FontAwesomeIcon>
                                </div>
                                }
                                <p>Remember me</p>
                            </div>
                            <button type="submit" onClick={handleOnSubmitLogin} className="btn">LOGIN</button>
                            <label>LOST YOUR PASSWORD?</label>
                        </form>
                    </div>
                    }
                    { tabID === 1 &&
                    <div className="search-form login-form fadeToLeft">
                        <form className="flex-col" onSubmit={handleOnSubmitRegister}>
                            <input type="text" placeholder="Username" name="username_re" onChange={handleOnChange}/>
                            <input type="text" placeholder="Fullname" name="fullname_re" onChange={handleOnChange}/>
                            <input type="password" placeholder="Password" name="registerPassword" onChange={handleOnChange}/>
                            <input type="text" placeholder="Phone" name="phone_re" onChange={handleOnChange}/>
                            <button className="btn">REGISTER</button>
                        </form>
                    </div>
                    }
                </div>
                }
            </div>
        </div>
    )
}
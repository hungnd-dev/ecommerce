import {useState} from "react";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import {useHistory} from "react-router-dom";

export default function Login(props){

    const [username, setUsername] = useState("");
    const [password,setPassword] = useState("");
    const history = useHistory();
    const handleOnSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:10399/sign_in/admin",{
            username:username,
            password:password
        })
            .then(res => {
                if(res.data.code === 200){
                    const adminInfo = JSON.stringify(res.data.data.userResponse)
                    console.log(adminInfo)
                    localStorage.setItem('adminInfo',adminInfo)
                    localStorage.setItem('tokenAdmin',res.data.data.accessToken)
                    props.setLogin(1)
                }
                else{
                    alert(res.data.message)
                }
            })
            .catch(err => alert(err.message))
    }

    const handleChangeUserName = (event) =>{
        setUsername(event.target.value)
    }

    const handleChangePassWord = (event) =>{
        setPassword(event.target.value)
    }
    return(
        <div className={'Sign'}>
            <div className="sign-container">
                <div className="sign-header flex">
                    <div className="sign-title">Admin Account</div>
                </div >

                <div className="sign-form">
                    <form className="flex-col" onSubmit={handleOnSubmit}>
                        <input className="input-css" type="text" placeholder="Username" name="username" onChange={handleChangeUserName}/>
                        <input className="input-css" type="password" placeholder="Password" name="password" onChange={handleChangePassWord}/>
                        <button className="btn" type="submit" >Sign in</button>
                    </form>

                    <div className="forgot-password">
                        <a href="">FORGOT PASSWORD</a>
                    </div>
                </div>
            </div>
        </div>
    )
}

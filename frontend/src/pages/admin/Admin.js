import Login from "../../components/Admin/Login/Login";
import AdminPage from "./AdminPage";
import {useEffect, useState} from "react";
import axios from "axios";

export default function Admin(){
    document.title = 'D-shop Admin'
    let token = "Bearer ".concat(localStorage.getItem("tokenAdmin"))
    const [openLoginAdmin, setOpenLoginAdmin] = useState(false);
    const [loginAdmin, setLoginAdmin] = useState(localStorage.getItem("login"));
    function closeLogin(){
        document.body.style.overflow = 'unset';
        setOpenLoginAdmin(false);
    }
    if(openLoginAdmin){
        document.body.style.overflow = 'hidden';
    }
    useEffect(()=>{
        localStorage.setItem('login',loginAdmin)
    },[loginAdmin])

    useEffect(()=>{
        axios.get("http://localhost:10399/admin/authen/validate/token", {headers:{Authorization: token}})
            .then(res => {
                if(res.data.code === 200){
                    const adminInfo = JSON.stringify(res.data.data)
                    localStorage.setItem('adminInfo',adminInfo)
                    setLoginAdmin(1);
                }
            })
            .catch(err =>{
                setLoginAdmin(0);
            })
    }, [loginAdmin])
    return(
        <div>
            {
                loginAdmin == 0 && <Login setLogin = {setLoginAdmin}/>
            }
            {
                loginAdmin == 1 && <AdminPage setLogin = {setLoginAdmin}/>
            }
        </div>
    )
}
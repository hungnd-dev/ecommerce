import Login from "../../components/Admin/Login/Login";
import AdminPage from "./AdminPage";
import {useEffect, useState} from "react";
import axios from "axios";

export default function Admin() {
    //set title is admin page
    document.title = 'D-shop Admin';
    //get token bearer from localStorage
    //if token is null -> useEffect to catch in axios -> require user login-> render Login
    //else -> go to useEffect -> true -> render AdminPage
    let token = "Bearer ".concat(localStorage.getItem("token_admin"))
    const [loginAdmin, setLoginAdmin] = useState(localStorage.getItem("login_admin"));

    //two effect to update when every to admin page
    useEffect(() => {
        localStorage.setItem('login_admin', loginAdmin)
    }, [loginAdmin])

    useEffect(() => {
        axios.get("http://localhost:10399/admin/authen/validate/token", {headers: {Authorization: token}})
            .then(res => {
                if (res.data.code === 200) {
                    const adminInfo = JSON.stringify(res.data.data)
                    localStorage.setItem('admin_info', adminInfo)
                    setLoginAdmin(1);
                }
            })
            .catch(err => {
                setLoginAdmin(0);
            })
    }, [loginAdmin])
    return (
        <div>
            {
                loginAdmin === 0 && <Login setLogin={setLoginAdmin}/>
            }
            {
                loginAdmin === 1 && <AdminPage setLogin={setLoginAdmin}/>
            }
        </div>
    )
}
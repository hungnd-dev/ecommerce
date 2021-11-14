import './Profile.css'
import {useHistory} from "react-router-dom";

export default function Profile() {
    const history = useHistory()
    const adminInfo = localStorage.getItem("admin_info")
    if(adminInfo == null){
        history.push("/admin")
    }
    const admin = JSON.parse(adminInfo)
    return (
        <div className="profile">
            <p className={"profile-header"}>{admin.fullname}</p>
            <p className="profile-item">Username: {admin.username}</p>
            <p className="profile-item"> Telephone: {admin.telephone}</p>
            <p className="profile-item"> State: isActive</p>
            <p className="profile-role">
                Administrator of D-Shop
            </p>
        </div>
    )
}
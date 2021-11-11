import './Profile.css'
export default function Profile(){
    const adminInfo = localStorage.getItem("adminInfo")
    const admin = JSON.parse(adminInfo)
    return(
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
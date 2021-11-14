import './AccountInfo.css'
export default function AccountInfo() {
    const handleOnClick =()=>{
        localStorage.removeItem("user_info")
        localStorage.removeItem("token_user")
        localStorage.removeItem("login_user")
        window.location.reload()
    }
    const userInfo = localStorage.getItem("user_info")
    const user = JSON.parse(userInfo)
    return (
        <div className="profile">
            <p className={"profile-header"}>{user.fullname}</p>
            <p className="profile-item">Username: {user.username}</p>
            <p className="profile-item"> Telephone: {user.telephone}</p>
            <p className="profile-item"> State: isActive</p>
            <p className="profile-item"> Created At: {user.createdAt}</p>
            <p className="profile-role">
                Setting
            </p>
            <p className="profile-role" onClick={handleOnClick}>
                Log out
            </p>
        </div>
    )
}
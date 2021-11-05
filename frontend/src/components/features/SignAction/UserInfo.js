import "./SignAction.css"
export default function UserInfo(props){
    const user = localStorage.getItem('userInfo')
    const userInf = JSON.parse(user)
    console.log(userInf)
    return(
        <div className="flex xs:hidden items-center ml-auto">
            <div className="dropdown">
                <button className="dropbtn">{userInf.username}</button>
                <div className="dropdown-content">
                    <a>link 1</a>
                    <a>Link 2</a>
                    <a onClick={()=>{
                        props.setSignIn(0)
                    }}>Sign Out</a>
                </div>
            </div>
        </div>
    )
}
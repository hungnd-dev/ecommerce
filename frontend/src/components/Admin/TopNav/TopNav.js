import React from 'react'
import './topnav.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faSearch, faUser, faCartPlus, faBars, faAngleDown, faBell} from "@fortawesome/free-solid-svg-icons";
import {useHistory} from "react-router-dom";
export default function TopNav(props){
    const history = useHistory();
    const toProfile = ()=>{
        history.push("/admin/profile");
    }
    const toSetting = ()=>{
        history.push("/admin/setting");
    }
    return (
        <div className='topnav'>
            <div className="topnav__search">
                <input type="text" placeholder='Search here...' />
                <i className='bx bx-search'></i>
            </div>
            <div className="topnav__right">
                <div className="topnav__right-item">
                    <div className={"dropdown"}>
                        <FontAwesomeIcon icon={faUser}/>
                        <div className={"dropdown-content"}>
                            <p className="dropdown-content-item" onClick={toProfile}>Profile</p>
                            <p className="dropdown-content-item" onClick={toSetting}>Setting</p>
                            <p className="dropdown-content-item" onClick={props.logout}>Logout</p>
                        </div>
                    </div>
                </div>

                <div className="topnav__right-item">
                    <FontAwesomeIcon icon={faBell}></FontAwesomeIcon>
                </div>
            </div>
        </div>
    )
}

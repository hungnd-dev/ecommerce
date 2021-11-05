import {Fragment, useContext, useEffect, useState} from "react";
import Search from "../../components/features/Search/Search";
import Navigation from "../../components/features/Navigation/Navigation";
import Signup from "../../components/features/SignAction/Signup";
import "../../App.css"
import Signin from "../../components/features/SignAction/Signin";
import "../../components/features/SignAction/SignAction.css"
import {StateContext} from "../../contexts/state/StateContext";
import SignButton from "../../components/features/SignAction/SignButton";
import UserInfo from "../../components/features/SignAction/UserInfo";
export default function Header(){
    const [openSignIn, setOpenSignIn] = useState(false);
    const [openSignUp, setOpenSignUp] = useState(false);
    const [signIn,setSignIn] = useState(localStorage.getItem('signIn'));
    function closeSignPage(){
        document.body.style.overflow = 'unset';
        setOpenSignIn(false);
        setOpenSignUp(false);
    }
    if(openSignIn || openSignUp){
        document.body.style.overflow = 'hidden';
    }

    useEffect(()=>{
        localStorage.setItem('signIn',signIn)
    },[signIn])

    return(
        <div>
            <div className=" xs:px-2 sm:px-6 sm:py-3 md:px-8 sm:py-4 flex justify-start bg-brand-dark py-4 px-10 items-center">
                <Search/>
                <div className="flex flex-grow items-center">
                    <div className="xs:hidden sm:hidden md:hidden">
                        <Navigation/>
                    </div>
                    { signIn == 0 &&
                        <SignButton setOpenSignUp={setOpenSignUp} setOpenSignIn={setOpenSignIn}/>
                    }
                    { signIn == 1 &&
                        <UserInfo setSignIn = {setSignIn}/>
                    }
                </div>

            </div>
            <Signup openSignUp = {openSignUp} closeSignPage = {closeSignPage}/>
            <Signin openSignIn = {openSignIn} closeSignPage = {closeSignPage} setSignIn = {setSignIn}/>
        </div>
    )
}
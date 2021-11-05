import "./SignAction.css"
export default function SignButton(props){
    const setOpenSignUp = props.setOpenSignUp;
    const setOpenSignIn = props.setOpenSignIn;
    return (
        <div className="flex xs:hidden items-center ml-auto">
            <div>
                <button className="sign-button" onClick={()=>{
                    setOpenSignUp(true)
                    setOpenSignIn(false)
                }}>Sign up</button>
            </div>

            <div>
                <button className="sign-button" onClick={()=>{
                    setOpenSignIn(true);
                    setOpenSignUp(false);
                }}>Sign in</button>
            </div>
        </div>
    )
}
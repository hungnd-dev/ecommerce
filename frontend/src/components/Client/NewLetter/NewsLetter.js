import React, { useRef, useState } from 'react';
import './NewsLetter.css'
export default function Newsletter () {

    const inputEmailRef = useRef();
    const [emailInput, setEmailInput] = useState("");
    const handleOnChange = (event) => {
        setEmailInput(event.target.value)
    }
    const handleOnSubmit = (event) => {
        event.preventDefault();
        //code here to send email to database. This mean email will be notified every news from shop
    }


    return(
        <div className="Newsletter">
            <div className="newsletter-container">
                <div className="newsletter-title flex-center">NewsLetter</div>
                <div className="newsletter-small">Get timely updates from your favorite products</div>
                <form className="newsletter-form flex-center" onSubmit={handleOnSubmit}>
                    <input
                        className="newsletter-input"
                        placeholder="Enter your email address"
                        type="email"
                        onChange={handleOnChange}
                        ref={inputEmailRef}
                    ></input>
                    <button className="newsletter-btn btn">Subcribe</button>
                </form>
                <div className="newsletter-line"></div>
            </div>
        </div>
    )
}

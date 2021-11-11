import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookF, faTwitter, faInstagram, faPinterest, faGoogle } from '@fortawesome/free-brands-svg-icons';
// import { Link } from 'react-router-dom'
import './Footer.css'
import {Link} from "@material-ui/core";



export default function Footer() {

    return (
        <div className="Footer">
            <div className="footer-container">
                <div className="footer-left">
                    <p className="cr">©2021 D-Shop</p>
                </div>
                <div className="footer-right">
                    <a to="/contact">Contact us</a>
                    {/*add link to contact*/}
                    <FontAwesomeIcon icon={faTwitter} className="cart-icon-footer" />
                    <FontAwesomeIcon icon={faFacebookF} className="cart-icon-footer" />
                    <FontAwesomeIcon icon={faInstagram} className="cart-icon-footer" />
                    <FontAwesomeIcon icon={faPinterest} className="cart-icon-footer" />
                    <FontAwesomeIcon icon={faGoogle} className="cart-icon-footer" />
                </div>
            </div>
        </div>
    )
}

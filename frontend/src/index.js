import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {CartProvider} from "./context/CartContext";
import {ToastCustomProvider} from "./context/ToastContext";
import {GlobalProvider} from "./context/GlobalContext";

document.title = 'D-Shop'
ReactDOM.render(
    // <React.StrictMode>
    <GlobalProvider>
        <ToastCustomProvider>
            <CartProvider>
                <App/>
            </CartProvider>
        </ToastCustomProvider>
    </GlobalProvider>,
    // </React.StrictMode>,
    document.getElementById('root')
);

reportWebVitals();

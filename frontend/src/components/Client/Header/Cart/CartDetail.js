// import React, {useContext} from 'react'
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
// import { faMinus, faPlus, faTrash } from '@fortawesome/free-solid-svg-icons';
// import { CartContext } from '../../contexts/Cart';
// import './CartItem.css'
// import '../../App.css'
//
// export default function CartDetail(props) {
//     const {cartItems, minusCount, plusCount, removeFromCart, updateCount} = useContext(CartContext)
//
//     const convert = (number) => {
//         return number.toLocaleString('vi-VN', {
//             style: 'currency',
//             currency: 'VND'
//         })
//     }
//
//     return (
//         <div className="cart-list">
//             <div className="container">
//                 <div className="field">Image</div>
//                 <div className="field">Name</div>
//                 <div className="field">Amount</div>
//                 <div className="field">Price</div>
//                 <div className="field">Total Price</div>
//                 <div className="field">Remove</div>
//
//             </div>
//             {
//                 cartItems.map((item, index) => {
//                     return (
//                         <div className="container" key={index}>
//                             <div className="item">
//                                 {
//                                     item.productImg &&
//                                     <img src={item.productImg} width="100%"></img>
//                                 }
//                             </div>
//                             <div className="item">{item.productName}</div>
//                             <div className="item counter">
//                                 <div className="down" id={item.productID} onClick={minusCount}>
//                                     <FontAwesomeIcon  style={{pointerEvents: 'none'}} icon={faMinus}/>
//                                 </div>
//                                 <input className="count" type="text" value={item.count} id={item.productID} onChange={updateCount}/>
//                                 <div className="up" id={item.productID} onClick={plusCount}>
//                                     <FontAwesomeIcon  style={{pointerEvents: 'none'}} icon={faPlus}/>
//                                 </div>
//                             </div>
//                             <div className="item">{convert(item.productPrice)}</div>
//                             <div className="item">{convert(item.count*item.productPrice)}</div>
//                             <div className="item trash" id={item.productID} onClick={removeFromCart}>
//
//                                 <FontAwesomeIcon  style={{pointerEvents: 'none'}} icon={faTrash}/>
//                             </div>
//
//                         </div>
//                     )
//                 })
//             }
//         </div>
//
//     )
// }
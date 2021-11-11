import {Route, Switch} from "react-router-dom";
import Customer from "../Detail/customer/Customer"
import Dashboard from "../Detail/dashboard/Dashboard";
import Product from "../Detail/product/Product";
import Order from "../Detail/order/Order";
import Analytic from "../Detail/analytic/Analytic";
import Categories from "../Detail/categories/Categories";
import Discount from "../Detail/discount/Discount";
import Profile from "../Detail/profile/Profile";
import Setting from "../Detail/setting/Setting";
export default function RoutesAdmin(){
    return (
        <Switch>
            <Route path='/admin' exact component={Dashboard}/>
            <Route path='/admin/profile' exact component={Profile}/>
            <Route path='/admin/setting' exact component={Setting}/>
            <Route path='/admin/customers' component={Customer}/>
            <Route path='/admin/products' component={Product}/>
            <Route path='/admin/orders' component={Order}/>
            <Route path='/admin/analytics' component={Analytic}/>
            <Route path='/admin/categories' component={Categories}/>
            <Route path='/admin/discount' component={Discount}/>
        </Switch>
    )
}
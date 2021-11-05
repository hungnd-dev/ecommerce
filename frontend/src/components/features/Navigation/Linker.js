import * as ROUTES from "../../../constants/Routes";
import { NavLink } from "react-router-dom"
import "../../../App.css"
export default function Linker(){
    return(
        <nav>
            <ul className="flex text-white font-helvetica tracking-wide text-base xs:text-xs sm:text-sm md:text-sm justify-between xs:px-6">
                <li className="xl:mr-6 sm:mr-8 md:mr-8 mr-4"><NavLink to={ROUTES.FEATURES.link} activeClassName="cursor-pointer text-orange-400"> {ROUTES.FEATURES.name}</NavLink></li>
                <li className="xl:mr-6 sm:mr-8 md:mr-8 mr-4"><NavLink to={ROUTES.PRODUCTS.link} activeClassName="cursor-pointer text-orange-400"> {ROUTES.PRODUCTS.name}</NavLink></li>
                <li className="xl:mr-6 sm:mr-8 md:mr-8 mr-4"><NavLink to={ROUTES.CUSTOMERS.link} activeClassName="cursor-pointer text-orange-400" >{ROUTES.CUSTOMERS.name}</NavLink></li>
                <li className="xl:mr-6 sm:mr-8 md:mr-8 mr-4"><NavLink to={ROUTES.SALES.link} activeClassName="cursor-pointer text-orange-400">{ROUTES.SALES.name}</NavLink></li>
            </ul>
        </nav>
    )
}
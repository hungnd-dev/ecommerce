import Sidebar from "../../components/Admin/SideBar/Sidebar";
import {BrowserRouter, Route, useHistory} from "react-router-dom";
import './admin.css'
import RoutesAdmin from "../../components/Admin/Constants/RoutesAdmin";
import '../../assets/boxicons-2.0.7/css/boxicons.min.css'
import '../../assets/css/grid.css'
import '../../assets/css/theme.css'
import '../../assets/css/index.css'
import TopNav from "../../components/Admin/TopNav/TopNav";
export default function AdminPage(props) {
    const history = useHistory();
    const handleLogOut = (event) => {
        event.preventDefault();
        props.setLogin(0);
        localStorage.clear();
        history.push("/admin")
    }
    return (
        <BrowserRouter>
            <Route render={(props) => (
                <div className={`admin theme-mode-light theme-color-blue`}>
                    <Sidebar {...props}/>
                    <div className="admin__content">
                        <TopNav logout={handleLogOut}/>
                        <div className="admin__content-main">
                            <RoutesAdmin/>
                        </div>
                    </div>
                </div>
            )}>
            </Route>
        </BrowserRouter>
    )
}
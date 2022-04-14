import {history} from "../helpers/history";
import {Route, Router, Switch} from "react-router-dom";
import MainPage from "../components/main/MainPage";
import UserSectorPage from "../components/usersector/UserSectorPage";
import ManageSectorsPage from "../components/managesectors/ManageSectorsPage";
import NavRoute from "../components/NavRoute";
import React from "react";

function App() {
    return (
        <Router history={history}>
            <Switch>
                <Route path="/" component={MainPage} exact/>
                <NavRoute path="/register" component={UserSectorPage} exact/>
                <NavRoute path="/manage" component={ManageSectorsPage} exact/>
            </Switch>
        </Router>
    );
}

export default App;

import React from 'react';
import ReactDOM from 'react-dom';
import './css/reset.css';
import './css/timeline.css';
import './css/login.css';
import App from './App';
import Login from './componentes/Login';
import Logout from './componentes/Logout';
import {BrowserRouter, Route, Switch} from "react-router-dom";

ReactDOM.render(
    (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={Login}/>
                <Route path="/timeline/:user?" component={App}/>
                <Route path="/logout" component={Logout}/>
            </Switch>
        </BrowserRouter>
    ),
    document.getElementById('root')
);

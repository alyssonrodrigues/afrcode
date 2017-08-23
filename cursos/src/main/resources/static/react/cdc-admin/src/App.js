import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "./partials/Home";
import AutorBox from "./partials/Autor";
import { Link } from "react-router-dom";
import "./css/pure-min.css"
import "./css/side-menu.css";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div id="layout">
          <a href="#menu" id="menuLink" className="menu-link">
            <span></span>
          </a>

          <div id="menu">
            <div className="pure-menu">
              <Link className="pure-menu-heading" to="/">Company</Link>
              <ul className="pure-menu-list">
                <li className="pure-menu-item">
                  <Link to="/" className="pure-menu-link">Home</Link>
                </li>
                <li className="pure-menu-item">
                  <Link to="/autores" className="pure-menu-link">Autores</Link>
                </li>
                <li className="pure-menu-item">
                  <Link to="/livros" className="pure-menu-link">Livros</Link>
                </li>
              </ul>
            </div>
          </div>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/autores" component={AutorBox} />
          </Switch>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;

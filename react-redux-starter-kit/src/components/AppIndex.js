import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../logo.svg';
import './AppIndex.css';

const AppIndex = () => (
  <div className="App">
    <header className="App-header">
      <img src={logo} className="App-logo" alt="logo" />
      <h1 className="App-title">Bem vindo ao React</h1>
    </header>
    <p className="App-intro">
      Para iniciar, edite o <code>src/AppIndex.js</code> e salve para recarregar.
    </p>
    <p>
      <Link className="btn btn-link" to='/logout'>Sair</Link>
    </p>
  </div>
);

export default AppIndex;

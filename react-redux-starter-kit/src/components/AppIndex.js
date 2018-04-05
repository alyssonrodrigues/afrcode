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
    <br/>
    <p className="App-intro">
      Para iniciar, edite o <code>src/AppIndex.js</code> e salve para recarregar.
    </p>
    <p className="App-intro">
      Veja também o <code>src/routes/AppRoutes.js</code> para definições de "routes".
    </p>
    <p className="App-intro">
      Encontre em <code>src/util/applicationContext.js</code> configurações de contexto da aplicação.
    </p>
    <p>
      <Link className="btn btn-link" to='/logout'>Sair</Link>
    </p>
  </div>
);

export default AppIndex;

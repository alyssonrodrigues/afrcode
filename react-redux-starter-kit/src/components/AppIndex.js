import React from 'react'
import { Link } from 'react-router-dom'
import logo from '../logo.svg'
import './AppIndex.css'

const devTips = process.env.NODE_ENV === 'production' ? '' : (
  <div>
    <p className='App-intro'>
      Para iniciar, edite o <code>src/AppIndex.js</code> e salve para recarregar.
    </p>
    <p className='App-intro'>
      Veja também o <code>src/routes/AppRoutes.js</code> para definições de "routes".
    </p>
    <p className='App-intro'>
      Encontre em <code>src/util/applicationContext.js</code> configurações de contexto da aplicação.
    </p>
    <p className='App-intro'>
      Encontre em <code>src/interceptors</code> "interceptors" para aspectos comuns da aplicação.
    </p>
    <p className='App-intro'>
      O que temos para hoje:
    </p>
    <ul className='App-intro'>
      <li>standard e babel-eslint p/ JavaScript Standard Style,</li>
      <li>login, login redirect p/ authenticated routes,</li>
      <li>routes, authenticated routes, not found page route,</li>
      <li>axios p/ requests, request error interceptor,</li>
      <li>authentication JWT, authentication JWT request interceptor,</li>
      <li>central error boundary,</li>
      <li>logout,</li>
      <li>application context config,</li>
      <li>security context holder/config,</li>
      <li>redux store config, redux-form reducer config,</li>
      <li>jest p/ TU,</li>
      <li>TODO...</li>
    </ul>
  </div>)

const AppIndex = () => (
  <div className='App'>
    <header className='App-header'>
      <img src={logo} className='App-logo' alt='logo' />
      <h1 className='App-title'>Bem vindo ao React</h1>
    </header>
    <br />
    {devTips}
    <p>
      <Link className='btn btn-link' to='/logout'>Sair</Link>
    </p>
  </div>
)

export default AppIndex

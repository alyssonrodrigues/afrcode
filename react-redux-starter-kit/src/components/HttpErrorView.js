import React from 'react'
import { Link } from 'react-router-dom'

import App from './App'

// TODO refinar página de erro http genérico
const HttpErrorView = () => (
  <App noAppToolbar>
    <h4>
      Erro http genérico
    </h4>
    <Link to='/'>Retornar</Link>
  </App>
)

export default HttpErrorView

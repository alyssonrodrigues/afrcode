import React from 'react'
import { Link } from 'react-router-dom'

import App from './App'

// TODO refinar página não encontrada
const NotFoundPage = () => (
  <App>
    <h4>
      404 Página não encontrada
    </h4>
    <Link to='/'>Retornar</Link>
  </App>
)

export default NotFoundPage
